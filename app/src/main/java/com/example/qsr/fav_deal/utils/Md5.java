package com.example.qsr.fav_deal.utils;

import java.security.MessageDigest;

/**************************************
 * FileName : com.example.qsr.fav_deal.utils
 * Author : qsr
 * Time : 2016/8/3 15:54
 * Description : Md5加密
 **************************************/
public class Md5 {
    private static String addSalt = "Qiao9504_*/";
    public static String encoder(String str){
        str += str + addSalt;
        try {
            //1.指定加密算法类型
            MessageDigest digest = MessageDigest.getInstance("MD5");
            //2.将需要加密的字符串中转化成byte类型的数组，然后随机哈希过程
            byte[] bs = digest.digest(str.getBytes());
            //3.循环遍历bs，然后让其生成32位字符串，这是固定写法
            //4.拼接字符串过程
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b:bs){
                //int类型的i需要转化成16机制字符
                int i = b & 0xff;
                String hexString = Integer.toHexString(i);
                //如果长度不够两位，补成0
                if(hexString.length() < 2){
                    hexString = "0" + hexString;
                }
                stringBuffer.append(hexString);
                return stringBuffer.toString();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
