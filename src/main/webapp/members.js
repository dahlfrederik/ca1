             let getAllMembersBtn = document.getElementById("loadMembers");
             
             getAllMembersBtn.addEventListener('click', (event) => {
                 event.preventDefault();
                 fetchAllMembers();
             })
             
             function fetchAllMembers(){
                 
                 let url = 'https://dachma.dk/ca1/api/groupmembers/all';
                 let allMembers = document.getElementById("allMembers");
                 
                 fetch(url)
                         .then(res => res.json())
                         .then(data => {
                             let newArray = data.map(x => `<tr><td>${x.name}</td><td>${x.id}</td><td>${x.favTvShow}</td></tr>`);
                             allMembers.innerHTML =
                                     `<table class="table table-bordered">
                                        <thead><th>Name</th><th>ID</th><th>Favorite TV-show</th></thead>
                                        ${newArray.join("")}
                                    </table>`;
                 });
             }

