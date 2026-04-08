function NoteItem({ note, deleteNote, toggleStatus }) {
    return (
        <li>
            <input
                type="checkbox"
                checked={note.status === "closed"}
                onChange={() => toggleStatus(note.id)}
            />
            <span>
                {note.title} (Status: {note.status}) {note.date} {note.time}
            </span>
            <button onClick={() => deleteNote(note.id)}>Delete</button>
        </li>
    );
}

export default NoteItem;