package io.jahed.crowd_play.action;

import io.jahed.crowd_play.ChatBot;

import java.util.function.BiFunction;
import java.util.stream.Stream;

public enum ActionType {
    TAP(TapAction::new),
    REPEAT(RepeatAction::new),
    HOLD(HoldAction::new);

    private BiFunction<ChatBot, Integer, Action> actionConstructor;

    ActionType(BiFunction<ChatBot, Integer, Action> actionConstructor) {
        this.actionConstructor = actionConstructor;
    }

    public String getKey(String input) {
        return input.startsWith(this.name().toLowerCase() + " ")
                ? input.substring(this.name().length() + 1)
                : input;
    }

    public Action createAction(ChatBot chatBot, Integer keyCode) {
        return actionConstructor.apply(chatBot, keyCode);
    }

    public static ActionType fromInput(String input) {
        return Stream.of(ActionType.values())
                .filter(type -> input.startsWith(type + " "))
                .findFirst()
                .orElse(TAP);
    }
}
