import React, { useEffect, useState } from 'react'
import Button from './Button'
import ExitIcon from './ExitIcon'
import { useForm } from 'react-hook-form'
import axios from 'axios'

const formatter = new Intl.NumberFormat('hu-HU', {
    currency: "HUF",
    style: "currency"
})

function format(amount) {
    if (!amount) return
    return formatter.format(amount)
}


export default function Transaction({ iban, amount, onSend }) {
    const {
        register,
        handleSubmit,
        formState: { errors },
        reset,
    } = useForm({ defaultValues: { IBAN: iban, amount } })
    const [authError, setAuthError] = useState()

    const [currency, setCurrency] = useState({})

    useEffect(() => {
        axios.get('api/user/currency').then(({ data }) => setCurrency(data))
    }, [])

    async function send(c) {
        try {
            const { data } = await axios.post("/api/user/send", c);
            setAuthError()
            reset()
            setCurrency(v => ({ ...v, amount: parseFloat(v.amount) - parseFloat(c.amount) }))
            onSend()
        } catch (e) {
            setAuthError(e?.response?.data?.error)
        }
    }




    return (
        <div style={{ display: "grid", placeItems: "center", height: "100%" }}>
            <form className="auth-form-container" onSubmit={handleSubmit(send)}>
                <h1 className="">Send money</h1>
                {!!authError && <label>
                    <span className="error">{authError}</span></label>}

                <label className="">
                    <span>Balance: {format(currency?.amount)}</span>
                </label>

                <label className="">
                    <span>Amount</span>
                    <input type="number" {...register('amount', { required: { message: "Required", value: true } })} max={currency?.amount} />
                    {!!errors.amount && <span className="error">{errors.amount?.message}</span>}
                </label>
                <label className="">
                    <span>IBAN</span>
                    <input type="number" {...register('IBAN', { required: { message: "Required", value: true } })} />
                    {!!errors.IBAN && <span className="error">{errors.IBAN?.message}</span>}
                </label>
                <Button type="submit" title="Send" />
            </form>
        </div>
    )
}
