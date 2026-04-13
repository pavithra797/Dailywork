import React, { useState } from "react";

function Question({ questions, hideAnswers, onChange, allData }) {

    const [form, setForm] = useState({
        question: "",
        answer: "",
        confirmAnswer: ""
    });

    const [errors, setErrors] = useState({});
    const [touched, setTouched] = useState({});

    const validate = (data) => {
        let err = {};

        if (!data.question) {
            err.question = "Question is required";
        }

        if (!data.answer) {
            err.answer = "Answer is required";
        } else if (!data.answer.trim()) {
            err.answer = "Answer cannot be empty spaces";
        } else if (data.answer.length < 5) {
            err.answer = "Minimum 5 characters";
        } else if (data.answer.length > 20) {
            err.answer = "Maximum 20 characters allowed";
        }

        if (!data.confirmAnswer) {
            err.confirmAnswer = "Confirm answer is required";
        } else if (data.answer !== data.confirmAnswer) {
            err.confirmAnswer = "Answers do not match";
        }

        return err;
    };

    const handleChange = (field, value) => {
        const newForm = { ...form, [field]: value };

        setForm(newForm);

        setTouched({ ...touched, [field]: true });

        const newErrors = validate(newForm);
        setErrors(newErrors);

        if (onChange) {
            onChange(newForm);
        }
    };

    return (
        <div className="row">

            <div className="col">
                <select
                    className="input"
                    value={form.question}
                    onChange={(e) => handleChange("question", e.target.value)}
                    onBlur={() => setTouched({ ...touched, question: true })}
                >
                    <option value="">Please select a Question</option>

                    {(questions || [])
                        .filter((q) => {
                            const selectedQuestions = (allData || [])
                                .map((item) => item?.question)
                                .filter(Boolean);

                            return (
                                !selectedQuestions.includes(q.question) ||
                                q.question === form.question
                            );
                        })
                        .map((q) => (
                            <option key={q.questionId} value={q.question}>
                                {q.question}
                            </option>
                        ))}
                </select>

                {touched.question && errors.question && (
                    <p style={{ color: "red" }}>{errors.question}</p>
                )}
            </div>

            <div className="col">
                <input
                    className="input"
                    type={hideAnswers ? "password" : "text"}
                    placeholder="Answer"
                    value={form.answer}
                    onChange={(e) => handleChange("answer", e.target.value)}
                />
                {touched.answer && errors.answer && (
                    <p style={{ color: "red" }}>{errors.answer}</p>
                )}
            </div>

            <div className="col">
                <input
                    className="input"
                    type={hideAnswers ? "password" : "text"}
                    placeholder="Confirm Answer"
                    value={form.confirmAnswer}
                    maxLength={20}
                    onChange={(e) =>
                        handleChange("confirmAnswer", e.target.value)
                    }
                />

                {touched.confirmAnswer && errors.confirmAnswer && (
                    <p style={{ color: "red" }}>{errors.confirmAnswer}</p>
                )}
            </div>

        </div>
    );
}

export default Question;