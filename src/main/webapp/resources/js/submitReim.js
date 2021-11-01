let form = document
  .getElementById("reim")
  .addEventListener("submit", submitReim);

async function submitReim(e) {
  e.preventDefault();
  let amount = document.getElementById("amount").value;
  let description = document.getElementById("description").value;
  let expType = document.getElementById("expenseType").value;
  let reimauthor = JSON.parse(sessionStorage.getItem("employee"));

  let reimbursement = {
    reimamount: amount,
    description: description,
    reimauthor: reimauthor.user_id,
    reimstatus: "1",
    reimtype: expType,
  };

  try {
    let req = await fetch("/ReimbursementApp/api/reimrequest", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(reimbursement),
    });
    let res = await req.json;
    console.log(res);
    location.href = "../html/home.html";
  } catch (e) {
    console.log(e);
  }
}
