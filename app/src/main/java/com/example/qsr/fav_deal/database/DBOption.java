package com.example.qsr.fav_deal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.qsr.fav_deal.globle.App;
import com.example.qsr.fav_deal.globle.AppConstants;
import com.example.qsr.fav_deal.utils.LogUtil;

/**************************************
 * FileName : com.example.qsr.fav_deal.database
 * Author : qsr
 * Time : 2016/7/27 10:26
 * Description : 数据库具体操作
 **************************************/
public class DBOption implements DBDao {
    private MyOpenHelper openHelper;
    private ContentValues values;

    private SQLiteDatabase db;
    public SQLiteDatabase getDb() {
        return db;
    }
    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    public DBOption(Context context) {
        this.openHelper = MyOpenHelper.getInstance(context);
        this.db = this.openHelper.getWritableDatabase();
        LogUtil.MyLog_e(context,"初始化dbOption成功");
    }

    @Override
    public void insertData(String url, String content,String time) {
        values = new ContentValues();
        values.put("url",url);
        values.put("content",content);
        values.put("time",time);
        db.insert(AppConstants.myDB.DB_TABLE, null, values);
        LogUtil.MyLog_e(App.mContext,"插入数据成功：\n url = "+url + "\n content = " +content + "\n time ="+time );
    }

    @Override
    public void deleteData(String url) {
        db.delete(AppConstants.myDB.DB_TABLE,"url=?",new String[]{url});
        LogUtil.MyLog_e(App.mContext,"删除数据成功：\n url = "+url);
    }

    @Override
    public void updateData(String url, String content, String time) {
        values = new ContentValues();
        values.put("url",url);
        values.put("content",content);
        values.put("time",time);
        db.update(AppConstants.myDB.DB_TABLE,values,"url=?",new String[]{url});
        LogUtil.MyLog_e(App.mContext,"更新数据成功：\n url = "+url + "\n content = " +content + "\n time ="+time );
    }

    @Override
    public String queryData(String url) {
        Cursor cursor = db.query(AppConstants.myDB.DB_TABLE,null,"url = ?",new String[]{url},null,null,null);

        if(cursor.moveToNext()){
            LogUtil.MyLog_e(App.mContext,"查询数据成功：\n url = "+ url + "\n content = " + cursor.getString(2) +
                    "\n time ="+ cursor.getString(3));
            return cursor.getString(2);
        }else
            return null;
    }

    @Override
    public String queryDataTime(String url) {
        Cursor cursor = db.query(AppConstants.myDB.DB_TABLE,null,"url = ?",new String[]{url},null,null,null);
        if(cursor.moveToNext()){
            return cursor.getString(3);
        }else
            return null;
    }
}
