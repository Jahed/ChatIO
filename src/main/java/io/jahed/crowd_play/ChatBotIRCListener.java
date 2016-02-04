package io.jahed.crowd_play;

import io.jahed.crowd_play.action.ActionType;
import org.pircbotx.PircBotX;
import org.pircbotx.User;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.Arrays;
import java.util.stream.Stream;

public class ChatBotIRCListener extends ListenerAdapter<PircBotX> {

    private final static int AVAILABLE_NICK_SPACE = 15;

    private final ChatBot chatBot;
    private final int availableInputSpace;

    public ChatBotIRCListener(ChatBot chatBot) {
        this.chatBot = chatBot;

        int maxKeyLength = chatBot.getKeyMap().getKeys().stream()
            .mapToInt(String::length)
            .max()
            .orElse(0);

        int maxPressLength = Stream.of(ActionType.values())
            .map(ActionType::name)
            .mapToInt(String::length)
            .max()
            .orElse(0);

        this.availableInputSpace = maxPressLength + maxKeyLength + 1;
    }

    @Override
    public void onMessage(MessageEvent<PircBotX> event) throws Exception {
        User user = event.getUser();
        String message = event.getMessage();

        if(!allowedUser(user)) {
            return;
        }

        try {
            chatBot.perform(message);
            printInput(user, message);
        } catch(Exception e) {
        }
    }

    private boolean allowedUser(User user) {
        if(chatBot.isRestricted()) {
            return user.getNick().equals(chatBot.getUsername());
        }
        return true;
    }

    private void printInput(User user, String input) {
        String nick = user.getNick();
        StringBuilder line = new StringBuilder(nick);
        int nickSpaceRemaining = AVAILABLE_NICK_SPACE - nick.length();
        if(nickSpaceRemaining < 0) {
            line.delete(AVAILABLE_NICK_SPACE +1, nick.length()+1);
            nickSpaceRemaining = 0;
        }

        char[] padding = new char[nickSpaceRemaining + availableInputSpace - input.length()];
        Arrays.fill(padding, ' ');

        line.append(padding);
        line.append(input);
        System.out.println(line);
    }

}
