const resultEL = document.getElementById('result')
const lengthEL = document.getElementById('length')
const uppercaseEL = document.getElementById('uppercase')
const lowercaseEL = document.getElementById('lowercase')
const numberEL = document.getElementById('number')
const symbolEL = document.getElementById('symbol')
const clipboard = document.getElementById('clipboard')
const generateBtn = document.getElementById('generate')

const randomFunc = {
    lower: getRandomLower,
    upper: getRandomUpper,
    number: getRandomNumber,
    symbol: getRandomSymbol
}

clipboard.addEventListener('click', () => {
    const textarea = document.createElement('textarea')
    const password = resultEL.innerText

    if(!password){
        return 
    }

    textarea.value = password
    document.body.appendChild(textarea)
    textarea.select()
    document.execCommand('copy')
    textarea.remove()
    alert('Password copied to clipboard!')
})

generateBtn.addEventListener('click',  () => {
    const length = +lengthEL.value
    const hasLower = lowercaseEL.checked
    const hasUpper = uppercaseEL.checked
    const hasNumber = numberEL.checked
    const hasSymbol = symbolEL.checked

    resultEL.innerText = generatePassword(hasLower, hasUpper, hasNumber, hasSymbol, length)
})

function generatePassword(lower, upper, number, symbol, length){
    let generatedPassword = ''
    const typesCount = lower + upper + number + symbol
    const typesArr = [{lower}, {upper}, {number}, {symbol}].filter(item => Object.values(item)[0])
    
    if(length < 5 || length >20){
        alert("The length should be between 5 and 20!")
        return ''
    }

    if(typesCount == 0){
        return ''
    }
    for(let i = 0; i < length; i++){
        const funcName = Object.keys(typesArr[randomValue(typesArr.length)])[0]
        generatedPassword += randomFunc[funcName]()
    }
    return generatedPassword
}

function getRandomLower(){
    return String.fromCharCode('a'.charCodeAt(0) + randomValue(26))
}

function getRandomUpper(){
    return String.fromCharCode('A'.charCodeAt(0) + randomValue(26))
}

function getRandomNumber(){
    return String.fromCharCode('0'.charCodeAt(0) + randomValue(10))
}

function getRandomSymbol(){
    const symbols = '!@#$%^&*()[]{}=<>/.,'
    return symbols[randomValue(symbols.length)]
}

function randomValue(max){
    return Math.floor(Math.random() * max)
}