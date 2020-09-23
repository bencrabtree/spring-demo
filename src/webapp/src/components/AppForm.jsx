import React, { useState, useEffect } from 'react';
import './AppForm.css';

const AppForm = ({ }) => {
    const [ ticker, setTicker ] = useState();

    const onCancel = () => {
        setTicker('');
    }

    const onSubmit = () => {
        // hit api
    }

    const handleChange = event => {
        setTicker(event.target.value);
    }

    return (
        <div className='app-form'>
            <div className='input-label'>
                <h2>Ticker</h2>
            </div>
            <div className='form-input'>
                <input
                    type='text'
                    id='ticker'
                    value={ticker}
                    onChange={handleChange}
                />
            </div>
            <div className='form-actions'>
                <span onClick={onCancel}>Clear</span>
                <span onClick={onSubmit}>Search</span>
            </div>
        </div>
    );
}

export default AppForm;