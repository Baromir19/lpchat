const togglePasswordBtn = document.getElementById('togglePassword');
const passwordInput = document.getElementById('password');
const eyeIconOpenPath = document.getElementById('eyeIconOpen');
const eyeIconClosedPath = document.getElementById('eyeIconClosed');
const element = document.getElementById('chatBody');

window.onload = function() {
  element.scrollTop = element.scrollHeight;
  
  var pTagsToCut = document.querySelectorAll('#toCut');
  pTagsToCut.forEach(function(element) {
    var text = element.textContent;
    var firstLetter = text.charAt(0);
    element.textContent = firstLetter + '.';
  });
    
  var pTagSpace = document.getElementById('space');
  var text = pTagSpace.textContent;
  pTagSpace.textContent = text + ' ';
};

togglePasswordBtn.addEventListener('click', () => {
  if (passwordInput.type === 'password') {
    passwordInput.type = 'showpass';
    eyeIconOpenPath.style.display = 'none';
    eyeIconClosedPath.style.display = 'block';
  } else {
    passwordInput.type = 'password';
    eyeIconOpenPath.style.display = 'block';
    eyeIconClosedPath.style.display = 'none';
  }
});

function validatePassword(event) {
    var passwordInput = document.getElementById("password");
    var password = passwordInput.value;

    var hasUpperCase = /[A-ZА-ЯЁ]/.test(password);
    var hasLowerCase = /[a-zа-яё]/.test(password);
    var hasNumber = /[0-9]/.test(password);
    var hasMinLength = password.length >= 8;

    if (!(hasUpperCase && hasLowerCase && hasNumber && hasMinLength)) {
        passwordInput.setCustomValidity("Пароль должен иметь 1 цифру, 1 букву нижнего и верхнего регистра и быть не менее 8 символов длиной.");
        passwordInput.reportValidity();
        event.preventDefault(); // Остановка отправки формы при неверном пароле
    } else {
        passwordInput.setCustomValidity(""); // Очистка пользовательского сообщения об ошибке
    }
  }

function autoResizeTextarea() {
    const textareas = document.getElementsByTagName('textarea');
    for (let i = 0; i < textareas.length; i++) {
      const textarea = textareas[i];
      textarea.style.height = 'auto';
      textarea.style.height = textarea.scrollHeight + 'px';
    }
}