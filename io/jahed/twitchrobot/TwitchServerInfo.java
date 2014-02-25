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
		//Library assumes too much.
		if(code == 4 && parsedLine.size() < 2) {
			System.err.println("TwitchServerInfo - Code 4 with " + parsedLine);
			return;
		}
		
		super.parse(code, parsedLine);
	}

}
