import React from 'react';
import AppForm from './AppForm';
import './App.css';

const App = ({}) => {
    return (
        <div className='main-app'>
            <div className='app-header'>
                <h1>Enter A Ticker to Lookup Company</h1>
            </div>
            <div className='form-container'>
                <AppForm />
            </div>
        </div>
    )
}

export default App;
