const form = document.getElementById('form')
const input = document.getElementById('input')
const todosUl = document.getElementById('todos')
const date = document.querySelector('.date')
const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];

setDate()
checkTodos()

function checkTodos(){
    const todos = JSON.parse(localStorage.getItem('todos'))
    if(todos){
        todos.forEach(todo => addTodo(todo))
    }
}

form.addEventListener('submit', (e) => {
    e.preventDefault()
    addTodo()
})

function addTodo(todo){
    let todoText = input.value

    if(todo){
        todoText = todo.text
    }
    if(todoText){
        const todoEl = setToDo(todoText, todo)
        todosUl.appendChild(todoEl)
        input.value = ''
        updateLS()
    }
}

function setToDo(todoText, todo){
    const todoEl = document.createElement('li')
    if(todo && todo.completed){
        todoEl.classList.add('completed')
    }
    todoEl.innerText = todoText

    todoEl.addEventListener('click', () => {
        if(todoEl.classList.contains('completed')){
            todoEl.classList.remove('completed')
            todoEl.innerText = todoText.slice(0, todoText.length)
        } else{
            todoEl.classList.add('completed')
            todoEl.innerText += ` ✔`
        }
        updateLS()
    })

    todoEl.addEventListener('contextmenu', (e) => {
        e.preventDefault()
        todoEl.remove()
        updateLS()
    })
    return todoEl
}

function updateLS(){
    const todosEl = document.querySelectorAll('li')
    const todos = []
    todosEl.forEach(todoEl => {
        todos.push({
            text: todoEl.innerText,
            completed: todoEl.classList.contains('completed')
        })
    })
    localStorage.setItem('todos', JSON.stringify(todos))
}

function setDate(){
    let currentDate = new Date()
    date.innerText = `${currentDate.getDay()} ${months[currentDate.getMonth()]} ${currentDate.getFullYear()}`
}