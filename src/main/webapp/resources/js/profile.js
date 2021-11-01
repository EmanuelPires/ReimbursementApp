getProfile();

//console.log(sessionStorage.getItem("employee").toString);

console.log("hello from javascript home file");

let table = document.getElementById("reimTableBody");

async function getProfile() {
  console.log("running getreimbursement function part 3");
  let res = await fetch(
    "http://localhost:8080/ReimbursementApp/api/profile"
  );

  let data = await res.json();
  console.log("this is the data coming back " + data);
  populateTable(data);
}

let user = JSON.parse(sessionStorage.getItem("employee"));
console.log(user);
console.log("This is the user " + user.firstName);
console.log("User ID " + user.user_id);

document.getElementById("welcome").innerHTML = "Welcome " + user.firstName;

function populateTable(data) {
  



    console.log("This is the empID " + data.id);
    let row = document.createElement("tr");
    row.innerHTML = `<th scope="row">${data.id}</th>
              <td>${data.firstName}</td>
              <td>${data.lastName}</td>
			  <td>${data.email}</td>
              <td>${data.username}</td>`;
    table.append(row);
  
}


document.getElementById("proUpdate").addEventListener("click", goToUpdate);

function goToUpdate(){
	location.href="../html/proUpdate.html"
}