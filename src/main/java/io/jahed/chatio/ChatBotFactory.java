package io.jahed.chatio;

import org.pircbotx.Configuration.BotFactory;
import org.pircbotx.PircBotX;
import org.pircbotx.ServerInfo;

public class ChatBotFactory extends BotFactory {
	
    @Override
    public ServerInfo createServerInfo(PircBotX bot) {
        return new ChatServerInfo(bot);
    }
    
}
