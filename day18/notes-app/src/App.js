import { useState } from "react";
import NoteForm from "./components/NoteForm";
import NoteList from "./components/NoteList";

function App() {
  const [notes, setNotes] = useState([]);

  const addNote = (text, completed) => {
    const newNote = { id: Date.now(), text, completed };
    setNotes([...notes, newNote]);
  };

  const deleteNote = (id) => {
    setNotes(notes.filter((n) => n.id !== id));
  };

  const toggleStatus = (id) => {
    setNotes(
      notes.map((note) =>
        note.id === id
          ? { ...note, completed: !note.completed }
          : note
      )
    );
  };

  return (
    <div>
      <h1>Notes App</h1>
      <NoteForm addNote={addNote} />
      <NoteList
        notes={notes}
        deleteNote={deleteNote}
        toggleStatus={toggleStatus}
      />
    </div>
  );
}

export default App;