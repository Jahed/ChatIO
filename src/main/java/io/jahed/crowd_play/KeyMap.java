package io.jahed.crowd_play;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class KeyMap {

    private Map<String, Integer> map;

    public KeyMap(Map<String,Integer> map) {
        this.map = map;
    }

    public Integer getKeyCode(String key) throws IllegalArgumentException {
        Integer result = map.get(key);
        if(result == null) {
            throw new IllegalArgumentException("Key not configured: " + key);
        }
        return result;
    }

    public Set<String> getKeys() {
        return map.keySet();
    }

    public static KeyMap parse(String path) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String, String>> hashMapTypeRef = new TypeReference<HashMap<String, String>>(){};
        Map<String, String> keyMapConfig = mapper.readValue(new File(path), hashMapTypeRef);
        return parseKeyMap(keyMapConfig);
    }

    private static KeyMap parseKeyMap(Map<String, String> keyMapConfig) throws Exception {
        HashMap<String, Integer> map = new HashMap<>();
        for(String word : keyMapConfig.keySet()) {
            Integer keyCode = (Integer)(KeyEvent.class.getField(keyMapConfig.get(word)).get(null));
            map.put(word, keyCode);
        }
        return new KeyMap(map);
    }
}
