package io.jahed.crowd_play.action;

import io.jahed.crowd_play.ChatBot;

public class TapAction extends Action {

    public TapAction(ChatBot chatBot, Integer key) {
        super(chatBot, key);
    }

    @Override
    public void perform() {
        chatBot.getInputBot().keyPress(keyCode);
        chatBot.getInputBot().keyRelease(keyCode);
    }

    @Override
    public void stop() {
    }
}
