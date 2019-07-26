package org.spring.springboot.example.main;

import javafx.collections.transformation.TransformationList;
import org.spring.springboot.example.core.Transform;
import org.spring.springboot.example.util.FileOperate;
import org.spring.springboot.example.util.ValidUtil;
import sun.security.util.Resources;

import java.net.URL;
import java.util.List;

public class Main {
    public static void main(String args[]){
        //String str = "IIIIM";
        String str = "MXCVI";

        URL filePath = FileOperate.class.getResource("/");
        /*try {
            System.out.println(new Transform().transSymbleToNum("MCMCMIII"));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        // System.out.println(ValidUtil.validRepeated(str));
        //System.out.println(ValidUtil.validRepeatedOne(str));
        String s = "^how much is.*?$";
        //System.out.println("how much wood pish tegj glob glob ?".matches(s));
        try {
            List<String> list = new Transform().transTxtInfoToMap();
            System.out.println();
            System.out.println("Test Output");
            for (String ss : list){
                System.out.println(ss);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
