const loveMe = document.querySelector('.loveMe')
const times = document.querySelector('#times')

let clickTime = 0
let timesCounter = 0

function heartCoordinates(x, y){
    // at first, x, y = coordinates on whole page

    //calcultating the coordinates of the image
    const offset = loveMe.getBoundingClientRect()
    const top = offset.top
    const left = offset.left

    //x, y = coordinates on the div
    x = x - left
    y = y - top
    heartEffect(x, y)
}

function heartEffect(x, y){
    const heart = document.createElement('i')

    loveMe.appendChild(heart)
    heart.classList.add('fas', 'fa-heart')
    heart.style.top = `${y}px`
    heart.style.left = `${x}px`
    setTimeout(() => {
        heart.remove()
    }, 600)
}

function updateCounter(){
    timesCounter++
    times.innerText = timesCounter
}

loveMe.addEventListener('click', (e) => {
    if(clickTime == 0){
        clickTime = new Date().getTime()
    } else{
        if((new Date().getTime() - clickTime) < 800){
            // DoubleClick!
            heartCoordinates(e.clientX, e.clientY)
            updateCounter()
            clickTime = 0
        } else{
            clickTime = new Date().getTime()
        }
    }
})