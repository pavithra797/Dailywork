import NoteItem from "./NoteItem";

function NoteList({ notes, deleteNote, toggleStatus }) {
    return (
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
    );
}

export default NoteList;