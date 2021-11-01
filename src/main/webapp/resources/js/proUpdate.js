let form = document
  .getElementById("reim")
  .addEventListener("submit", submitReim);



async function submitReim(e) {
  e.preventDefault();
let user = JSON.parse(sessionStorage.getItem("employee"));
let id = user.id;
  let firstName = document.getElementById("firstName").value;
  let lastName = document.getElementById("lastName").value;
  let email = document.getElementById("email").value;
  let username = document.getElementById("username").value;
  let password = document.getElementById("password").value;

  let update = {
	id:id,
    firstName: firstName,
    lastName: lastName,
    email: email,
    username: username,
    password: password,
  };

  try {
    let req = await fetch("/ReimbursementApp/api/proupdate", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(update),
    });
    let res = await req.json;
    console.log(res);
    location.href = "../html/profile.html";
  } catch (e) {
    console.log(e);
  }
}
