package io.jahed.twitchrobot;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class TwitchRobot implements HttpHandler {
	
    public static void main(String[] args) throws InterruptedException, IOException, AWTException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8090), 0);
        TwitchRobot twitchRobot = new TwitchRobot();
        server.createContext("/recieve", twitchRobot);
        server.setExecutor(null);
        
        System.out.println("Starting in 5 seconds. Get the game window into the foreground!");

        Thread.sleep(5000);

        System.out.println("\n  ==  SERVER STARTED  ==  ");
        System.out.println("  Press CTRL+C to Close.");
        
        server.start();
    }
    
    private List<String> allowedChoices;
    private Robot robot;
    
    public TwitchRobot() throws AWTException {
    	allowedChoices = Arrays.asList("up", "down", "left", "right", "a", "b", "x", "y", "l", "r", "start", "select");
    	robot = new Robot();
    	robot.setAutoDelay(100); //required to register
//    	robot.setAutoWaitForIdle(false);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
    	
        if(!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
        	return;
        }
        
        try(Scanner scanner = new Scanner(exchange.getRequestBody())) {
        	if(!scanner.hasNext()) return;
        	
    		String token = scanner.next();
    		
    		if(token.charAt(1) == '»') { //for continuing lines
    			if(!scanner.hasNext()) {
    				System.out.println(" xx Rejected (" + token + ")");
    				return;
    			}
    			
				token = scanner.next();
    		}

        	System.out.println("Message from a User {");        	
    		perform(token); //only allow first line, first word
        	System.out.println("}\n");
        }
        
        exchange.getResponseBody().close();

    }
    
    public void perform(String choice) {
    	
    	if(!allowedChoices.contains(choice)) {
    		System.err.println("  xx Choice Rejected (" + choice + ")");
    		return;
    	}
    	
    	switch(choice) {
    		case "up":
    			robot.keyPress(KeyEvent.VK_NUMPAD8);
    			robot.keyRelease(KeyEvent.VK_NUMPAD8);
    			break;
    		case "down":
    			robot.keyPress(KeyEvent.VK_NUMPAD2);
    			robot.keyRelease(KeyEvent.VK_NUMPAD2);
    			break;
    		case "left":
    			robot.keyPress(KeyEvent.VK_NUMPAD4);
    			robot.keyRelease(KeyEvent.VK_NUMPAD4);
    			break;
    		case "right":
    			robot.keyPress(KeyEvent.VK_NUMPAD6);
    			robot.keyRelease(KeyEvent.VK_NUMPAD6);
    			break;
    		case "a":
    			robot.keyPress(KeyEvent.VK_NUMPAD3);
    			robot.keyRelease(KeyEvent.VK_NUMPAD3);
    			break;
    		case "b":
    			robot.keyPress(KeyEvent.VK_NUMPAD1);
    			robot.keyRelease(KeyEvent.VK_NUMPAD1);
    			break;
    		case "x":
    			robot.keyPress(KeyEvent.VK_NUMPAD9);
    			robot.keyRelease(KeyEvent.VK_NUMPAD9);
    			break;
    		case "y":
    			robot.keyPress(KeyEvent.VK_NUMPAD7);
    			robot.keyRelease(KeyEvent.VK_NUMPAD7);
    			break;
    		case "l":
    			robot.keyPress(KeyEvent.VK_SLASH);
    			robot.keyRelease(KeyEvent.VK_SLASH);
    			break;
    		case "r":
    			robot.keyPress(KeyEvent.VK_ASTERISK);
    			robot.keyRelease(KeyEvent.VK_ASTERISK);
    			break;
    		case "start":
    			robot.keyPress(KeyEvent.VK_NUMPAD0);
    			robot.keyRelease(KeyEvent.VK_NUMPAD0);
    			break;
    		case "select":
    			robot.keyPress(KeyEvent.VK_PERIOD);
    			robot.keyRelease(KeyEvent.VK_PERIOD);
    			break;
    		default: return;
    	}
    	
		System.out.println("  -- Choice Performed (" + choice + ")");

    }
}