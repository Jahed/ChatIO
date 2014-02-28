package io.jahed.twitchrobot;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

public class TwitchListener extends ListenerAdapter<PircBotX> {
	
	private TwitchRobot robot;
	
	private int availableNickSpace;;
	private int availableKeySpace;
	
	public TwitchListener(TwitchRobot robot) {
		this.robot = robot;
		
		this.availableNickSpace = 15;
		this.availableKeySpace = Collections.max(robot.getKeyMap().keySet(), new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return s1.length() - s2.length();
			}
		}).length();
	}
	
	@Override
	public void onMessage(MessageEvent<PircBotX> event) throws Exception {
		String message = event.getMessage();
		
		if(robot.perform(message)) {
			String nick = event.getUser().getNick();
			
			StringBuilder builder = new StringBuilder(nick);
			
			int nickDiff = availableNickSpace - nick.length();
			
			if(nickDiff < 0) {
				builder.delete(availableNickSpace+1, nick.length()+1);
				nickDiff = 0;
			}
			
			char[] padding = new char[nickDiff + availableKeySpace - message.length()];
			Arrays.fill(padding, ' ');
			
			builder.append(padding);
			builder.append(message);
			
			System.out.println(builder.toString());
		}
	}
	
}
