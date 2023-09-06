import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';

const Register = () => {
    const [name, setName] = useState('');
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const [isRegistered, setIsRegistered] = useState(false);
    
    useEffect(() => {
        // Check if the user's JWT token is valid
        const accessToken = localStorage.getItem('accessToken');
        console.log(localStorage.getItem('accessToken'));
        if (accessToken) {
            validateTokenOnServer(accessToken);
        }
    }, []);

    const validateTokenOnServer = async (e, token) => {
        e.preventDefault();
        try {
            const response = await fetch('http://localhost:8080/api/v1/auth/validateToken', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ token: token }),
            });
    
            if (response.ok) {
                setIsRegistered(true);
            }
        } catch (error) {
            setIsRegistered(false);
        }
    };

    const handleRegistration = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch('http://localhost:8080/api/v1/auth/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    name: name,
                    username: username,
                    email: email,
                    password: password
                })
            });

            if (response.ok) {
                setIsRegistered(true);
                const data = await response.json();
                // Assuming your API response provides accessToken and refreshToken
                localStorage.setItem('accessToken', data.accessToken);
                localStorage.setItem('refreshToken', data.refreshToken);
                localStorage.setItem('userId', data.userId);
                // Redirect the user or perform other actions upon successful registration
            } else {
                // Handle registration error
                console.error('Registration failed.');
            }
        } catch (error) {
            setIsRegistered(false);
            console.error('Error registering:', error);
        }
    };

    return (
        <div>
            {isRegistered ? (
                <>
                    <h1>Registration successful!</h1>
                    <br />
                </>
            ) : (
                <>
                    <h1>Register</h1>
                    <br />
                    <ul>
                        <li>
                            <input
                                type='text'
                                id='round'
                                className='textfield'
                                placeholder='Name'
                                value={name}
                                onChange={event => setName(event.target.value)}
                            />
                            <br />
                            <br />
                        </li>
                        <li>
                            <input
                                type='text'
                                id='round'
                                className='textfield'
                                placeholder='Username'
                                value={username}
                                onChange={event => setUsername(event.target.value)}
                            />
                            <br />
                            <br />
                        </li>
                        <li>
                            <input
                                type='text'
                                id='round'
                                className='textfield'
                                placeholder='Email (ex. xyz@email.com)'
                                value={email}
                                onChange={event => setEmail(event.target.value)}
                            />
                            <br />
                            <br />
                        </li>
                        <li>
                            <input
                                type='password'
                                id='round'
                                className='textfield'
                                placeholder='Password'
                                value={password}
                                onChange={event => setPassword(event.target.value)}
                            />
                            <br />
                            <br />
                        </li>
                    </ul>
                    <br />
                    <br />
                    <button
                        className='coolbutton'
                        id='round'
                        name='createAccount'
                        onClick={handleRegistration}
                    >
                        Confirm
                    </button>
                    <br />
                    <br />
                    <h2>
                        Already have an account? <Link to='/login'>Sign in</Link>.
                    </h2>
                </>
            )}
        </div>
    );
};

export default Register;
