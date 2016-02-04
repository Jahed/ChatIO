package io.jahed.crowd_play.action;

import io.jahed.crowd_play.ChatBot;

public class NullAction extends Action {

    public NullAction(ChatBot chatBot, Integer key) {
        super(chatBot, key);
    }

    @Override
    public void perform() {
    }

    @Override
    public void stop() {
    }
}
