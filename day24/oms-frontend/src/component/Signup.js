import { useState } from "react";
import axios from "axios";

function Signup() {
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");

    const handleSignup = async (e) => {
        e.preventDefault();

        try {
            await axios.post(
                "http://localhost:8080/api/auth/signup",
                {
                    username,
                    email,
                    password,
                    role: ["user"]
                },
                {
                    headers: {
                        "Content-Type": "application/json"
                    }
                }
            );

            setMessage("User registered successfully");
        } catch (err) {
            console.error(err.response?.data);
            setMessage(err.response?.data?.message || "Signup failed");
        }
    };

    return (
        <div className="container">
            <h2>Sign Up</h2>

            <input placeholder="Username" onChange={(e) => setUsername(e.target.value)} />
            <input placeholder="Email" onChange={(e) => setEmail(e.target.value)} />
            <input type="password" placeholder="Password" onChange={(e) => setPassword(e.target.value)} />

            <button onClick={handleSignup}>Sign Up</button>

            {message && <p style={{ color: "red" }}>{message}</p>}
        </div>
    );
}

export default Signup;