package io.jahed.chatio;

import org.pircbotx.Configuration.Builder;
import org.pircbotx.PircBotX;

public class ChatBuilder extends Builder<PircBotX> {
	
	public ChatBuilder() {
		super();
		this.botFactory = new ChatBotFactory();
	}
}
