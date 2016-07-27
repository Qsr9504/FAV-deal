package com.example.qsr.fav_deal.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.qsr.fav_deal.globle.App;
import com.example.qsr.fav_deal.globle.AppConstants;
import com.example.qsr.fav_deal.utils.LogUtil;

/**************************************
 * FileName : com.example.qsr.fav_deal.db
 * Author : qsr
 * Time : 2016/7/27 9:39
 * Description : 数据库建立
 **************************************/
public class MyOpenHelper extends SQLiteOpenHelper {
    private static MyOpenHelper openHelper = null;
    public synchronized static MyOpenHelper getInstance(Context context){
        if(openHelper == null){
            openHelper = new MyOpenHelper(context);
        }
        return openHelper;
    }
    private MyOpenHelper(Context context) {
        super(context, AppConstants.myDB.DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(AppConstants.mySQL.CREATE_TABLE);
        LogUtil.MyLog_e(App.mContext,"数据库创建成功");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
