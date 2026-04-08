import { useState } from "react";

function NoteForm({ addNote }) {
    const [text, setText] = useState("");
    const [completed, setCompleted] = useState(false);
    const [error, setError] = useState("");

    const handleSubmit = (e) => {
        e.preventDefault();
        if (!text.trim()) {
            setError("Note is required");
            return;
        }

        addNote(text, completed);
        setText("");
        setCompleted(false);
        setError("");
    };

    return (
        <form onSubmit={handleSubmit}>
            <input
                placeholder="Enter note"
                value={text}
                onChange={(e) => {
                    setText(e.target.value);
                    setError("");
                }}
            />

            <label>
                <input
                    type="checkbox"
                    checked={completed}
                    onChange={(e) => setCompleted(e.target.checked)}
                />
                Status
            </label>

            <button>Add</button>
            {error && <p style={{ color: "red" }}>{error}</p>}
        </form>
    );
}

export default NoteForm;