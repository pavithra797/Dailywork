function NoteItem({ note }) {

    const formatDate = (date) => {
        if (!date) return "";
        return new Date(date).toLocaleString("en-IN", {
            dateStyle: "medium",
            timeStyle: "short"
        });
    };

    return (
        <li>
            <span>
                <strong>{note.title}</strong> <br />
                {note.content} <br />
                Status: {note.status} <br />
                {formatDate(note.created_at)} 
            </span>
        </li>
    );
}

export default NoteItem;