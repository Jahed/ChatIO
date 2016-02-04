package io.jahed.crowd_play.action;

import io.jahed.crowd_play.ChatBot;

public class HoldAction extends Action {

    public HoldAction(ChatBot chatBot, Integer key) {
        super(chatBot, key);
    }

    @Override
    public void perform() {
        chatBot.getInputBot().keyPress(keyCode);
    }

    @Override
    public void stop() {
        chatBot.getInputBot().keyRelease(keyCode);
    }
}
