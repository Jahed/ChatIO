package io.jahed.crowd_play.action;

import io.jahed.crowd_play.ChatBot;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RepeatAction extends Action implements ActionListener {

    private Timer timer;

    public RepeatAction(ChatBot chatBot, Integer key) {
        super(chatBot, key);
        timer = new Timer(100, this);
        timer.setInitialDelay(0);
    }

    @Override
    public void perform() {
        timer.start();
    }

    @Override
    public void stop() {
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        chatBot.getInputBot().keyPress(keyCode);
        chatBot.getInputBot().keyRelease(keyCode);
    }
}
