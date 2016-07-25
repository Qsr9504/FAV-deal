package com.example.qsr.fav_deal.globle;

import android.app.Activity;

import java.util.Stack;

/**************************************
 * FileName : com.example.qsr.fav_deal.globle
 * Author : qsr
 * Time : 2016/6/10 17:47
 * Description : 统一程序当中所有的activity栈管理
 * 增加，删除指定、当前的activity，或者删除所有的activity
 * 求栈大小
 **************************************/
public class AppManager {
    private static AppManager appManager = null;
    private Stack<Activity> activityStack = new Stack<Activity>();
    private AppManager(){};
    public static AppManager getInstance(){
        if(appManager == null){
            appManager = new AppManager();
        }
        return appManager;
    }
    //添加一个activity
    public void addActivity(Activity activity){
        activityStack.add(activity);
    }

    //删除一个指定的activity
    public void removeActivity(Activity activity){
        for (int i = activityStack.size() - 1; i >= 0 ;i-- ) {
            Activity activity1 = activityStack.get(i);
            if(activity1.getClass().equals(activity.getClass())){
                activity1.finish();
                activityStack.remove(activity);
                break;
            }
        }
    }

    //删除当前的activity
    public void removeCurrent(){
        Activity lastElement = activityStack.lastElement();
        lastElement.finish();
        activityStack.remove(lastElement);
    }

    //删除所有的activity，当程序退出时，清空
    public void removeAll(){
        for (int i = activityStack.size() - 1 ; i >= 0; i--){
            Activity a = activityStack.get(i);
            a.finish();
            activityStack.remove(a);
        }
    }

    //查看当前栈中有多少activity
    public int getSize(){
        return activityStack.size();
    }


}
