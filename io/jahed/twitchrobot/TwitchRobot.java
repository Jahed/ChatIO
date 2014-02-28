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

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TwitchRobot {
	
	public static final String IRC_NAME = "TwitchRobot";
	
	public static void main(String[] args) throws Exception {
		//Temporary while there's no error-checking
		System.out.println("\nIncase something goes wrong, to run the program properly use:\n" +
				"\tjava -jar twitch-robot.jar \"config_path.json\" \"keys_path.json\" \n");

		ObjectMapper mapper = new ObjectMapper();
		
		TwitchConfig config = mapper.readValue(new File(args[0]), TwitchConfig.class);
		
	    TypeReference<HashMap<String,String>> hashMapTypeRef = new TypeReference<HashMap<String,String> >() {}; 
	    Map<String,String> keyMap = mapper.readValue(new File(args[1]), hashMapTypeRef); 
	    
		TwitchRobot twitchRobot = new TwitchRobot(config, keyMap);
		
		System.out.println("\nStarting robot in 5 seconds, focus your window...");
		Thread.sleep(5000);
		System.out.println("\nRobot Started | press CTRL + C to stop\n");
		twitchRobot.run();
	}

	private Robot inputBot;
	private PircBotX ircBot;
	private Map<String, Integer> keyMap;

	public TwitchRobot(TwitchConfig config, Map<String, String> stringKeyMap) throws Exception {
		
		System.out.println("Parsing Keys");
		this.setKeyMap(stringKeyMap);
		
		System.out.println("Setting up IRC Configuration");
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

		System.out.println("Creating Robot");
		inputBot = new Robot();
		inputBot.setAutoDelay(100); // required to register key presses
		// inputBot.setAutoWaitForIdle(false);
	}
	
	private void setKeyMap(Map<String, String> stringKeyMap) throws Exception {
		this.keyMap = new HashMap<String, Integer>();
		
		for(String word : stringKeyMap.keySet()) {
			Integer keyCode = (Integer)KeyEvent.class.getField(stringKeyMap.get(word)).get(null);
			this.keyMap.put(word, keyCode);
		}
	}
	
	public Map<String, Integer> getKeyMap() {
		return keyMap;
	}
	
	public void run() throws IOException, IrcException {
		ircBot.startBot();		
	}

	public boolean perform(String choice) {
		Integer keyCode = keyMap.get(choice);
		
		if(keyCode == null) {
			return false;
		}
		
		inputBot.keyPress(keyCode);
		inputBot.keyRelease(keyCode);

		return true;
	}

}