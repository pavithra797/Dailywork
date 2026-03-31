let todos = JSON.parse(localStorage.getItem('todos')) || [];

function saveTodos() {
    localStorage.setItem('todos', JSON.stringify(todos));
}

function addTodo() {
    const input = document.getElementById('todo-input');
    const timeInput = document.getElementById('task-datetime'); 
    const priorityInput = document.getElementById('task-priority');

    const text = input.value.trim();
    const time = timeInput.value;
    const priority = priorityInput.value;

    if (text === '') {
        document.getElementById('error-msg').innerText = "Task cannot be empty!";
        input.classList.add('input-error');
        return;
    }

    todos.push({
        text,
        time,
        priority,
        completed: false,
        createdAt: Date.now(),
        completedAt: null
    });

    input.value = '';
    timeInput.value = '';
    priorityInput.value = 'Low';

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
        const priorityClass = todo.priority.toLowerCase();
        const priorityText = `<span class="${priorityClass}">(${todo.priority})</span>`;

        const timeDisplay = todo.time
            ? new Date(todo.time).toLocaleString()
            : '';
            li.innerHTML = `
            <span class="${todo.completed ? 'completed' : ''}">
            ${todo.text} ${priorityText}
            </span>
            <div class="right-section">
            <small class="time-text">${timeDisplay}</small>
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