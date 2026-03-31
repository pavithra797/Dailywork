
const { describe, it } = require('mocha');
const { expect } = require('chai');
const request = require('supertest');
const app = require('../Server');

describe('GET /notes', () => {
    it('should return all notes', async () => {
        const res = await request(app).get('/notes');

        expect(res.status).to.equal(200);
        expect(res.body).to.be.an('array');
    });
});

it('should return 404 for non existent noteID ', async () => {
    console.log(request);

    const res = await request(app).get('/notes/123');
    expect(res.status).to.equal(404);
});

it('test create new note', async () => {
    const res = await request(app)
        .post('/notes')
        .send({ title: 'task1', content: 'this is a note' });

    expect(res.status).to.equal(201);

});

it('should return 400 if title or content is missing', async () => {
    const res = await request(app)
        .post('/notes')
        .send({});

    expect(res.status).to.equal(400);
    expect(res.body).to.have.property('error');
    expect(res.body.error).to.equal('Title and content required');
});

it('should delete an existing note successfully', async () => {
    const createRes = await request(app)
        .post('/notes')
        .send({ title: 'Note to delete', content: 'This note will be deleted' });

    const noteId = createRes.body.id;

    const deleteRes = await request(app).delete(`/notes/${noteId}`);

    expect(deleteRes.status).to.equal(200);
    expect(deleteRes.body).to.have.property('message');
    expect(deleteRes.body.message).to.equal('Deleted successfully');
});

it('should return 404 for a non-existent note ID', async () => {
    const res = await request(app).delete('/notes/999999999');

    expect(res.status).to.equal(404);
    expect(res.body).to.have.property('error');
    expect(res.body.error).to.equal('Note not found');
});