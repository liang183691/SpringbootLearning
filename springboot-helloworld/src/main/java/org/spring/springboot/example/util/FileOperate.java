package org.spring.springboot.example.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileOperate {
    public static List<String> readFile(String fileName) {
        List<String> arrayList = new ArrayList<String>();
        FileReader fr = null;
        BufferedReader bf = null;
        try {
            fr = new FileReader(fileName);
            bf = new BufferedReader(fr);
            String str = null;
            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
                arrayList.add(str);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                bf.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }
}
