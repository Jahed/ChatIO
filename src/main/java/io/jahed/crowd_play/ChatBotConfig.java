package io.jahed.crowd_play;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class ChatBotConfig {

    public String server;
    public Integer port;
    public String channel;
    public String username;
    public String password;
    public Boolean restricted;

    public static ChatBotConfig parse(String path) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(path), ChatBotConfig.class);
    }
    
}
