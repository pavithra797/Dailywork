const express = require("express");
const router = express.Router();

const { getQuestions, saveAnswers } = require("../controllers/questionController");

router.post("/answers", saveAnswers);
router.get("/", getQuestions);

module.exports = router;