import React, { useState } from 'react'
import Register from './Register';
import Login from './Login';


function Auth({}) {
    const [register, setRegiser] = useState(false);

    if (register) return <Register onLogin={() => setRegiser(false)} />
    return <Login onRegister={() => setRegiser(true)} />
}

export default Auth
