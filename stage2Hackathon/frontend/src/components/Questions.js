import React, { useState } from "react";

function Question({ questions, hideAnswers, onChange }) {
    const [form, setForm] = useState({
        question: "",
        answer: "",
        confirmAnswer: ""
    });

    const [errors, setErrors] = useState({});

    const validate = (data) => {
        let err = {};

        if (!data.question) {
            err.question = "Answer is Required";
        }

        if (!data.answer) {
            err.answer = "Answer is Required";
        } else if (data.answer.length < 5) {
            err.answer = "Min 5 chars";
        }

        if (data.confirmAnswer && data.answer !== data.confirmAnswer) {
            err.confirmAnswer = "Not match";
        }

        setErrors(err);
    };

    const handleChange = (field, value) => {
        const updated = { ...form, [field]: value };
        setForm(updated);
        validate(updated);
        onChange(updated);
    };

    return (
        <div className="row">

            <div className="col">
                <select
                    className="input"
                    value={form.question}
                    onChange={(e) => handleChange("question", e.target.value)}
                >
                    <option value="">Select Question</option>

                    {Array.isArray(questions) &&
                        questions.map((q) => (
                            <option key={q.questionId} value={q.question}>
                                {q.question}
                            </option>
                        ))}
                </select>

                {errors.question && <p className="error">{errors.question}</p>}
            </div>

            <div className="col">
                <input
                    className="input"
                    type={hideAnswers ? "password" : "text"}
                    placeholder="Answer"
                    value={form.answer}
                    onChange={(e) => handleChange("answer", e.target.value)}
                />
                {errors.answer && <p className="error">{errors.answer}</p>}
            </div>

            <div className="col">
                <input
                    className="input"
                    type={hideAnswers ? "password" : "text"}
                    placeholder="Confirm Answer"
                    value={form.confirmAnswer}
                    onChange={(e) => handleChange("confirmAnswer", e.target.value)}
                />
                {errors.confirmAnswer && (
                    <p className="error">{errors.confirmAnswer}</p>
                )}
            </div>

        </div>
    );
}

export default Question;