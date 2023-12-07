import React from 'react'


function Button({title, type, onClick}) {
    return (
        <button className="primary-button" type={type || "button"} onClick={onClick}>{title}</button>
    )
}

export default Button
