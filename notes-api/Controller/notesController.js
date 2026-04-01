const { request } = require('express');
const service = require('../services/notesServices')

exports.getAllNotes = async (request, response) => {
    const notes = await service.getNotes();

    response.json(notes);
}

exports.getNoteById = async (req, res) => {
    const notes = await service.getNotes();
    const note = notes.find(n => n.id == req.params.id);

    if (!note) return res.status(404).json({ error: 'Not found' });

    res.json(note);
};

exports.createNote = async (req, res) => {
    const { title, content } = req.body;

    if (!title || !content) {
        return res.status(400).json({ error: 'Title and content required' });
    }

    const notes = await service.getNotes();

    const newNote = {
        id: Date.now(),
        title,
        content,
        status: 'created',
        createdAt: new Date().toISOString()
    };

    notes.push(newNote);
    await service.saveNotes(notes);

    res.status(201).json(newNote);
};

exports.deleteNote = async (req, res) => {
    const notes = await service.getNotes();
    const id = Number(req.params.id);

    const noteExists = notes.find(n => n.id === id);
    if (!noteExists) {
        return res.status(404).json({ error: 'Note not found' });
    }

    const filtered = notes.filter(n => n.id !== id);
    await service.saveNotes(filtered);

    res.json({ message: 'Deleted successfully' });
};

exports.updateNote = async (req, res) => {
    const id = Number(req.params.id);
    const { title, content } = req.body;

    const notes = await service.getNotes();
    const noteIndex = notes.findIndex(n => n.id === id);

    if (!title && !content) {
        return res.status(400).json({ error: 'No data provided to update' });
    }
    if (noteIndex === -1) {
        return res.status(404).json({ error: 'Note not found' });
    }
    if (title !== undefined) notes[noteIndex].title = title;
    if (content !== undefined) notes[noteIndex].content = content;

    notes[noteIndex].status = 'updated';

    await service.saveNotes(notes);

    res.status(200).json({ message: 'Updated successfully' });
};