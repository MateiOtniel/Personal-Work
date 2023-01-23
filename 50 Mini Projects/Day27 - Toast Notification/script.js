const button = document.getElementById('button')
const toasts = document.getElementById('toasts')

const messages = [
    'Message One',
    'Message Two',
    'Message Three',
    'Message Four',
    'Message Five'
]

const messageTypes = [
    'succes',
    'error',
    'info'
]

button.addEventListener('click', () => createNotification())

function createNotification(){
    //create notification
    var notification = document.createElement("div")

    //customize it
    customizeNotification(notification)

    //use it
    useNotification(notification)

}

function customizeNotification(notification){
    notification.classList.add('toast')
    notification.classList.add(messageTypes[getRandomNumber(messageTypes.length)])
    notification.innerHTML = `${messages[getRandomNumber(messages.length)]}`
}

function getRandomNumber(max){
    return Math.floor(Math.random() * max)
}

function useNotification(notification){
    //Add the notification for 2.5s and then remove it
    toasts.appendChild(notification)
    setTimeout( () => {
        toasts.removeChild(notification)
    }, 2500)
}