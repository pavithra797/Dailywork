let todos = JSON.parse(localStorage.getItem('todos')) || [];

function saveTodos() {
    localStorage.setItem('todos', JSON.stringify(todos));
}

function addTodo() {
    const input = document.getElementById('todo-input');
    const dateTimeInput = document.getElementById('task-datetime');
    const priorityInput = document.querySelector('input[name="priority"]:checked');
    const errorMsg = document.getElementById('error-msg');

    const text = input.value.trim();
    const dateTime = dateTimeInput.value;

    if (text === '') {
        errorMsg.innerText = "Task name is mandatory";
        input.classList.add('input-error');
        return;
    }
    errorMsg.innerText = "";
    input.classList.remove('input-error');

    todos.push({
        text,
        dateTime,
        priority: priorityInput.value,
        completed: false
    });

    input.value = '';
    dateTimeInput.value = '';

    saveTodos();
    renderTodos();
}

function deleteTodo(index) {
    todos.splice(index, 1);
    saveTodos();
    renderTodos();
}

function toggleCompleted(index) {
    todos[index].completed = !todos[index].completed;
    saveTodos();
    renderTodos();
}

function sortTodos() {
    todos.sort((a, b) => a.text.localeCompare(b.text));
    saveTodos();
    renderTodos();
}

function renderTodos() {
    const list = document.getElementById('todo-list');
    list.innerHTML = '';

    todos.forEach((todo, index) => {
        const li = document.createElement('li');

        const timeDisplay = todo.time ? `${todo.time}` : '';
        const dateTimeDisplay = todo.dateTime
            ? `${new Date(todo.dateTime).toLocaleString()}`
            : '';
        const priorityDisplay = todo.priority === 'urgent' ? 'Urgent' : 'Not Urgent';

        li.innerHTML = `
            <span class="${todo.completed ? 'completed' : ''}">
                ${todo.text}
            </span>
            <div class="right-section">
                <small class="time-text">${timeDisplay}</small>
                <small class="time-text">${dateTimeDisplay}</small>
                <small class="priority-text">${priorityDisplay}</small>
                <button class="complete-btn" onclick="toggleCompleted(${index})">✔</button>
                <button class="delete-btn" onclick="deleteTodo(${index})">✖</button>
            </div>
        `;

        list.appendChild(li);
    });
}

renderTodos();

const input = document.getElementById('todo-input');
const errorMsg = document.getElementById('error-msg');

input.addEventListener('input', () => {
    if (input.value.trim() !== '') {
        errorMsg.innerText = '';
        input.classList.remove('input-error');
    }
});