/**
 *  Copyright (C) 2014 Jahed Ahmed
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of
 *  this software and associated documentation files (the "Software"), to deal in the
 *  Software without restriction, including without limitation the rights to use, copy,
 *  modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 *  and to permit persons to whom the Software is furnished to do so, subject to the
 *  following conditions:
 *  
 *  The above copyright notice and this permission notice shall be included in all copies
 *  or substantial portions of the Software.
 *  
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 *  INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 *  PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 *  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 *  CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 *  OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package io.jahed.twitchrobot;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TwitchRobot {
	
	public static final String IRC_NAME = "TwitchRobot";
	
	public static void main(String[] args) throws AWTException, JsonParseException, IOException, IrcException {
		ObjectMapper mapper = new ObjectMapper();
		TwitchConfig config = mapper.readValue(new File("config.json"), TwitchConfig.class);
		
		TwitchRobot twitchRobot = new TwitchRobot(config);
		twitchRobot.run();
	}

	private List<String> allowedChoices;
	private Robot inputBot;
	private PircBotX ircBot;

	public TwitchRobot(TwitchConfig config) throws AWTException {
		
		Configuration<PircBotX> configuration = new TwitchBuilder()
	        .setLogin(IRC_NAME)
	        .setRealName(IRC_NAME)
	        .setAutoNickChange(true)
	        
	        .setName(config.username)
	        .setServerHostname(config.server)
	        .setServerPort(config.port)
	        .setServerPassword(config.password)
	        .addAutoJoinChannel(config.channel)
	        
	        .addListener(new TwitchListener(this))
		.buildConfiguration();
		
		ircBot = new PircBotX(configuration);

		allowedChoices = Arrays.asList("up", "down", "left", "right", "a", "b",
				"x", "y", "l", "r", "start", "select");
		
		inputBot = new Robot();
		inputBot.setAutoDelay(100); // required to register
		// inputBot.setAutoWaitForIdle(false);
		
		//Connect to server
		
		
		
	}
	
	public void run() throws IOException, IrcException {
		ircBot.startBot();
	}

	public boolean perform(String choice) {

		if (!allowedChoices.contains(choice)) {
			return false;
		}

		switch (choice) {
			case "up":
				inputBot.keyPress(KeyEvent.VK_NUMPAD8);
				inputBot.keyRelease(KeyEvent.VK_NUMPAD8);
				break;
			case "down":
				inputBot.keyPress(KeyEvent.VK_NUMPAD2);
				inputBot.keyRelease(KeyEvent.VK_NUMPAD2);
				break;
			case "left":
				inputBot.keyPress(KeyEvent.VK_NUMPAD4);
				inputBot.keyRelease(KeyEvent.VK_NUMPAD4);
				break;
			case "right":
				inputBot.keyPress(KeyEvent.VK_NUMPAD6);
				inputBot.keyRelease(KeyEvent.VK_NUMPAD6);
				break;
			case "a":
				inputBot.keyPress(KeyEvent.VK_NUMPAD3);
				inputBot.keyRelease(KeyEvent.VK_NUMPAD3);
				break;
			case "b":
				inputBot.keyPress(KeyEvent.VK_NUMPAD1);
				inputBot.keyRelease(KeyEvent.VK_NUMPAD1);
				break;
			case "x":
				inputBot.keyPress(KeyEvent.VK_NUMPAD9);
				inputBot.keyRelease(KeyEvent.VK_NUMPAD9);
				break;
			case "y":
				inputBot.keyPress(KeyEvent.VK_NUMPAD7);
				inputBot.keyRelease(KeyEvent.VK_NUMPAD7);
				break;
			case "l":
				inputBot.keyPress(KeyEvent.VK_SLASH);
				inputBot.keyRelease(KeyEvent.VK_SLASH);
				break;
			case "r":
				inputBot.keyPress(KeyEvent.VK_ASTERISK);
				inputBot.keyRelease(KeyEvent.VK_ASTERISK);
				break;
			case "start":
				inputBot.keyPress(KeyEvent.VK_NUMPAD0);
				inputBot.keyRelease(KeyEvent.VK_NUMPAD0);
				break;
			case "select":
				inputBot.keyPress(KeyEvent.VK_PERIOD);
				inputBot.keyRelease(KeyEvent.VK_PERIOD);
				break;
			default:
				return false;
		}

		return true;
	}

}