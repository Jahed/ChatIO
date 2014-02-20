// ==UserScript==
// @name        Twitch Robot Message Sender
// @author      Jahed
// @icon        http://i.imgur.com/Lkl0EDA.png
// @description For the Twitch Robot to get messages from chat.
// @version     0.1
// @date        2014-02-18
// @updateURL   http://userscripts.org/scripts/source/???
// @namespace   TwitchRobotMessageSender_Jahed
// @include     *.twitch.tv/*
// ==/UserScript==

/**
 *  Copyright (C) 2014 Jahed Ahmed
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of
 *  this software and associated documentation files (the "Software"), to deal in the
 *  Software without restriction, including without limitation the rights to use, copy,
 *  modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 *  and to permit persons to whom the Software is furnished to do so, subject to the
 *  following conditions:
 *  
 *  The above copyright notice and this permission notice shall be included in all copies
 *  or substantial portions of the Software.
 *  
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 *  INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 *  PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 *  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 *  CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 *  OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
 
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