let getAllCarsBtn = document.getElementById("loadCars");

getAllCarsBtn.addEventListener('click', (event) => {
    event.preventDefault();
    fetchAllCars();
})

function fetchAllCars() {

    //let url = 'http://localhost:8080/jpareststarter/api/cars/all';
    let url = 'https://dachma.dk/ca1/api/cars/all';
    
    makeCarTable(url); 
}

let getCarsByMakeBtn = document.getElementById("getCarsByMakeBtn");

getCarsByMakeBtn.addEventListener('click', (event) => {
    event.preventDefault();
    fetchCarByMake(document.getElementById("searchByMakeInput").value);
});

function fetchCarByMake(make) {
    //let url = 'http://localhost:8080/jpareststarter/api/cars/bymake/' + make;
    let url = 'https://dachma.dk/ca1//api/cars/bymake/' + make;
    
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

let getCarsByPriceBtn = document.getElementById("getCarsByPriceBtn"); 

getCarsByPriceBtn.addEventListener('click', (event) => {
    event.preventDefault();
    fetchCarByPrice(document.getElementById("searchByMakeInput").value);
});


function fetchCarByPrice(price){
    //let url = 'http://localhost:8080/jpareststarter/api/cars/byprice/' + price;
    let url = 'https://dachma.dk/ca1//api/cars/byprice/' + price;
    
    makeCarTable(url);
}

let sortCarsBtn = document.getElementById("sortCarsBtn"); 

sortCarsBtn.addEventListener('click', (event) => {
    event.preventDefault();
    sortCars();
});

function sortCars(){
    //let url = 'http://localhost:8080/jpareststarter/api/cars/all';
    let url = 'https://dachma.dk/ca1/api/cars/all';
    
   fetch(url)
            .then(res => res.json())
            .then(data => {
                let newArray = data.map(x => `<tr><td>${x.make}</td><td>${x.model}</td><td>${x.year}</td><td>${x.price}</td></tr>`);
                newArray.sort(); 
                cars.innerHTML =
                        `${newArray.join("")}`;
                
            });
           
}
    

