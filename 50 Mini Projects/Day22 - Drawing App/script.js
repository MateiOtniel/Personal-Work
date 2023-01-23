const canvas = document.getElementById('canvas')
const increaseBtn = document.getElementById('increase')
const decreaseBtn = document.getElementById('decrease')
const sizeEl = document.getElementById('size')
const colorEl = document.getElementById('color')
const clearEl = document.getElementById('clear')
const context = canvas.getContext('2d')

let size = 10
let isPressed = false
let color = 'black'
let x, y

//when mouse is pressed
canvas.addEventListener('mousedown', (event) =>{
    isPressed = true
    x = event.offsetX
    y = event.offsetY
})

//when mouse is released
canvas.addEventListener('mouseup', (event) =>{
    isPressed = false
    x = undefined
    y = undefined
})

//when mouse is released
canvas.addEventListener('mousemove', (event) =>{
    if(isPressed){
        const x2 = event.offsetX
        const y2 = event.offsetY

        drawCircle(x2, y2)
        drawLine(x, y, x2, y2)

        x = x2
        y = y2
    }
})

function drawCircle(x, y){
    context.beginPath()
    context.arc(x, y, size, 0, Math.PI * 2,)
    context.fillStyle = color
    context.fill()
}

function drawLine(x1, y1, x2, y2){
    context.beginPath()
    context.moveTo(x1, y1)
    context.lineTo(x2, y2)
    context.strokeStyle = color
    context.lineWidth = size * 2
    context.stroke()
}

increaseBtn.addEventListener('click', () => {
    if(size < 50){
        size += 2
        sizeEl.innerHTML = `${size}`
    }
})

decreaseBtn.addEventListener('click', () => {
    if(size > 2){
        size -= 2
        sizeEl.innerHTML = `${size}`
    }
})

clearEl.addEventListener('click', () =>  {
    context.clearRect(0, 0, canvas.width, canvas. height)
})

colorEl.addEventListener('change', (event) =>
    color = event.target.value)

// drawCircle(25, 25)
// drawLine(300, 300, 600, 600)

