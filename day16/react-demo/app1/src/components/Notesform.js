export default function Notesform() {
    const save =function save(event) {
        console.log(event);
    
    }
    const update = (event)=>{
        console.log(event.target.value);
    }
        return (<div>
        <h6>Add Note</h6>
        <input onChange={update} />
        <button onClick={save}>save</button>
    </div>
    )

}