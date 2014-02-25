#Twitch Robot

A simple cross-platform tool for controlling stuff off Twitch.tv chat.

Inspired by [TwitchPlaysPokemon](http://www.twitch.tv/twitchplayspokemon).

##Usage
Install "message-sender.js" as a Userscript (use Greasemonkey or something).

Build the JAR or grab it [from here (not always up-to-date)](https://www.dropbox.com/sh/m4ggpmfokvkdkkt/yAQN1-L-eC/Twitch%20Robot).

Go to your channel chat (web-only currently) with the script enabled, refresh.

Run "java -jar twitch-robot.jar" on the same machine.

Move focus to the window you want to control (e.g. the emulator).

If you're using your personal computer, I suggest running it within a virtual machine using VirtualBox.

##Mappings
Currently the mappings are hard coded to work with SNES games. Change your emulator's controls to match these.

The inputs are mapped as:

up    -> Numpad 8

down  -> Numpad 2

left  -> Numpad 4

right -> Numpad 6


b -> Numpad 1

a -> Numpad 3

y -> Number 7

x -> Numpad 9

l -> Slash (/)

r -> Asterisk (*)


start  -> Numpad 0

select -> Period (.)


##TODO
There's a lot to do. This is all currently at its first stages.

* Only trigger inputs when specified window is in focus
* Allow customised inputs
* Fancy output

##LICENSE

Copyright (C) 2014 Jahed Ahmed

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in the
Software without restriction, including without limitation the rights to use, copy,
modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
and to permit persons to whom the Software is furnished to do so, subject to the
following conditions:
 
The above copyright notice and this permission notice shall be included in all copies
or substantial portions of the Software.
 
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
