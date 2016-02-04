package io.jahed.crowd_play;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.cli.*;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

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
            printVersion();
        }

        if(cmd.hasOption("help") || !cmd.hasOption("config") || !cmd.hasOption("keys")) {
            printHelp(options, cmd.hasOption("help"));
        }

        System.out.print("Parsing Config...");
        String configPath = cmd.getOptionValue("config");
        ChatBotConfig config = ChatBotConfig.parse(configPath);
        System.out.println(" OK");

        System.out.print("Parsing Keys...");
        String keysPath = cmd.getOptionValue("keys");
        KeyMap keyMap = KeyMap.parse(keysPath);
        System.out.println(" OK");

        ChatBot chatBot = new ChatBot(config, keyMap);

        System.out.println("\nCrowd Play");
        System.out.println("\nStarting bot in 5 seconds, focus your window...");

        Thread.sleep(5000);

        System.out.println("\nBot Started | press CTRL + C to stop\n");
        if(cmd.hasOption("blank")) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
        chatBot.start();
    }

    private static void printHelp(Options options, boolean asOption) {
        HelpFormatter formatter = new HelpFormatter();
        System.out.println();
        formatter.printHelp("java -jar crowd-play.jar --config <config json> --keys <keys json>", options);

        System.exit(asOption ? 0 : -1);
    }

    private static void printVersion() {
        System.out.println(VERSION);
        System.exit(0);
    }

    private static ChatBotConfig getConfig(ObjectMapper mapper, String path) throws Exception {
        return mapper.readValue(new File(path), ChatBotConfig.class);
    }

    private static Map<String,Integer> getKeyMap(ObjectMapper mapper, String path) throws Exception {
        TypeReference<HashMap<String, String>> hashMapTypeRef = new TypeReference<HashMap<String, String>>(){};
        Map<String, String> keyMapConfig = mapper.readValue(new File(path), hashMapTypeRef);
        return parseKeyMap(keyMapConfig);
    }

    private static HashMap<String, Integer> parseKeyMap(Map<String, String> keyMapConfig) throws Exception {
        HashMap<String, Integer> keyMap = new HashMap<>();
        for(String word : keyMapConfig.keySet()) {
            Integer keyCode = (Integer)(KeyEvent.class.getField(keyMapConfig.get(word)).get(null));
            keyMap.put(word, keyCode);
        }
        return keyMap;
    }

}
