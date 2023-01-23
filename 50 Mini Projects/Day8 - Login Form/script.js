const labels = document.querySelectorAll('.form-control label')

labels.forEach( label => {
    // se transforma HTML-ul in span-uri
    label.innerHTML = label.innerText //se ia fiecare litera
    // din label
    .split('') //se imparte intr-un array
    .map((letter, idx) => `<span 
    style="transition-delay:${idx * 50}ms">${letter}</span>`) //array of
    // spans
    .join('')
})