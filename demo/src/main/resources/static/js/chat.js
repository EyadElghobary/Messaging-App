'use static';

var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');
var stompClient = null;
var username = null;
var group = null;


var colors = [
  '#2196F3',
  '#32c787',
  '#00BCD4',
  '#ff5652',
  '#ffc107',
  '#ff85af',
  '#FF9800',
  '#39bbb0',
];

function connect() {
    group = sessionStorage.getItem('group');
    username = sessionStorage.getItem('username');
    if(username && group) {
        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, onConnected, onError);
    }
}

// function onConnected() {
//   // Subscribe to the Public Topic
//   var username = sessionStorage.getItem('username');
//   stompClient.subscribe('/topic/' + group, onMessageReceived);

//   // Tell the server that a user has joined the chat.
//   stompClient.send(
//     '/app/chat.addUser',
//     {},
//     JSON.stringify({ sender: username, type: 'JOIN', group: group })
//   );

//   connectingElement.classList.add('hidden');
// }

function onConnected() {
    stompClient.subscribe('/topic/' + group, onMessageReceived);
    stompClient.send('/app/chat.addUser', {}, JSON.stringify({sender: username, type: 'JOIN', group: group}));
    connectingElement.classList.add('hidden');
}

function onError(error) {
  connectingElement.textContent =
    'Could not connect to WebSocket server. Please refresh this page to try again!';
  connectingElement.style.color = 'red';
}

function sendMessage(event) {
  var messageContent = messageInput.value.trim();
  console.log(messageContent);
  if (messageContent && stompClient) {
    var chatMessage = {
      sender: username,
      content: messageInput.value,
      group: group,
      type: 'CHAT',
    };
    stompClient.send('/app/chat.sendMessage', {}, JSON.stringify(chatMessage));
    messageInput.value = '';
  }
  event.preventDefault();
}

function onMessageReceived(payload) {
  var message = JSON.parse(payload.body);

  var messageElement = document.createElement('li');

  if (message.type === 'JOIN') {
    messageElement.classList.add('event-message');
    message.content = message.sender + ' joined!';
  } else if (message.type === 'LEAVE') {
    messageElement.classList.add('event-message');
    message.content = message.sender + ' left!';
  } else {
    messageElement.classList.add('chat-message');

    var avatarElement = document.createElement('i');
    var avatarText = document.createTextNode(message.sender[0]);
    avatarElement.appendChild(avatarText);
    avatarElement.style['background-color'] = getAvatarColor(message.sender);

    messageElement.appendChild(avatarElement);

    var usernameElement = document.createElement('span');
    var usernameText = document.createTextNode(message.sender);
    usernameElement.appendChild(usernameText);
    messageElement.appendChild(usernameElement);
  }

  var textElement = document.createElement('p');
  var messageText = document.createTextNode(message.content);
  textElement.appendChild(messageText);

  messageElement.appendChild(textElement);

  messageArea.appendChild(messageElement);
  messageArea.scrollTop = messageArea.scrollHeight;
}

function getAvatarColor(messageSender) {
  var hash = 0;
  for (var i = 0; i < messageSender.length; i++) {
    hash = 31 * hash + messageSender.charCodeAt(i);
  }
  var index = Math.abs(hash % colors.length);
  return colors[index];
}

messageForm.addEventListener('submit', sendMessage, true);


document.addEventListener('DOMContentLoaded', function() {
    connect();
});

