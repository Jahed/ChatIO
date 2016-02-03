package io.jahed.crowd_play;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ChatRobot implements ActionListener {
    
    public static final String IRC_NAME = "ChatRobot";
    
    public static final String REPEAT_TOKEN = "repeat ";
    public static final int REPEAT_TOKEN_LENGTH = REPEAT_TOKEN.length();
    
    public static final String HOLD_TOKEN = "hold ";
    public static final int HOLD_TOKEN_LENGTH = HOLD_TOKEN.length();

    private Robot inputBot;
    private PircBotX ircBot;
    private final ChatConfig config;
    private Map<String, Integer> keyMap;
    private Timer repeatTimer;
    private Integer repeatKey;
    private Integer holdKey;

    public ChatRobot(ChatConfig config, Map<String, String> keyMapConfig) throws Exception {
        this.config = config;

        System.out.print("Parsing Keys...");
        this.setKeyMap(keyMapConfig);
        System.out.println(" OK");

        System.out.print("Setting up IRC Configuration...");
        Configuration<PircBotX> configuration = new ChatBuilder()
            .setLogin(IRC_NAME)
            .setRealName(IRC_NAME)
            .setAutoNickChange(true)
            .setName(config.username)
            .setServerHostname(config.server)
            .setServerPort(config.port)
            .setServerPassword(config.password)
            .addAutoJoinChannel(config.channel)
            .addListener(new ChatListener(this))
            .buildConfiguration();
        ircBot = new PircBotX(configuration);
        System.out.println(" OK");

        System.out.print("Creating Robot...");
        inputBot = new Robot();
        inputBot.setAutoDelay(100); // required to register key presses on some emulators
        System.out.println(" OK");

        repeatTimer = new Timer(100, this);
    }

    public ChatConfig getConfig() {
        return config;
    }

    private void setKeyMap(Map<String, String> keyMapConfig) throws Exception {
        this.keyMap = new HashMap<>();
        
        for(String word : keyMapConfig.keySet()) {
            Integer keyCode = (Integer)KeyEvent.class.getField(keyMapConfig.get(word)).get(null);
            this.keyMap.put(word, keyCode);
        }
    }
    
    public Map<String, Integer> getKeyMap() {
        return keyMap;
    }
    
    public void run() throws IOException, IrcException {
        ircBot.startBot();        
    }

    public boolean perform(String input) {
        boolean repeat = input.startsWith(REPEAT_TOKEN);
        boolean hold = input.startsWith(HOLD_TOKEN);
        Integer keyCode;
        
        if(repeat) {
            keyCode = keyMap.get(input.substring(REPEAT_TOKEN_LENGTH));
        } else if(hold) {
            keyCode = keyMap.get(input.substring(HOLD_TOKEN_LENGTH));
        } else {
            keyCode = keyMap.get(input);
        }
        
        if(keyCode == null) {
            return false;
        }
        
        if(repeatKey != null) {
            repeatTimer.stop();
            repeatKey = null;
        } else if(holdKey != null && (!hold || !keyCode.equals(holdKey))) {
            inputBot.keyRelease(holdKey);
            holdKey = null;
        }
        
        if(repeat) {
            repeatKey = keyCode;
            repeatTimer.start();
        } else if(hold && !keyCode.equals(holdKey)) {
            holdKey = keyCode;
            inputBot.keyPress(keyCode);
        } else {
            inputBot.keyPress(keyCode);
            inputBot.keyRelease(keyCode);
        }

        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        inputBot.keyPress(repeatKey);
        inputBot.keyRelease(repeatKey);
    }

}