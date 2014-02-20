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

* Use IRC channel instead of web chat (remove dependancy on userscript)
* Only trigger inputs when specified window is in focus
* Allow customised inputs
* Fancy output
