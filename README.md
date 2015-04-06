# Crowd Play
Trigger input commands using messages from multiple users via IRC.

## Dependencies
* [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)

## Quick Start
1. Build the app.

2. Create a Config JSON to connect to the IRC channel you want to hook up to.

   To hook up to a Twitch channel's chat see [Twitch's IRC help page]
   (http://help.twitch.tv/customer/portal/articles/1302780-twitch-irc).
   Channel names should be lowercase otherwise you won't join the same IRC
   channel as the one on your live channel page.

3. Create a Key Mapping JSON to map words to specific keys.

4. Run the app.

## Building
```sh
$ ./gradlew build

./build/libs/crowd-play-<version>.jar
```

### Usage
```sh
$ java -jar crowd-play.jar --help

usage: java -jar crowd-play.jar --config <config json> --keys <keys json>
 -b,--blank          hide setup logging when ready
 -c,--config <arg>   configuration json
 -h,--help           this help
 -k,--keys <arg>     key mapping json
 -v,--version        version number
```

See `./config` for example JSON files.

If you're using your personal computer, I suggest running it within a virtual
machine such as [VirtualBox](https://www.virtualbox.org/)) so that inputs aren't
triggered directly on your machine.

### Key Mappings
You can map words to keyboard keys by defining a key configuration.

Keys should map to [static KeyEvent fields]
(http://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyEvent.html), that is
anything prefixed with `VK_`. Example key configurations can be found under
[`./config/keys`](./config/keys).

### Prefixes
In chat, you can use prefixes to perform different tasks other than single key
presses. For example `hold a` will hold the `a` key.

| Prefix    | Description                                                 |
|:----------|:------------------------------------------------------------|
| `hold `   | Hold a key until the next command is received               |
| `repeat ` | Continuously press a key until the next command is received | 


## Contributors
This project was heavily inspired by [TwitchPlaysPokemon](http://www.twitch.tv/twitchplayspokemon).

## Contributing
I'm open to pull requests, issues and any other form of contribution.
