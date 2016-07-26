package com.example.qsr.fav_deal.utils;

import android.content.Context;
import android.util.Log;

/**************************************
 * FileName : com.example.qsr.p2pfinance.utils
 * Author : qsr
 * Time : 2016/6/11 10:24
 * Description : Log的统一封装
 **************************************/
public class LogUtil {
    private static boolean isOpen = true;
    public static void openLog(boolean b){
        isOpen = b;
    }
    public static void MyLog_i(Context context,String str){
        if(isOpen){
            Log.i(context.getClass().getSimpleName(),str);
        }
    }
    public static void MyLog_e(Context context,String str){
        if(isOpen){
            Log.e(context.getClass().getSimpleName(),str);
        }
    }
}
