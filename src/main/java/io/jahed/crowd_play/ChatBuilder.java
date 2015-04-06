package io.jahed.crowd_play;

import org.pircbotx.Configuration.Builder;
import org.pircbotx.PircBotX;

public class ChatBuilder extends Builder<PircBotX> {
    
    public ChatBuilder() {
        super();
        this.botFactory = new ChatBotFactory();
    }

}
