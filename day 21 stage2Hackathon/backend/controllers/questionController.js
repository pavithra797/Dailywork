const fs = require("fs");
const path = require("path");

const questions = require("../data/questions.json");

const answersFilePath = path.join(__dirname, "../data/answers.json");

const getQuestions = (req, res) => {
    res.json(questions);
};

const saveAnswers = (req, res) => {
    const newAnswers = req.body;

    let existingAnswers = [];

    if (fs.existsSync(answersFilePath)) {
        const data = fs.readFileSync(answersFilePath);
        existingAnswers = JSON.parse(data);
    }

    existingAnswers.push(newAnswers);

    fs.writeFileSync(
        answersFilePath,
        JSON.stringify(existingAnswers, null, 2)
    );

    res.json({ message: "Answers saved successfully " });
};

module.exports = {
    getQuestions,
    saveAnswers
};