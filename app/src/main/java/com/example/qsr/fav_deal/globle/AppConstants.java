package com.example.qsr.fav_deal.globle;

/**************************************
 * FileName : com.example.qsr.fav_deal.globle
 * Author : qsr
 * Time : 2016/7/25 17:22
 * Description : 常量封装
 **************************************/
public class AppConstants {
   public interface myDB{
        final String DB_NAME = "fav_data";
        final String DB_TABLE = "qsr";
    }
   public interface mySQL{
//       final String CREATE_TABLE = "create table qsr(_id integer primary key autoincrement" +
//               ",name text,classes text)";
        final String CREATE_TABLE = "create table " + myDB.DB_TABLE + "(_id integer primary key autoincrement,"
                + " url text," + "content text,time integer NOT NULL)";
    }
   public interface CONFIG{
       final String IS_GUIDE = "is_guide";//是否已经启动过欢迎界面
       final String OPEN_UPDATE = "open_update";//是否开启版本更新
       final String VERSION_CODE = "versionCode";//当前版本号
       final String CART = "cart";//本地购物车信息
    }
}
