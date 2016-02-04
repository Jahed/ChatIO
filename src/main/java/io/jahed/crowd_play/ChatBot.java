package io.jahed.crowd_play;

import io.jahed.crowd_play.action.Action;
import io.jahed.crowd_play.action.ActionType;
import io.jahed.crowd_play.action.NullAction;
import io.jahed.crowd_play.irc.TwitchIRCBotFactory;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

import java.awt.*;

public class ChatBot {
    
    public static final String IRC_NAME = "CrowdPlayBot";

    private final Robot inputBot;
    private final PircBotX ircBot;
    private final ChatBotConfig config;
    private final KeyMap keyMap;

    private Action lastAction = new NullAction(this, null);

    public ChatBot(ChatBotConfig config, KeyMap keyMap) throws Exception {
        this.config = config;
        this.keyMap = keyMap;

        System.out.print("Creating IRC Bot...");
        ircBot = createIRCBot();
        System.out.println(" OK");

        System.out.print("Creating Input Bot...");
        inputBot = createInputBot();
        System.out.println(" OK");
    }

    private PircBotX createIRCBot() {
        Configuration<PircBotX> configuration = new Configuration.Builder<>()
            .setBotFactory(new TwitchIRCBotFactory())
            .setLogin(IRC_NAME)
            .setRealName(IRC_NAME)
            .setAutoNickChange(true)
            .setName(config.username)
            .setServerHostname(config.server)
            .setServerPort(config.port)
            .setServerPassword(config.password)
            .addAutoJoinChannel(config.channel)
            .addListener(new ChatBotIRCListener(this))
            .buildConfiguration();
        return new PircBotX(configuration);
    }

    private Robot createInputBot() throws Exception {
        Robot inputBot = new Robot();
        inputBot.setAutoDelay(100); // required to register key presses on some emulators
        return inputBot;
    }

    public void start() throws Exception {
        ircBot.startBot();
    }

    public void perform(String input) {
        ActionType actionType = ActionType.fromInput(input);

        Integer keyCode = keyMap.getKeyCode(actionType.getKey(input));

        Action newAction = actionType.createAction(this, keyCode);

        lastAction.stop();
        newAction.perform();

        lastAction = newAction;
    }

    public Robot getInputBot() {
        return inputBot;
    }

    public KeyMap getKeyMap() {
        return keyMap;
    }

    public boolean isRestricted() {
        return config.restricted;
    }

    public String getUsername() {
        return config.username;
    }

}