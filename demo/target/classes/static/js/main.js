// 'use strict';

// var usernameForm = document.querySelector('#usernameForm');

// var stompClient = null;
// var username = null;
// var group = null;

// function connect(event) {
//     username = document.querySelector('#name').value.trim();
//     group = document.querySelector('#group').value.trim();

//     if(username && group) {
//         // usernamePage.classList.add('hidden');
//         // chatPage.classList.remove('hidden');

//         // var socket = new SockJS('/ws');
//         // stompClient = Stomp.over(socket);
//         // stompClient.connect({}, onConnected, onError);

//         sessionStorage.setItem('username', username);
//         window.location.href = '/chat/' + group;
//     }
//     event.preventDefault();
// }

// usernameForm.addEventListener('submit', connect, true)

'use strict';

var usernameForm = document.querySelector('#usernameForm');
var username = null;
var group = null;

function connect(event) {
    username = document.querySelector('#name').value.trim();
    group = document.querySelector('#group').value.trim();

    if(username && group) {
        sessionStorage.setItem('username', username);
        sessionStorage.setItem('group', group);
        window.location.href = '/chat/' + group;
    }
    event.preventDefault();
}

usernameForm.addEventListener('submit', connect, true);
