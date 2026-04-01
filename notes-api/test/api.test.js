
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

it("Test for POST /notes with no title", async () => {
    const response = await request(app)
        .post("/notes")
        .send({ content: "Content" });

    expect(response.status).to.equal(400);
});

it("Test for POST /notes with no content", async () => {
    const response = await request(app)
    .post("/notes")
    .send({ title: "Title" });

    expect(response.status).to.equal(400);
});

it("Test for POST /notes with neither title nor content", async () => {
    const response = await request(app)
    .post("/notes")
    .send({});
    expect(response.status).to.equal(400);
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

it('test successful deletetion', async () => {
    const saveResponse = await request(app)
        .post('/notes/')
        .send({ 'title': 'pavithra', 'content': 'Testing sucessful Deletion.' });
    const response = await request(app).delete('/notes/' + saveResponse.body.id);
    expect(response.status).to.equal(200);
});

it('should create and update a note successfully', async () => {
    const createRes = await request(app)
        .post('/notes')
        .send({ title: 'New Title', content: 'New Content' });
    const noteId = createRes.body.id;

    const updateRes = await request(app)
    .put(`/notes/${noteId}`)
    .send({ title: 'Updated Title', content: 'Updated Content' });
   
});

it('should return 400 for empty body', async () => {
    const res = await request(app)
        .put('/notes/1')
        .send({});
    expect(res.status).to.equal(400);
    expect(res.body).to.have.property('error');
});

it('should return 404 for non-existent note', async () => {
    const res = await request(app)
        .put('/notes/999999') 
        .send({ title: 'New Title' });

    expect(res.status).to.equal(404);
    expect(res.body).to.have.property('error');
    expect(res.body.error).to.equal('Note not found');
});

it('should have createdAt field when note is created', async () => {
    const res = await request(app)
        .post('/notes')
        .send({ title: 'Test', content: 'Content' });

    expect(res.status).to.equal(201);
    expect(res.body).to.have.property('createdAt');
});

it('should have createdAt as a string', async () => {
    const res = await request(app)
        .post('/notes')
        .send({ title: 'Test', content: 'Content' });

    expect(res.body.createdAt).to.be.a('string');
});

it('should have valid date format in createdAt', async () => {
    const res = await request(app)
        .post('/notes')
        .send({ title: 'Test', content: 'Content' });

    const date = new Date(res.body.createdAt);

    expect(date.toString()).to.not.equal('Invalid Date');
});