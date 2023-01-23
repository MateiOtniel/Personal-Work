const colors = ['#FF5733', '#7A3E32', '#29AF38', '#296BAF', '#7A29AF']

function createSquares(){
    const container = document.getElementById('container')
    const squares = 500
    for(let i = 0; i < squares; i++){
        const square = document.createElement('div')
        square.addEventListener('mouseover', () => setColor(square))
        square,addEventListener('mouseout', () => removeColor(square))
        square.classList.add('square')
        container.appendChild(square)
    }
}

function setColor(element){
    const color = getRandomColor()
    element.style.background = color
    element.style.boxShadow = `0 0 2px ${color}, 0 0 2px ${color}`
}

function removeColor(element){
    element.style.background = `#1d1d1d`
    element.style.boxShadow = `0 0 2px #fff`
}

function getRandomColor(){
    return colors[Math.floor(Math.random() * colors.length)]
}

createSquares()