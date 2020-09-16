let getAllCarsBtn = document.getElementById("loadCars");

getAllCarsBtn.addEventListener('click', (event) => {
    event.preventDefault();
    fetchAllCars();
})

function fetchAllCars() {

    //let url = 'http://localhost:8080/jpareststarter/api/cars/all';
    let url = 'http://dachma.dk/ca1/api/cars/all';
    
    makeCarTable(url); 
}

let getCarsByMakeBtn = document.getElementById("getCarsByMakeBtn");

getCarsByMakeBtn.addEventListener('click', (event) => {
    event.preventDefault();
    fetchCarByMake(document.getElementById("searchByMakeInput").value);
});

function fetchCarByMake(make) {
    //let url = 'http://localhost:8080/jpareststarter/api/cars/bymake/' + make;
    let url = 'http://dachma.dk/ca1//api/cars/bymake/' + make;
    
    makeCarTable(url);
}


function makeCarTable(url){
    let cars = document.getElementById("cars");
    fetch(url)
            .then(res => res.json())
            .then(data => {
                let newArray = data.map(x => `<tr><td>${x.make}</td><td>${x.model}</td><td>${x.year}</td><td>${x.price}</td></tr>`);
                cars.innerHTML =
                        `${newArray.join("")}`;
            });
}



