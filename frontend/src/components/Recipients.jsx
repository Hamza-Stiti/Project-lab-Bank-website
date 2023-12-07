import React, { useEffect, useState } from 'react'
import Button from './Button'
import ExitIcon from './ExitIcon'
import { useForm } from 'react-hook-form'
import axios from 'axios'


export default function Transaction({ onSend }) {
    const [recipients, setRecipients] = useState([])
    let event;

    useEffect(() => {
        axios.get('api/user/recipients').then(({ data }) => setRecipients(data))
    }, [])

    return (
        <div style={{ display: 'flex', flexDirection: "column", gap: '2rem' }}>
            <h1 style={{ fontSize: '2rem', fontWeight: 600 }}>Recipients</h1>
            <div style={{ display: 'flex', flexDirection: 'column', gap: '1.5rem' }}>
                {recipients.map(({ id, recipientIBAN }) =>
                    <div key={id} style={{ display: 'grid', alignItems: 'center', gap: '2rem', gridTemplateColumns: '1fr 1fr', background: "#eec65986", padding: "0.5rem 1rem", borderRadius: "1.5rem" }}>
                        <p style={{ margin: "auto" }}>IBAN: {recipientIBAN}</p>
                        <div style={{ margin: "auto" }}>
                            <Button title="Send" onClick={() => {
                                onSend(recipientIBAN)
                            }
                            } />
                        </div>
                    </div>
                )}
            </div>
        </div>
    )
}
