import React, { useState, useEffect } from "react";
import "./App.css";
import Question from "./components/Questions";
import Toggle from "./components/security";
import axios from "axios";

function App() {
  const [hideAnswers, setHideAnswers] = useState(false);

  const [questions, setQuestions] = useState([]);

  const [allData, setAllData] = useState(
    Array.from({ length: 5 }, () => ({
      question: "",
      answer: "",
      confirmAnswer: ""
    }))
  );

  const [errorMsg, setErrorMsg] = useState("");
  const [successMsg, setSuccessMsg] = useState("");

  useEffect(() => {
    fetch("http://localhost:3001/questions")
      .then((res) => res.json())
      .then((data) => {
        if (Array.isArray(data)) {
          setQuestions(data);
        } else {
          setQuestions(data.questions || []);
        }
      })
      .catch((err) => console.log(err));
  }, []);

  const updateData = (index, data) => {
    const updated = [...allData];
    updated[index] = data;
    setAllData(updated);
  };

  const handleSubmit = async () => {
    setErrorMsg("");
    setSuccessMsg("");

    for (let i = 0; i < allData.length; i++) {
      const q = allData[i];

      if (!q.question || !q.answer || !q.confirmAnswer) {
        setErrorMsg(`Question ${i + 1} is required`);
        return;
      }

      if (q.answer !== q.confirmAnswer) {
        setErrorMsg(`Answers do not match in Question ${i + 1}`);
        return;
      }
    }

    try {
      const res = await axios.post(
        "http://localhost:3001/questions/answers",
        allData
      );

      console.log(res.data);

      setSuccessMsg("Saved successfully ");

    } catch (error) {
      console.log(error);
      setErrorMsg("Something went wrong ");
    }
  };

  return (
    <div className="container">
      <div className="card">
        <h3>Security Questions</h3>

        {errorMsg && <p className="error">{errorMsg}</p>}
        {successMsg && <p className="success">{successMsg}</p>}

        {allData.map((_, index) => (
          <Question
            key={index}
            questions={questions}
            hideAnswers={hideAnswers}
            onChange={(data) => updateData(index, data)}
          />
        ))}

        <Toggle
          hideAnswers={hideAnswers}
          setHideAnswers={setHideAnswers}
        />

        <button className="submit-btn" onClick={handleSubmit}>
          Update
        </button>
      </div>
    </div>
  );
}

export default App;