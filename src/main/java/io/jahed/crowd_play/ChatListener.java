package io.jahed.crowd_play;

import org.pircbotx.PircBotX;
import org.pircbotx.User;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.Arrays;
import java.util.Collections;

public class ChatListener extends ListenerAdapter<PircBotX> {

    private final ChatConfig config;
    private final ChatRobot robot;

    private final int availableNickSpace;
    private final int availableKeySpace;

    public ChatListener(ChatRobot robot) {
        this.robot = robot;
        this.config = robot.getConfig();

        this.availableNickSpace = 15;

        int maxInputLength = Collections.max(robot.getKeyMap().keySet(), (s1, s2) -> s1.length() - s2.length()).length();
        this.availableKeySpace = maxInputLength + ChatRobot.REPEAT_TOKEN_LENGTH;
    }

    @Override
    public void onMessage(MessageEvent<PircBotX> event) throws Exception {
        User user = event.getUser();

        if(!allowedUser(user)) {
            return;
        }

        String message = event.getMessage();
        if(robot.perform(message)) {
            printInput(user, message);
        }
    }

    private void printInput(User user, String input) {
        String nick = user.getNick();

        StringBuilder builder = new StringBuilder(nick);

        int nickDiff = availableNickSpace - nick.length();

        if(nickDiff < 0) {
            builder.delete(availableNickSpace+1, nick.length()+1);
            nickDiff = 0;
        }

        char[] padding = new char[nickDiff + availableKeySpace - input.length()];
        Arrays.fill(padding, ' ');

        builder.append(padding);
        builder.append(input);

        System.out.println(builder);
    }


    private boolean allowedUser(User user) {
        return !config.restricted || user.getNick().equals(config.username);
    }

}
