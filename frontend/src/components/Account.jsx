import React, { useEffect, useState } from 'react'
import Button from './Button'
import ExitIcon from './ExitIcon'
import Flag from './Flag'
import { useForm } from 'react-hook-form'
import axios from 'axios'

const formatter = new Intl.NumberFormat('hu-HU', {
    currency: "HUF",
    style: "currency"
})

function formatCurrency(amount) {
    if (!amount) return
    return formatter.format(amount)
}


export default function Transaction({ }) {
    const [currency, setCurrency] = useState({})

    useEffect(() => {
        axios.get('api/user/currency').then(({data}) => setCurrency(data))
    }, [])

    return (
        <div className="">
            <div className="balance-card">
                <div className="currency-type">
                    <div className="flag-container"><Flag /></div>
                    <p className="">Hungarian Forints account</p>
                </div>
                <p><span>Balance:</span> <span>{formatCurrency(currency?.amount)}</span></p>
                <p><span>IBAN:</span> <span>{currency?.iban}</span></p>
            </div>
        </div>
    )
}
