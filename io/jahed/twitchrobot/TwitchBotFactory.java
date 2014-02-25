package io.jahed.twitchrobot;

import org.pircbotx.Configuration.BotFactory;
import org.pircbotx.PircBotX;
import org.pircbotx.ServerInfo;

public class TwitchBotFactory extends BotFactory {
	
	@Override
	public ServerInfo createServerInfo(PircBotX bot) {
		return new TwitchServerInfo(bot);
	}
}
