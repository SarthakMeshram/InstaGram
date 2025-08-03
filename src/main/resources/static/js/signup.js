document.getElementById("signupForm").addEventListener("submit", async function (e) {
  e.preventDefault();

  const email = document.getElementById("email").value.trim();
  const fullname = document.getElementById("fullname").value.trim(); // optional, based on your backend
  const username = document.getElementById("newUsername").value.trim();
  const password = document.getElementById("newPassword").value.trim();

  if (email && username && password) {
    try {
      const response = await fetch("http://localhost:8080/signup", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          email,
          username,
          password
        }),
      });

      if (response.ok) {
        alert("Signup successful");
      } else {
        const error = await response.text();
        alert("Signup failed: " + error);
      }
    } catch (err) {
      alert("Error: " + err.message);
    }
  } else {
    alert("Please fill out all fields.");
  }
});
