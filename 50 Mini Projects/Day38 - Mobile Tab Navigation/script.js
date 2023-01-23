const images = document.querySelectorAll('.content')
const buttons = document.querySelectorAll('li')

buttons.forEach((button, idx) => {
    button.addEventListener('click', () => {
        hideAllImages()
        hideAllItems()
        showSelectedOne(idx)
    })
})

function hideAllImages(){
    images.forEach(image => {
        image.classList.remove('show')
    })
}

function hideAllItems(){
    buttons.forEach(button => {
        button.classList.remove('active')
    })
}

function showSelectedOne(idx){
    images[idx].classList.add('show')
    buttons[idx].classList.add('active')
}