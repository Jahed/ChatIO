#ChatIO

A simple cross-platform tool for controlling stuff off IRC (such as Twitch.tv chat). Currently only trigger key presses.

Inspired by [TwitchPlaysPokemon](http://www.twitch.tv/twitchplayspokemon).

##Usage
Build the JAR or grab it [from here (not always up-to-date)](https://www.dropbox.com/sh/m4ggpmfokvkdkkt/yAQN1-L-eC/Twitch%20Robot).

Create a config.json (see config.json.template) to connect to the IRC channel you want to listen to.

Create a keys.json (see keys folder) to map words to specific keys. Keys should map to [KeyEvent](http://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyEvent.html) fields (anything prefixed with 'VK_').

Run "java -jar twitch-robot.jar {config.json} {keys.json}" on the same machine.

Move focus to the window you want to control (e.g. the emulator).

If you're using your personal computer, I suggest running it within a virtual machine using VirtualBox.

##Mappings
You can map words to keys.
Prefixing 'hold ' to a word will hold that key until the next command is recieved.
Prefixing 'repeat ' will continuously press that key until the next command.

##TODO
There's a lot to do. This is all currently at its first stages.

* Only trigger inputs when specified window is in focus
* Customisable window ready for easy live-streaming

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
