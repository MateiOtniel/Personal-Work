const input = document.getElementById('password')
const bg = document.getElementById('background')

input.addEventListener('input', (e) => {
    const length = e.target.value.length //const length = input.value.length
    bg.style.filter = `blur(${Math.max(20 - length * 2, 0)}px)`
})