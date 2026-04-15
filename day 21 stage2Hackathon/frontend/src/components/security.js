
function Toggle({ hideAnswers, setHideAnswers }) {
    return (
        <div className="toggle">
            <input
                type="checkbox"
                checked={hideAnswers}
                onChange={() => setHideAnswers(prev => !prev)}
            />
            <label> Hide Answers</label>
        </div>
    );
}

export default Toggle;