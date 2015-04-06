package io.jahed.crowd_play;

import java.util.List;

import org.pircbotx.PircBotX;
import org.pircbotx.ServerInfo;

public class ChatServerInfo extends ServerInfo {

    public ChatServerInfo(PircBotX bot) {
        super(bot);
    }
    
    @Override
    public void parse(int code, List<String> parsedLine) {
        // Twitch's IRC Server has an extra Code 4 response
    	// containing user's nick.
        if(code == 4 && parsedLine.size() < 2) return;
        
        super.parse(code, parsedLine);
    }

}
