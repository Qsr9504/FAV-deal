package com.example.qsr.fav_deal.utils;

import android.text.TextUtils;

/**************************************
 * FileName : com.example.qsr.fav_deal.utils
 * Author : qsr
 * Time : 2016/7/26 12:55
 * Description :
 **************************************/
public class TextUtil {
    /**
     * 判断字符串是否为空
     */
    public static boolean isEmpty(String ... strings){
        for (String s: strings){
            if(TextUtils.isEmpty(s)){
                return true;
            }
        }
        return false;
    }
}
