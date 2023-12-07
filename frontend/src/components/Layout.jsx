import React, { useEffect, useState } from 'react'
import Button from './Button'
import ExitIcon from './ExitIcon'
import Account from './Account'
import Transaction from './Transaction'
import Send from './Send'
import Recipients from './Recipients'
import { useForm } from 'react-hook-form'
import axios from 'axios'


export default function Layout({ }) {
    const [selectedPage, setSelectedPage] = useState("account");
    const [iban, setIban] = useState();
    const [amount, setAmount] = useState();

    function logout() {
        localStorage.removeItem("token");
        window.location.reload()
    }

    function getPage() {
        switch (selectedPage) {
            case 'account': return <Account />
            case 'transactions': return <Transaction onResend={(iban, amount) => {setIban(iban); setAmount(amount); setSelectedPage('send')}} />
            case 'send': return <Send iban={iban} amount={amount} onSend={() => {setIban(undefined); setAmount(undefined)}} />
            case 'recipients': return <Recipients onSend={iban => {setIban(iban); setSelectedPage('send')}} />
        }
    }

    useEffect(() => {
        setIban()
        setAmount()
    }, [selectedPage])

    return (
        <div className="layout-container">
            <div className="layout">
                <header className="">
                    <h1 className="logo">DigiBank</h1>
                    <button className="logout danger-button" onClick={logout}>
                        <ExitIcon />
                        <span>Logout</span>
                    </button>
                </header>
                <main className="">
                    <aside className="">
                        <Button title="Send" onClick={() => setSelectedPage('send')} />
                        <ul>
                            <li>
                                <button data-active={selectedPage === 'account'} onClick={() => setSelectedPage('account')}>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-house" viewBox="0 0 16 16">
                                        <path d="M8.707 1.5a1 1 0 0 0-1.414 0L.646 8.146a.5.5 0 0 0 .708.708L2 8.207V13.5A1.5 1.5 0 0 0 3.5 15h9a1.5 1.5 0 0 0 1.5-1.5V8.207l.646.647a.5.5 0 0 0 .708-.708L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293zM13 7.207V13.5a.5.5 0 0 1-.5.5h-9a.5.5 0 0 1-.5-.5V7.207l5-5z" />
                                    </svg>
                                    <span>
                                        Account
                                    </span>
                                </button>
                            </li>
                            <li>
                                <button data-active={selectedPage === 'transactions'} onClick={() => setSelectedPage('transactions')}>
                                    <svg stroke="currentColor" fill="currentColor" stroke-width="0" viewBox="0 0 24 24" height="20" width="20" xmlns="http://www.w3.org/2000/svg"><path fill="none" stroke-width="2" d="M2,7 L20,7 M16,2 L21,7 L16,12 M22,17 L4,17 M8,12 L3,17 L8,22"></path></svg>
                                    <span>
                                        Transactions
                                    </span>
                                </button>
                            </li>
                            <li>
                                <button data-active={selectedPage === 'recipients'} onClick={() => setSelectedPage('recipients')}>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-person-fill" viewBox="0 0 16 16">
                                        <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6" />
                                    </svg>
                                    <span>
                                        Recipients
                                    </span>
                                </button>
                            </li>
                        </ul>
                    </aside>

                    <div className="body">
                        {getPage()}
                    </div>
                </main>
            </div>
        </div>
    )
}
