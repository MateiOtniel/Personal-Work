const poke_container = document.getElementById('poke-container')
const pokemon_count = 100
const colors = {
    fire: '#FDDFDF',
    grass: '#DEFDE0',
	electric: '#FCF7DE',
	water: '#DEF3FD',
	ground: '#f4e7da',
	rock: '#d5d5d4',
	fairy: '#fceaff',
	poison: '#98d7a5',
	bug: '#f8d5a3',
	dragon: '#97b3e6',
	psychic: '#eaeda1',
	flying: '#F5F5F5',
	fighting: '#E6E0D4',
	normal: '#F5F5F5'
}

const main_types = Object.keys(colors)
console.log(main_types);

const fetchPokemons = async () => {
    for(let i = 1; i <= pokemon_count; i++){
        await getPokemon(i)
    }
}

const getPokemon = async (id) =>{
    const url = `https://pokeapi.co/api/v2/pokemon/${id}`
    const res = await fetch(url)
    const data = await res.json()
    createCard(data)
}

function createCard(pokemon){
    const card = document.createElement('div')
    card.classList.add('pokemon')

    const id = pokemon.id.toString().padStart(3, '0')
    const poke_types = pokemon.types.map(type => type.type.name)
    const type = main_types.find(type => poke_types.indexOf(type) > -1)
    const color = colors[type]

    card.style.backgroundColor = color

    //can't get the png from the api anymore :(
    card.innerHTML = `
            <div class="img-container">
                <img src="https://images.unsplash.com/photo-1643725173053-ed68676f1878?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxfDB8MXxyYW5kb218MHx8fHx8fHx8MTY0NDA1OTY1Nw&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=1080" alt="">
            </div>
            <div class="info">
                <span class="number">#${id}</span>
                <h3 class="name">${pokemon.name[0].toUpperCase() + pokemon.name.slice(1, pokemon.name.length)}</h3>
                <small class="type">Type: <span>${type}</span></small>
            </div>
    `
    poke_container.appendChild(card)
}

fetchPokemons()