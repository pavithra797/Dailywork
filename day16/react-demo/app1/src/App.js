import { useState } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import Noteform from "./components/Noteform";
import NoteList from "./components/NoteList";
import Navbar from "./components/Navbar";

import "./App.css";

function App() {
  const [notes, setNotes] = useState([]);

  const addNote = (note) => {
    const newNote = { ...note, id: Date.now() };
    setNotes((prev) => [...prev, newNote]);
  };

  const deleteNote = (id) => {
    setNotes((prev) => prev.filter((n) => n.id !== id));
  };

  const toggleStatus = (id) => {
    setNotes((prev) =>
      prev.map((note) =>
        note.id === id
          ? {
            ...note,
            status: note.status === "open" ? "closed" : "open",
          }
          : note
      )
    );
  };

  return (
    <Router>
      <div className="container">
        <Navbar />

        <Routes>
          <Route path="/" element={<h2>Notes App</h2>} />
          <Route path="/add" element={<Noteform addNote={addNote} />} />
          <Route
            path="/list"
            element={
              <NoteList
                notes={notes}
                deleteNote={deleteNote}
                toggleStatus={toggleStatus}
              />
            }
          />
        </Routes>
      </div>
    </Router>
  );
}

export default App;