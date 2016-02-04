package io.jahed.crowd_play.irc;

import org.pircbotx.Configuration.BotFactory;
import org.pircbotx.PircBotX;
import org.pircbotx.ServerInfo;

import java.util.List;

public class TwitchIRCBotFactory extends BotFactory {

    @Override
    public ServerInfo createServerInfo(PircBotX bot) {
        return new TwitchIRCServerInfo(bot);
    }

    private class TwitchIRCServerInfo extends ServerInfo {
        public TwitchIRCServerInfo(PircBotX bot) {
            super(bot);
        }

        @Override
        public void parse(int code, List<String> parsedLine) {
            if(isAdditionalTwitchInfo(code, parsedLine)) {
                return;
            }

            super.parse(code, parsedLine);
        }

        private boolean isAdditionalTwitchInfo(int code, List<String> parsedLine) {
            // Twitch's IRC Server has an extra Code 4 response containing user's nick.
            return code == 4 && parsedLine.size() < 2;
        }
    }

}
