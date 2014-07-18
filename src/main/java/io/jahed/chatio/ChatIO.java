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
        TypeReference<HashMap<String, String>> hashMapTypeRef = new TypeReference<HashMap<String, String>>(){};
        Map<String,String> keyMap = mapper.readValue(new File(args[1]), hashMapTypeRef);
        
        ChatRobot chatRobot = new ChatRobot(config, keyMap);
        
        System.out.println("\nStarting robot in 5 seconds, focus your window...");
        Thread.sleep(5000);
        System.out.println(
            "\nRobot Started | press CTRL + C to stop" +
            "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
        );
        chatRobot.run();
    }
    
}
