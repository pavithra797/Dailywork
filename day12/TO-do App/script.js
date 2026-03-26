
let todos = JSON.parse(localStorage.getItem('todos')) || [];

function saveTodos() {
    localStorage.setItem('todos', JSON.stringify(todos));
}

function addTodo() {
    const input = document.getElementById('todo-input');
    const text = input.value.trim();
    if (text === '') return; // Fix: ignore empty input
    todos.push({ text, completed: false });
    input.value = '';
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

function renderTodos() {
    const list = document.getElementById('todo-list');
    list.innerHTML = '';

    todos.forEach((todo, index) => {
        const li = document.createElement('li');
        if (todo.completed) li.classList.add('completed');

        li.innerHTML = `
        <span>${todo.text}</span>
        <div>
        <button class="complete-btn" onclick="toggleCompleted(${index})">✔</button>
        <button class="delete-btn" onclick="deleteTodo(${index})">✖</button>
        </div>
        `;
        list.appendChild(li);
    });
}

renderTodos();