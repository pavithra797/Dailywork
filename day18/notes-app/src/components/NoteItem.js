function NoteItem({ note, deleteNote }) {
    return (
        <li>
            {note.text} {note.completed ? "Closed" : "Open"}
            <button onClick={() => deleteNote(note.id)}>Delete</button>
        </li>
    );
}

export default NoteItem;