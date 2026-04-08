const express = require("express");
const cors = require("cors");

const app = express();

app.use(cors());
app.use(express.json());

const questionRoutes = require("./routes/questionRoutes");

app.use("/questions", questionRoutes);

module.exports = app;

if (process.env.NODE_ENV !== "test") {
  app.listen(3001, () => {
    console.log("Server running on http://localhost:3001");
  });
}