let form = document.getElementById("login").addEventListener("submit", login);
console.log("Why is this not updating");
async function login(e) {
  e.preventDefault();

  let email = document.getElementById("email").value;
  let password = document.getElementById("password").value;

  let user = {
    email,
    password,
  };

  try {
    let req = await fetch("http://localhost:8080/ReimbursementApp/api/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(user),
    });
    let res = await req.json();
    
    

    let curUser = {
     
      firstName: res.firstName,
      lastName: res.lastName,
      email: res.email,
      userRole: res.userRole,
      user_id:res.id
    };

    console.log(curUser);
   
    sessionStorage.setItem("employee", JSON.stringify(curUser));
    console.log("What is going on?");


if(curUser.userRole==1){

    location.href = "../html/home.html";
    }else if(curUser.userRole==2){
	location.href="../html/admin.html"
}
  } catch (e) {
    alert("Username or password was incorrect!  COMING FROM JAVASCRIPT");
    return;
  }
}
