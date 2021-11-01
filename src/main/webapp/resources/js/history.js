getReimbursements();

//console.log(sessionStorage.getItem("employee").toString);

console.log("hello from javascript home file");

let table = document.getElementById("reimTableBody");

async function getReimbursements() {
  console.log("running getreimbursement function part 3");
  let res = await fetch(
    "http://localhost:8080/ReimbursementApp/api/reimbursementhistory"
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
  for (reims of data) {
    let status = reims.reimStatus;

    if (status == 1) {
      status = "Unapproved";
    } else if (status == 2) {
      status = "Approved";
    } else if (status == 3) {
      status = "Denied";
    }
    console.log("This is the amount " + reims.description);
    let row = document.createElement("tr");
    row.innerHTML = `<th scope="row">${reims.reimId}</th>
              <td>${new Date(reims.subTimeStamp).toDateString()}</td>
              <td>${reims.amount}</td>
			  <td>${reims.description}</td>
              <td>${status}</td>`;
    table.append(row);
  }
}
