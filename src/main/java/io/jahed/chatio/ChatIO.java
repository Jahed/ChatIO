//
//  Copyright (C) 2014 Jahed Ahmed
//
//  Permission is hereby granted, free of charge, to any person obtaining a copy of
//  this software and associated documentation files (the "Software"), to deal in the
//  Software without restriction, including without limitation the rights to use, copy,
//  modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
//  and to permit persons to whom the Software is furnished to do so, subject to the
//  following conditions:
//  
//  The above copyright notice and this permission notice shall be included in all copies
//  or substantial portions of the Software.
//  
//  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
//  INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
//  PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
//  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
//  CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
//  OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
//

package io.jahed.chatio;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ChatIO {
	
    public static void main(String[] args) throws Exception {
        //Temporary while there's no error-checking
        System.out.println(
        	"\nIncase something goes wrong, to run the program properly use:\n" +
            "\tjava -jar chat-io.jar \"config_path.json\" \"keys_path.json\" \n"
        );

        ObjectMapper mapper = new ObjectMapper();
        
        ChatConfig config = mapper.readValue(new File(args[0]), ChatConfig.class);
        
        TypeReference<HashMap<String,String>> hashMapTypeRef = new TypeReference<HashMap<String,String> >() {}; 
        Map<String,String> keyMap = mapper.readValue(new File(args[1]), hashMapTypeRef); 
        
        ChatRobot chatRobot = new ChatRobot(config, keyMap);
        
        System.out.println("\nStarting robot in 5 seconds, focus your window...");
        Thread.sleep(5000);
        System.out.println("\nRobot Started | press CTRL + C to stop\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        chatRobot.run();
    }
    
}
