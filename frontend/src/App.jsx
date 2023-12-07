import React, { useEffect, useState } from 'react'
import Auth from './components/Auth'
import Layout from './components/Layout'


function App() {
  const [loggedIn, setLoggedIn] = useState(false)

  useEffect(() => {
    setLoggedIn(!!localStorage.getItem('token'))
  }, [])

  if (!loggedIn) return <div className="page-container">
    <Auth />
  </div>

  return (
    <Layout></Layout>
  )
}

export default App
