document.getElementById("loginForm").addEventListener("submit", function (e) {
  e.preventDefault();

  const username = document.getElementById("username").value.trim();
  const password = document.getElementById("password").value.trim();

  if (username && password) {
    alert("Login successful (demo only)");
  } else {
    alert("Please enter both username and password.");
  }
});
