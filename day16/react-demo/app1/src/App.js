import { useState, useEffect } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import axios from "axios";

import Noteform from "./components/Noteform";
import NoteList from "./components/NoteList";
import Navbar from "./components/Navbar";

import "./App.css";

function App() {
  const [notes, setNotes] = useState([]);

  const fetchNotes = () => {
    axios.get("http://localhost:8080/notes")
      .then(res => {
        console.log("Fetched:", res.data);
        setNotes(res.data);
      })
      .catch(err => console.error(err));
  };

  useEffect(() => {
    fetchNotes();
  }, []);

  return (
    <Router>
      <div className="container">
        <Navbar />

        <Routes>
          <Route path="/" element={<h2>Notes App</h2>} />

          <Route path="/add" element={<Noteform />} />

          <Route
            path="/list"
            element={
              <NoteList
                notes={notes}
              />
            }
          />
        </Routes>
      </div>
    </Router>
  );
}

export default App;