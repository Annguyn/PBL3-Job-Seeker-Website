document.addEventListener('DOMContentLoaded', function() {
    var loginButton = document.getElementById('loginBtn');
    loginButton.addEventListener('click', function() {
      console.log('loginButton clicked');
      window.location.href = "../index.html";
    });
  });
  document.addEventListener('DOMContentLoaded', function() {
    var signupButton = document.getElementById('signUpBtn');
    signupButton.addEventListener('click', function() {
      console.log('loginButton clicked');
      window.location.href = "../sign-up.html";
    });
  });
