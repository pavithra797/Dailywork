const express = require('express');
const app = express();
const cors = require('cors');

const notesRoutes = require('./routes/notesRoutes');
console.log('error', notesRoutes);
const corsOptions = {
    origin: "http://localhost:3000",
    methods: ["GET", "POST", "PUT", "DELETE"]
};

app.use(cors(corsOptions));
app.use(express.json());
app.use('/notes', notesRoutes);
app.listen(3001, ()=>{
    console.log("Server Started at port 3001");
});

module.exports = app;