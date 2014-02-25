package io.jahed.twitchrobot;

import java.util.List;

import org.pircbotx.PircBotX;
import org.pircbotx.ServerInfo;

public class TwitchServerInfo extends ServerInfo {

	public TwitchServerInfo(PircBotX bot) {
		super(bot);
	}
	
	@Override
	public void parse(int code, List<String> parsedLine) {
		if(code == 4 && parsedLine.size() < 2) {
			//Twitch IRC Server has an extra Code 4 response containing user's nick.
			return;
		}
		
		super.parse(code, parsedLine);
	}

}
