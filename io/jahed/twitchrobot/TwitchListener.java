package io.jahed.twitchrobot;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ConnectEvent;
import org.pircbotx.hooks.events.DisconnectEvent;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.QuitEvent;

public class TwitchListener extends ListenerAdapter<PircBotX> {
	
	private TwitchRobot robot;
	
	public TwitchListener(TwitchRobot robot) {
		this.robot = robot;
	}

	@Override
	public void onConnect(ConnectEvent<PircBotX> event) throws Exception {
		
	}

	@Override
	public void onDisconnect(DisconnectEvent<PircBotX> event) throws Exception {
		
	}

	@Override
	public void onJoin(JoinEvent<PircBotX> event) throws Exception {
		
	}

	@Override
	public void onMessage(MessageEvent<PircBotX> event) throws Exception {
		String message = event.getMessage();
		if(robot.perform(message)) {
			System.out.println(event.getUser().getNick() + "\t" + message);
		}
	}

	@Override
	public void onQuit(QuitEvent<PircBotX> event) throws Exception {
		
	}
	
}
