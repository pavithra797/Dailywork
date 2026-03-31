const express = require('express');
const app = express();

const notesRoutes = require('./routes/notesRoutes');

app.use(express.json());
app.use('/notes', notesRoutes);

app.listen(3000, () => {
    console.log('Server started');
});

module.exports = app;