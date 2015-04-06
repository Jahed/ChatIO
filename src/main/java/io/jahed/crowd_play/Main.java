package io.jahed.crowd_play;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.cli.*;

public class Main {

    public static final String VERSION = "1.0.0";

    public static void main(String[] args) throws Exception {

        Options options = new Options()
            .addOption("c", "config", true, "configuration json")
            .addOption("k", "keys", true, "key mapping json")
            .addOption("b", "blank", false, "hide setup logging when ready")
            .addOption("h", "help", false, "this help")
            .addOption("v", "version", false, "version number");

        CommandLineParser parser = new PosixParser();
        CommandLine cmd = parser.parse(options, args);

        if(cmd.hasOption("version")) {
            System.out.println(VERSION);
            System.exit(0);
        }

        if(!cmd.hasOption("config") || !cmd.hasOption("keys") || cmd.hasOption("help")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar crowd-play.jar --config <config json> --keys <keys json>", options);

            System.exit(cmd.hasOption("help") ? 0 : -1);
        }

        String configPath = cmd.getOptionValue("config");
        String keysPath = cmd.getOptionValue("keys");

        ObjectMapper mapper = new ObjectMapper();
        ChatConfig config = mapper.readValue(new File(configPath), ChatConfig.class);
        TypeReference<HashMap<String, String>> hashMapTypeRef = new TypeReference<HashMap<String, String>>(){};
        Map<String,String> keyMap = mapper.readValue(new File(keysPath), hashMapTypeRef);
        
        ChatRobot chatRobot = new ChatRobot(config, keyMap);

        System.out.println("\nCrowd Play");
        System.out.println("\nStarting robot in 5 seconds, focus your window...");
        Thread.sleep(5000);
        System.out.println("\nRobot Started | press CTRL + C to stop\n");

        if(cmd.hasOption("blank")) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }

        chatRobot.run();
    }
    
}
