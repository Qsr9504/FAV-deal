package com.example.qsr.fav_deal.globle;

/**************************************
 * FileName : com.example.qsr.fav_deal.globle
 * Author : qsr
 * Time : 2016/7/25 17:21
 * Description : 网络连接封装
 **************************************/
public class AppNetConfig {
//    public static String BASE_HOST = "http://192.168.3.62/Advertises/fav/"; //默认主机地址
//    public static String BASE_HOST = "http://192.168.3.62/FAV_deal/fav/"; //我的台式电脑主机地址
    public static String BASE_HOST = "http://192.168.3.8:8080/FAV_deal/"; //熊明主机地址
    public static String UPDATE = BASE_HOST + "version";      //检查更新的xml文件

    /* ********** 以下是主界面的url *********** */
    public static String ALL_FRUIT = BASE_HOST + "mainpage/all_fruit";      //获取所有的水果
    public static String ALL_VEG = BASE_HOST + "mainpage/all_veg";      //获取所有的蔬菜
    public static String TYPE_ALL = BASE_HOST + "mainpage/typeAll";      //获取指定种类的所有
    public static String GOOD_INFO = BASE_HOST + "mainpage/goodsById";      //获取指定的物品

}
