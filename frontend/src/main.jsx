import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import './styles/globals.scss'
import axios from 'axios'

axios.defaults.baseURL = "http://localhost:8080"

axios.interceptors.request.use(function (config) {
  const token = localStorage.getItem("token")
  if (token)
    config.headers.Authorization = `Bearer ${token}`;
  return config;
});

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
)
