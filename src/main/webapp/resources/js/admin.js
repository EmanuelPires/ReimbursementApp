

getReimbursements();

//console.log(sessionStorage.getItem("employee").toString);

console.log("hello from javascript Manager file");

let table = document.getElementById("reimTableBody");

async function getReimbursements() {
  console.log("running getreimbursement function part 3");
  let res = await fetch(
    "http://localhost:8080/ReimbursementApp/api/managerapprovals"
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
    console.log("This is the amount " + reims.description);
    let row = document.createElement("tr");
    row.innerHTML = `<th scope="row">${reims.reimId}</th>
              <td>${new Date(reims.subTimeStamp).toDateString()}</td>
              <td>${reims.amount}</td>
			  <td>${reims.description}</td>
              <td><button value=${reims.reimId} id=${reims.reimId} onclick="approveReq(event)">Approve</button></td>
              <td><button value=${reims.reimId} onclick="denyReq(event)">Deny</button></td>`;
    table.append(row);
  }
}

//document.getElementsByClassName("approve").forEach().addEventListener("click", approveReq);



//document.querySelector(".approve").addEventListener("click", approveReq);
//let deny = document.getElementsByClassName("deny");
//approve.addEventListener("click", approveReq);
//deny.addEventListener("click", denyReq);


function approveReq(e){
	let reimId=e.target.value;
	
	let reimApp={
		reimId
	}
	try{
		let req = fetch("http://localhost:8080/ReimbursementApp/api/approve", {
			method: "POST",
			headers:{
				"Content-Type": "application/json",
			},
			body: JSON.stringify(reimApp),
		});
		
	}catch(e){
		console.log(e);
	}
	//debugger;
	location.href="../html/admin.html"
}






 function denyReq(e) {
	let reimId=e.target.value;
	
	let reimApp={
		reimId
	}
	try{
		let req = fetch("http://localhost:8080/ReimbursementApp/api/deny", {
			method: "POST",
			headers:{
				"Content-Type": "application/json",
			},
			body: JSON.stringify(reimApp),
		});
		
	}catch(e){
		console.log(e);
	}
	//debugger;
	location.href="../html/admin.html"
}
