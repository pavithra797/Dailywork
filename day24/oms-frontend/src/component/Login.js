import { useState } from "react";
import axios from "axios";


function Login({ onLogin }) {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");

    const handleLogin = async (e) => {
        e.preventDefault();

        try {
            const res = await axios.post(
                "http://localhost:8080/api/auth/signin",
                {
                    username,
                    password
                }
            );

            setMessage("Login successful");
            onLogin();

        } catch (err) {
            setMessage("Invalid credentials");
        }
    };

    return (
        <div className="container">
            <h2>Login</h2>

            <input
                type="text"
                placeholder="Username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
            />

            <input
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />

            <button onClick={handleLogin}>Login</button>

            {message && <p>{message}</p>}
        </div>
    );
}
export default Login;