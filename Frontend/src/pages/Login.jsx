import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const [isLoggedIn, setIsLoggedIn] = useState(false);

    useEffect(() => {
        // Check if the user's JWT token is valid
        const accessToken = localStorage.getItem('accessToken');
        if (accessToken) {
            validateTokenOnServer(accessToken);
        }
    }, []);

    const validateTokenOnServer = async (token) => {
        try {
            const response = await fetch('http://localhost:8080/api/v1/auth/validateToken', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ token: token }),
            });
            if (response.ok) {
                setIsLoggedIn(true);
            } else {
                setIsLoggedIn(false);
            }
        } catch (error) {
            setIsLoggedIn(false);
        }
    };

    const handleLogin = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch('http://localhost:8080/api/v1/auth/authenticate', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    username: username,
                    password: password
                })
            });

            if (response.ok) {
                const data = await response.json();
                // Assuming your API response provides accessToken and refreshToken
                localStorage.setItem('accessToken', data.accessToken);
                localStorage.setItem('refreshToken', data.refreshToken);
                localStorage.setItem('userId', data.userId);
                // Redirect the user or perform other actions upon successful login
                setIsLoggedIn(true);
            } else {
                console.error('Login failed.');
            }
        } catch (error) {
            console.error('Error logging in:', error);
        }
    };

    const handleLogout = async () => {
        try {
            const accessToken = localStorage.getItem('accessToken');
            console.log(accessToken);
            if (accessToken) {
                const response = await fetch('http://localhost:8080/api/v1/auth/logout', {
                    method: 'POST',
                    headers: {
                        Authorization: `Bearer ${accessToken}`,
                    },
                });

                if (response.ok) {
                    localStorage.removeItem('accessToken');
                    localStorage.removeItem('refreshToken');
                    setIsLoggedIn(false);
                } else {
                    console.error('Logout failed.');
                }
            } else {
                console.error('No accessToken found.');
            }
        } catch (error) {
            console.error('Error logging out:', error);
        }
    };

    return (
        <div>
            {isLoggedIn ? (
                <>
                    <h1>You are already logged in!</h1>
                    <button className="coolbutton" id="round" onClick={handleLogout}>Sign Out</button>
                </>
            ) : (
                <>
                    <h1>Log in</h1>
                    <br />
                    <form onSubmit={handleLogin}>
                        <input
                            className="textfield"
                            id="round"
                            type="text"
                            name="usernameField"
                            placeholder="Username"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                        />
                        <br />
                        <br />
                        <input
                            className="textfield"
                            id="round"
                            type="password"
                            name="passwordField"
                            placeholder="Password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                        <br />
                        <br />
                        <button className="coolbutton" id="round" name="loginButton" type="submit">
                            Log in
                        </button>
                    </form>
                    <br />
                    <br />
                    <h2>
                        Don't have an account yet? <Link to="/register">Register here</Link>.
                    </h2>
                </>
            )}
        </div>
    );
};

export default Login;
