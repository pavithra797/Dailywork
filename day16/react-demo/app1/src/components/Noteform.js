import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function Noteform() {

    const [note, setNote] = useState({
        title: "",
        content: "",
        status: "CREATED",
        date: "",
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

        if (!note.date || !note.time) {
            setError("Date and Time are required");
            return;
        }

        const newNote = {
            title: note.title,
            content: note.content,
            status: note.status,
            created_at: formatDate(note.date, note.time) 
        };

        console.log("Sending:", newNote);

        axios.post("http://localhost:8080/notes", newNote)
            .then(() => navigate("/list"))
            .catch(err => console.error(err));
    };

    const formatDate = (date, time) => {
        return new Date(`${date}T${time}`).toISOString(); 
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
                    checked={note.status === "CLOSED"}
                    onChange={(e) =>
                        setNote({
                            ...note,
                            status: e.target.checked ? "CLOSED" : "CREATED"
                        })
                    }
                />
                Status
            </label>

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

            <button type="submit">Add</button>
        </form>
    );
}

export default Noteform;