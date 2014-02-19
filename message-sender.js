// ==UserScript==
// @name		Twitch Robot Message Sender
// @author		Jahed
// @icon		http://i.imgur.com/Lkl0EDA.png
// @description		For the Twitch Robot to get messages from chat.
// @version		0.1
// @date		2014-02-18
// @updateURL		http://userscripts.org/scripts/source/???
// @namespace		TwitchRobotMessageSender_Jahed
// @include		*.twitch.tv/*
// ==/UserScript==

var chatLineList = document.getElementById('chat_line_list');
chatLineList.addEventListener('DOMNodeInserted', setup, false);

function setup(event) {
	if(event.target.firstElementChild.innerHTML.indexOf('Welcome to the chat room!') === -1) {
		return;
	}

	chatLineList.removeEventListener("DOMNodeInserted", setup, false);
	chatLineList.addEventListener("DOMNodeInserted", chatAdded, false);
	
	console.log('Chat Ready');
}

function chatAdded(event) {
	var chatLineElement = event.target,
		chatText;
		
	if(chatLineElement.className !== 'chat_line') {
		chatLineElement = chatLineElement.getElementsByClassName('chat_line')[0];
	}
	
	chatText = chatLineElement.innerHTML;
	
	console.log(chatText);
	
	var xhr = new XMLHttpRequest();
	xhr.open("post", "http://localhost:8090/recieve", true);
	xhr.send(chatText);
}

console.log('Twitch Robot Message Sender Loaded');