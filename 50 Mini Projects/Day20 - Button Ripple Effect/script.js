const buttons = document.querySelectorAll('.ripple')

buttons.forEach(button => {
    button.addEventListener('click', function (e){
        //Getting the position of every click
        const x =  e.clientX
        const y = e.clientY

        const buttonTop = e.target.offsetTop
        const buttonLeft = e.target.offsetLeft
        
        const xInside = x - buttonLeft
        const yInside = y - buttonTop

        const circle = document.createElement('span')
        circle.classList.add('circle')
        circle.style.top = yInside + 'px'
        circle.style.left = xInside + 'px'

        this.appendChild(circle)
        //Remove the circle to clear the Dom
        setTimeout(() => circle.remove(), 500)
    })
})