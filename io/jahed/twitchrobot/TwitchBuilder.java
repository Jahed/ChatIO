package io.jahed.twitchrobot;

import org.pircbotx.Configuration.Builder;
import org.pircbotx.PircBotX;

public class TwitchBuilder extends Builder<PircBotX> {
	
	public TwitchBuilder() {
		super();
		this.botFactory = new TwitchBotFactory();
	}
}
