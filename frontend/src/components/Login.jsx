import React, { useState } from 'react'
import Button from './Button'
import { useForm } from 'react-hook-form'
import axios from 'axios'


function Login({onRegister}) {
    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm()
    const [authError, setAuthError] = useState()

    async function login({ email, password }) {
        try {
            const { data } = await axios.post('api/auth/login', { email, password });
            setAuthError()
            localStorage.setItem('token', data.accessToken)
            window.location.reload()
        } catch (e) {
            setAuthError(e?.response?.data?.error)
        }
    }


    return (
        <div className="auth-container">
            <form className="auth-form-container" onSubmit={handleSubmit(login)}>
                <h1 className="">Login</h1>
                {!!authError && <label>
                    <span className="error">{authError}</span></label>}

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
                <Button type="submit" title="Login" />
                <button className='text-btn' type='button' onClick={onRegister}>Register</button>
            </form>
        </div>
    )
}

export default Login
