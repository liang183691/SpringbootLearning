package org.spring.springboot.example.core;

import org.spring.springboot.example.util.FileOperate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Transform {
    List<String> allList = null;
    Map<String,String> symbleMap = null;
    Map<String,Integer> priceMap = null;
    //提取处理txt数据
    public List<String> transTxtInfoToMap() throws Exception{
        allList = FileOperate.readFile(getTxtUrl());
        System.out.println("Test Input:");
        for (String s : allList){
            System.out.println(s);
        }
        //分析的list
        List<String> transList = new ArrayList<String>(allList.size());
        transList.addAll(allList);
        //symble别名
        symbleMap = getSymbleMap(transList);
        transList = new ArrayList<String>();
        transList.addAll(allList);
        //货币单价
        priceMap = getPriceMap(transList);
        List<String> resultList = caculateRsult(allList);
        return resultList;
    }

    public List<String> caculateRsult(List<String> list) throws Exception{
        List<String> resultList = new ArrayList<String>(list.size());
        String reg = "^how much is.*?$";
        String creditsReg = "^how many Credits is.*?$";
        for(String ques : list){
            if(ques.matches(reg)){
                String printStr = ques.substring(ques.indexOf("is ")+3,ques.indexOf(" ?"));
                String[] seq =printStr.split(" ");
                String sybleStr = "";
                try{
                    for(String str : seq){
                        sybleStr = sybleStr + symbleMap.get(str);
                    }
                    int total = transSymbleToNum(sybleStr);
                    resultList.add(printStr +" is " + total);
                }catch (Exception e){
                    resultList.add("I have no idea what you are talking about");
                    throw new Exception(e);
                }
            }else if(ques.matches(creditsReg)){
                String printStr = ques.substring(ques.indexOf("is ")+3,ques.indexOf(" ?"));
                String[] seq =printStr.split(" ");
                String sybleStr = "";
                try{
                    for(int i = 0;i<seq.length-1;i++){
                        sybleStr = sybleStr + symbleMap.get(seq[i]);
                    }
                    String corn = seq[seq.length - 1];
                    int total = transSymbleToNum(sybleStr) * priceMap.get(corn);
                    resultList.add(printStr +" is " + total + " Credits");
                }catch (Exception e){
                    resultList.add("I have no idea what you are talking about");
                    throw new Exception(e);
                }
            }else {
                resultList.add("I have no idea what you are talking about");
            }
        }
        return resultList;
    }

    public Map<String,String> getSymbleMap(List<String> list){
        String reg = "^[a-z].*[IVXLCDM]$";
        Map<String,String> map = new HashMap<String, String>();
        for(String str : list){
            if(str.matches(reg)){
                String[] seq = str.split(" ");
                map.put(seq[0],seq[2]);
                allList.remove(str);
            }
        }
        return map;
    }

    public Map<String,Integer> getPriceMap(List<String> list) throws Exception{
        String reg = "^[a-z].*Credits$";
        Map<String,Integer> map = new HashMap<String, Integer>();
        for(String str : list){
            if(str.matches(reg)){
                String symbleStr = "";
                String[] seq = str.substring(0,str.indexOf("is ")-1).split(" ");
                String totalPrice = str.substring(str.indexOf("is ")+3,str.indexOf("Credits")-1);
                for(int i = 0;i<seq.length-1;i++){
                    symbleStr = symbleStr + symbleMap.get(seq[i]);
                }
                int num = transSymbleToNum(symbleStr);
                int price = Integer.parseInt(totalPrice)/num;
                map.put(seq[seq.length-1],price);
                allList.remove(str);
            }
        }
        return map;
    }

    public int transSymbleToNum(String symble) throws Exception{
        int num = 0;
        char[] ch = symble.toCharArray();
        for(int i = 0;i<ch.length;i++){
            if('M' == ch[i]){
                num = num + 1000;
            } else if('D' == ch[i]){
                num = num + 500;
            } else if('C' == ch[i]){
                if(i<ch.length - 1) {
                    if ('M' == ch[i + 1]) {
                        num = num + 900;
                        i++;
                    } else if ('D' == ch[i + 1]) {
                        num = num + 400;
                        i++;
                    }else{
                        num = num + 100;
                    }
                }else {
                    num = num + 100;
                }
            } else if('L' == ch[i]){
                num = num + 50;
            } else if('X' == ch[i]){
                if(i<ch.length - 1) {
                    if ('C' == ch[i + 1]) {
                        num = num + 90;
                        i++;
                    } else if ('L' == ch[i + 1]) {
                        num = num + 40;
                        i++;
                    }else{
                        num = num + 10;
                    }
                }else {
                    num = num + 10;
                }
            } else if('V' == ch[i]){
                num = num + 5;
            } else if('I' == ch[i]){
                if(i<ch.length - 1) {
                    if ('X' == ch[i + 1]) {
                        num = num + 9;
                        i++;
                    } else if ('V' == ch[i + 1]) {
                        num = num + 4;
                        i++;
                    }else{
                        num = num + 1;
                    }
                }else {
                    num = num + 1;
                }
            } else{
                throw new Exception("非法的字符串");
            }
        }
        return num;
    }

    public String getTxtUrl(){
        String url = this.getClass().getClassLoader().getResource("input.txt").getPath();
        return url;
    }
}
