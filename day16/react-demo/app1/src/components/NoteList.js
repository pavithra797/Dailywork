import NoteItem from "./NoteItem";
import { useNavigate } from "react-router-dom";

function NoteList({ notes }) {
    const navigate = useNavigate();

    return (
        <div>
            <h1>Notes App</h1>

            <button onClick={() => navigate("/add")}>
                Add Note
            </button>

            <ul>
                {notes.length === 0 ? (
                    <p>No notes found</p>
                ) : (
                    notes.map((note) => (
                        <NoteItem
                            key={note.id}
                            note={note}
                        />
                    ))
                )}
            </ul>
        </div>
    );
}

export default NoteList;