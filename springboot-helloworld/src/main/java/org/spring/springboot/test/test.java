package org.spring.springboot.test;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class test {
    public static final Map<String,String> ERROR_CODE = new HashMap<String,String>();
    public static final User user = new User();
    public static void main(String[] args){
         ERROR_CODE.put("a","123");
         user.setName("1");
        System.out.println(user.getName());
    }
}
