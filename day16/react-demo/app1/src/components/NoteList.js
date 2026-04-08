import NoteItem from "./NoteItem";
import { useNavigate } from "react-router-dom";

function NoteList({ notes, deleteNote, toggleStatus }) {
  const navigate = useNavigate();
  return (
    <div>
      <h1>Notes App</h1>
      <button className="add-note-btn" onClick={() => navigate("/add")}>
        Add Note
      </button>
      <ul>
        {notes.map((note) => (
          <NoteItem
            key={note.id}
            note={note}
            deleteNote={deleteNote}
            toggleStatus={toggleStatus}
          />
        ))}
      </ul>
    </div>
  );
}

export default NoteList;