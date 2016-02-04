package io.jahed.crowd_play.action;

import io.jahed.crowd_play.ChatBot;

public abstract class Action {

    public final ChatBot chatBot;
    public final Integer keyCode;

    public Action(ChatBot chatBot, Integer keyCode) {
        this.chatBot = chatBot;
        this.keyCode = keyCode;
    }

    public abstract void perform();
    public abstract void stop();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Action action = (Action) o;

        if (chatBot != null ? !chatBot.equals(action.chatBot) : action.chatBot != null) return false;
        return keyCode != null ? keyCode.equals(action.keyCode) : action.keyCode == null;
    }

    @Override
    public int hashCode() {
        int result = chatBot != null ? chatBot.hashCode() : 0;
        result = 31 * result + (keyCode != null ? keyCode.hashCode() : 0);
        return result;
    }

}
