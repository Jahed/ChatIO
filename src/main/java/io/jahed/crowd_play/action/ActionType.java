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
        String inputPrefix = getInputPrefix(this);
        return input.startsWith(inputPrefix)
            ? input.substring(inputPrefix.length())
            : input;
    }

    public Action createAction(ChatBot chatBot, Integer keyCode) {
        return actionConstructor.apply(chatBot, keyCode);
    }

    public static ActionType fromInput(String input) {
        return Stream.of(ActionType.values())
            .filter(type -> input.startsWith(getInputPrefix(type)))
            .findFirst()
            .orElse(TAP);
    }

    private static String getInputPrefix(ActionType actionType) {
        return actionType.name().toLowerCase() + " ";
    }
}
