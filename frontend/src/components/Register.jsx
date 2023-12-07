import React, { useState } from 'react'
import Button from './Button'
import { useForm } from 'react-hook-form'
import axios from 'axios'


export default function Register({ onLogin }) {
    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm()
    const [authError, setAuthError] = useState()

    async function create(body) {
        try {
            const oldDate = new Date(body.DOB);
            const day = oldDate.getDate();
            const month = oldDate.getMonth() + 1;
            const year = oldDate.getFullYear();
            const date = `${day}/${month}/${year}`;

            const { data } = await axios.post('api/auth/register', { ...body, DOB: date });
            localStorage.setItem('token', data.accessToken)
            window.location.reload()
            setAuthError()
        } catch (e) {
            setAuthError(e?.response?.data?.error)
        }
    }

    console.log(errors)

    return (
        <div className="auth-container">
            <form className="auth-form-container" onSubmit={handleSubmit(create)}>
                <h1 className="">Create new account</h1>
                {!!authError && <label>
                    <span className="error">{authError}</span></label>}

                <label className="">
                    <span>Full name</span>
                    <input type="text" placeholder="First name last name" {...register('name', { required: { message: "Required", value: true } })} />
                    {!!errors.name && <span className="error">{errors.name?.message}</span>}
                </label>
                <label className="">
                    <span>Date of birth</span>
                    <input type="date" placeholder="dd/mm/yyyy" {...register('DOB', { required: { message: "Required", value: true } })} />
                    {!!errors.DOB && <span className="error">{errors.DOB?.message}</span>}
                </label>
                <label className="">
                    <span>Email address</span>
                    <input type="text" placeholder="email@gmail.com" {...register('email', { required: { message: "Required", value: true }, pattern: { message: "Invalid email", value: /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/ } })} />
                    {!!errors.email && <span className="error">{errors.email?.message}</span>}
                </label>
                <label className="">
                    <span>Password</span>
                    <input type="password" placeholder="Password" {...register('password', { required: { message: "Required", value: true } })} />
                    {!!errors.password && <span className="error">{errors.password?.message}</span>}
                </label>
                <Button type="submit" title="Register" />
                <button className='text-btn' type='button' onClick={onLogin}>Login</button>
            </form>
        </div>
    )
}

