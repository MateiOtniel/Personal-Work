const header = document.getElementById('header')
const title = document.getElementById('title')
const excerpt = document.getElementById('excerpt')
const profile_img = document.getElementById('profile_img')
const name= document.getElementById('name')
const date = document.getElementById('date')

const animated_bgs = document.querySelectorAll('.animated-bg')
const animated_bgs_texts = document.querySelectorAll('.animated-bg-text')

setTimeout(getData, 1700)

function getData(){
    header.innerHTML = '<img src="https://images.unsplash.com/photo-1560707303-4e980ce876ad?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxfDB8MXxyYW5kb218MHx8fHx8fHx8MTY0NTE5MjcwMA&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=1080" alt="">'
    title.innerHTML = 'Lorem ipsum dolor sit amet'
    excerpt.innerHTML = 'Lorem ipsum, dolor sit amet consectetur adipisicing elit. Delectus, deserunt?'
    profile_img.innerHTML = ' <img src="https://randomuser.me/api/portraits/men/45.jpg" alt="">'
    name.innerHTML = 'John Doe'
    date.innerHTML = 'Oct 08, 2020'

    animated_bgs.forEach(bg => bg.classList.remove('animated-bg'))
    animated_bgs_texts.forEach(bg => bg.classList.remove('animated-bg-text'))
}