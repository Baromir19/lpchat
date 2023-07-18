var socket = new SockJS("/ws");
var stompClient = Stomp.over(socket);

function sendMessage() {
	event.preventDefault();
    var messageText = document.getElementById("messageText").value;
    var senderId = 23;
    var key = "MySecretKey11111"; // Здесь можно использовать реализацию обмена ключами
    var encryptedMessage = encryptMessage(messageText, key);
    
    var messageObject = {
		message_text: encryptedMessage,
    	senderId: senderId
    	};
    					 
    stompClient.send("/app/chats/message", {}, JSON.stringify(messageObject));
    clearTextarea();
}

stompClient.connect({}, function() {
    console.log("WebSocket initialized");

    stompClient.subscribe("/chats/message", function(response) {
		console.log(response);
        var message = JSON.parse(response.body);
        
        var key = "MySecretKey11111"; 
        var decryptedMessage = decryptMessage(message.message_text, key);
        
		createMessageElement(decryptedMessage, message.senderId, 0) /// TODO: senderId to sender name, get whos send (me or companion)

    });
});

function createMessageElement(message, username, isCompanion) {
  var messagesBox = document.querySelector(".messages-box");
	
  var messageDiv = document.createElement("div");
  var messageSender = document.createElement("p");
  var messageText = document.createElement("p");

  if (isCompanion) {
    messageDiv.classList.add("companion-message");
  } else {
    messageDiv.classList.add("user-message");
  }

  messageSender.textContent = username;
  messageText.textContent = message; //later do username under message

  messageDiv.appendChild(messageSender);
  messageDiv.appendChild(messageText);

  messagesBox.appendChild(messageDiv);
}

function clearMessages() {
  var messagesBox = document.querySelector(".messages-box");
  messagesBox.innerHTML = "";
}

function clearTextarea() {
  var textarea = document.querySelector(".message-input-container textarea");
  textarea.value = "";
}

  function encryptMessage(messageText, key) {
    var ciphertext = CryptoJS.AES.encrypt(messageText, key);
    return ciphertext.toString();
  }

  function decryptMessage(ciphertext, key) {
    var bytes = CryptoJS.AES.decrypt(ciphertext, key);
    var plaintext = bytes.toString(CryptoJS.enc.Utf8);
    return plaintext;
  }

// Вызов функции для запроса сообщений при загрузке страницы или при необходимости