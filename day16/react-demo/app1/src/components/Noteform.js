import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function Noteform({ addNote }) {
    const [note, setNote] = useState({
        title: "",
        content: "",
        status: "open",
        time: ""
    });

    const [error, setError] = useState("");
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();

        if (!note.title.trim() || !note.content.trim()) {
            setError("Title and Content are required");
            return;
        }
        addNote(note);
        sendPostRequest(note);

        setNote({
            title: "",
            content: "",
            status: "open",
            time: "",
            date: ""
        });

        setError("");
        navigate("/");
        navigate("/list");
    };

    const sendPostRequest = (note) => {
        axios.post("http://localhost:3001/notes", note);
    };

    return (
        <form onSubmit={handleSubmit}>
            <h1>Notes App</h1>

            <input
                placeholder="Enter title"
                value={note.title}
                onChange={(e) =>
                    setNote({ ...note, title: e.target.value })
                }
            />

            <input
                placeholder="Enter content"
                value={note.content}
                onChange={(e) =>
                    setNote({ ...note, content: e.target.value })
                }
            />

            {error && <p className="error">{error}</p>}

            <label>
                <input
                    type="checkbox"
                    checked={note.status === "closed"}
                    onChange={(e) =>
                        setNote({
                            ...note,
                            status: e.target.checked ? "closed" : "open"
                        })
                    }
                />
                Status
            </label>

            <div style={{ display: "flex", gap: "5px" }}>
                <input
                    type="date"
                    value={note.date}
                    onChange={(e) =>
                        setNote({ ...note, date: e.target.value })
                    }
                />
                <input
                    type="time"
                    value={note.time}
                    onChange={(e) =>
                        setNote({ ...note, time: e.target.value })
                    }
                />
            </div>
            <button type="submit">Add</button>
        </form>
    );
}

export default Noteform;