const fs = require('fs').promises;

async function getNotes(){
    const data = await fs.readFile('./data/notes.json','utf-8')
    return JSON.parse(data);
}
async function saveNotes(notes) {
    await fs.writeFile('./data/notes.json', JSON.stringify(notes, null, 2));
}
module.exports = {getNotes,saveNotes};