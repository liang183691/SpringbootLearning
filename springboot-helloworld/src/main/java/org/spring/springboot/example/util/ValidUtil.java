package org.spring.springboot.example.util;

public class ValidUtil {
    public static boolean validRepeated(String str){
        String regStr = "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
        return str.matches(regStr);
    }
}
