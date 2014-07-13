# Chat IO

A simple cross-platform tool for triggering key presses through IRC channels.

Inspired by [TwitchPlaysPokemon](http://www.twitch.tv/twitchplayspokemon).

## Dependencies
* [JDK 7 or later](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Gradle](http://www.gradle.org/)

## Getting Started
1. [Build](#building) the app.

2. Create a [config.json](config.json.template) to connect to the IRC channel you want to listen to.

 > To hook up to a Twitch channel's chat see [Twitch's IRC help page](http://help.twitch.tv/customer/portal/articles/1302780-twitch-irc).
   Channel names should be lowercase otherwise you won't join the same IRC channel as the one on your live channel page.

3. Create a [keys.json](keys) to map words to specific keys. See [Mappings](#mappings).

## Usage
```sh
java -jar twitch-robot.jar <path_to_config_json> <path_to_keys_json>
# Then move focus to the window you want to control (e.g. an emulator).
```

> If you're using your personal computer, I suggest running it within a virtual machine using a VM
  (like [VirtualBox](https://www.virtualbox.org/) so that inputs aren't triggered directly on your machine.

## Building

To automate builds, the project uses [Gradle](http://www.gradle.org/).

```sh
gradle build
# Creates a JAR with dependencies ready to run under ./build/libs/
```

## Mappings
You can map words to keyboard keys by defining a key configuration.

Keys should map to [KeyEvent](http://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyEvent.html) fields (anything prefixed with 'VK_').
Example key configurations can be found [here](keys)

### Prefixes
You can use prefixes to perform different tasks other than single key presses.
For example `hold a` can hold the `a` key indefinitely.

| Prefix | Description |
|:-------|:------------|
| `hold ` | Hold a key until the next command is received | 
| `repeat ` | Continuously press a key until the next command is received | 

## To Do
There's a lot to do. This is all currently at its first stages.

* Only trigger inputs when a specified window is in focus
* Customisable window ready for easy live-streaming (stop using stdin)
* Mouse movement?

## License

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
