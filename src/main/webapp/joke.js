// Buttons
let getAllJokesBtn = document.getElementById("loadAllJokes");
let getRandomJokesBtn = document.getElementById("loadRandomJoke")
let findJokeBtn = document.getElementById("findJoke");


// event listeners
getAllJokesBtn.addEventListener('click', (event) => {
    event.preventDefault();
    fetchAllJokes();
})

getRandomJokesBtn.addEventListener('click', (event) => {
    event.preventDefault();
    fetchRandomJoke();
})

findJokeBtn.addEventListener('click', (event) => {
    event.preventDefault();
    let jokeIDTextInput = document.getElementById("jokeID");
    fetchJokeById(jokeIDTextInput.value);
});

// Functions
function fetchAllJokes() {
    let url = 'http://localhost:8080/jpareststarter/api/joke/all';
    let allJokes = document.getElementById("allJokes");

    fetch(url)
            .then(res => res.json())
            .then(data => {
                let newArray = data.map(x => `<tr><td>${x.theJoke}</td><td>${x.reference}</td><td>${x.type}</td></tr>`);
                allJokes.innerHTML =
                        `${newArray.join("")}`
            });
}

function fetchJokeById(id) {
    let url = 'http://localhost:8080/jpareststarter/api/joke/id/' + id;
    fetch(url)
            .then(res => res.json()) 
            .then(data => {
                let allJokes = document.getElementById("allJokes");
                allJokes.innerHTML = renderObjectToHTML(data);
            });
}


// fetches a random joke and calls renderObjecToHTML
function fetchRandomJoke() {
    let url = 'http://localhost:8080/jpareststarter/api/joke/randomJoke';
    fetch(url)
            .then(res => res.json())
            .then(data => {
                let allJokes = document.getElementById("allJokes");
                allJokes.innerHTML = renderObjectToHTML(data);
            });
}

function renderObjectToHTML(x) {
    result = `<tr><td>${x.theJoke}</td><td>${x.reference}</td><td>${x.type}</td></tr>`
    return result;
}


