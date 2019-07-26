package org.spring.springboot.example.util;

import java.util.HashMap;
import java.util.Map;

public class Enum {
    public static Map<String,String> symbleMap = new HashMap<String,String>();
    static {
        symbleMap.put("I","1");
        symbleMap.put("V","5");
        symbleMap.put("X","10");
        symbleMap.put("L","50");
        symbleMap.put("C","100");
        symbleMap.put("D","500");
        symbleMap.put("M","1000");
    }
}
