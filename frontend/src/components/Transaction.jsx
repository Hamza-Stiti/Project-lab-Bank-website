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

export default function Transaction({ onResend }) {
    const [transactions, setTransactions] = useState([])
    let event;

    useEffect(() => {
        axios.get('api/user/transactions').then(({ data }) => setTransactions(data))
    }, [])

    return (
        <div style={{ display: 'flex', flexDirection: "column", gap: '2rem' }}>
            <h1 style={{ fontSize: '2rem', fontWeight: 600 }}>Transactions</h1>
            <div style={{ display: 'flex', flexDirection: 'column', gap: '1.5rem' }}>
                {transactions.map(({ id, recipientIBAN, amount, date }) =>
                    <div key={id} style={{ display: 'grid', alignItems: 'center', gap: '2rem', gridTemplateColumns: 'repeat(4, 1fr)', background: "#eec65986", padding: "0.5rem 1rem", borderRadius: "1.5rem" }}>
                        <p>IBAN: {recipientIBAN}</p>
                        <p>Date: {date}</p>
                        <p>Amount: {format(amount)}</p>
                        <div style={{ marginLeft: "auto" }}>
                            <Button title="Resend" onClick={() => {
                                onResend(recipientIBAN, amount)
                            }
                            } />
                        </div>
                    </div>
                )}
            </div>
        </div>
    )
}
