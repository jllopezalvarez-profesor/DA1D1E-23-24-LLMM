const firstPokemonPage = 'https://pokeapi.co/api/v2/pokemon'

let pokemonList = []
let totalNumberOfPokemon = 0
let nextPokemonPage = firstPokemonPage
let previousPokemonPage = firstPokemonPage

async function getNextPokemonPage() {
    if (nextPokemonPage)
        await getPokemonPage(nextPokemonPage)
}


async function getPreviousPokemonPage() {
    if (previousPokemonPage)
        await getPokemonPage(previousPokemonPage)
}

async function getPokemonPage(pokemonPageUrl) {
    const response = await fetch(pokemonPageUrl)
    // console.log(response)
    const responseContent = await response.json()
    // console.log(responseContent)
    pokemonList = responseContent.results;
    previousPokemonPage = responseContent.previous;
    nextPokemonPage = responseContent.next;
}



async function getPokemonDetails(pokemonDetailsUrl) {
    const response = await fetch(pokemonDetailsUrl)
    // console.log(response)
    const responseContent = await response.json()
    // console.log(responseContent)
    return responseContent
}



async function updatePokemonList(pokemonsInfo) {
    // Buscamos el elemento ul donde vamos a mostrar los poquemon
    const ulElement = document.getElementById('pokemon-list')
    // Si no lo ha encontrado, error
    if (!ulElement) {
        console.log('No se ha encontrado el elemento UL')
        return;
    }
    // Lo vaciamos, por si reusamos el método.
    ulElement.innerHTML = '';
    // Para cada elemento en el resultado creamos un elemento para el pokemon
    pokemonList.forEach(async pokemon => {
        const pokemonDetails = await getPokemonDetails(pokemon.url);
        // console.log(pokemonDetails)
        const liElement = document.createElement('li')
        liElement.innerHTML = `<img src="${pokemonDetails.sprites.front_default}" alt="${pokemonDetails.name}" data-sound-url="${pokemonDetails.cries.latest}" >`
        if (pokemonDetails.cries && pokemonDetails.cries.latest) {
            liElement.addEventListener('click', playPokemonSound)
        }
        ulElement.appendChild(liElement);
    });

}

function playPokemonSound(event) {
    const soundUrl = event.target.getAttribute('data-sound-url');
    var audio = new Audio(soundUrl);
    audio.play();
}

// Asociar la función al evento clic de botones
document.getElementById('btn-prev-page').addEventListener('click', async () => {
    await getPreviousPokemonPage();
    await updatePokemonList();
});

document.getElementById('btn-next-page').addEventListener('click', async () => {
    await getNextPokemonPage();
    await updatePokemonList();
});

window.addEventListener('load', async () => {
    await getNextPokemonPage()
    await updatePokemonList();
})



