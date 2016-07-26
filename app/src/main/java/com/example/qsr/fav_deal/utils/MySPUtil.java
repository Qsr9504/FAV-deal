package com.example.qsr.fav_deal.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.example.qsr.fav_deal.globle.App;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**************************************
 * FileName : com.example.qsr.fav_deal.utils
 * Author : qsr
 * Time : 2016/7/26 12:50
 * Description : sharePreferences的工具类
 **************************************/
public class MySPUtil {
    private static MySPUtil mySPUtil = null;
    private static SharedPreferences sp = null;
    private static SharedPreferences.Editor editor;
    public static SharedPreferences  getInstance(){
        if(sp == null) {
            sp = App.mContext.getSharedPreferences("config", Context.MODE_PRIVATE);
            editor = sp.edit();
        }
        return sp;
    }

    /**
     * 清除方法
     * @return
     */
    public static boolean clear() {
        editor.clear();
        return editor.commit();
    }

    /**
     * 判断包含
     * @param key
     * @return
     */
    public static boolean contains(String key) {
        return sp.contains(key);
    }

    /**
     * 取布尔值
     * @param key
     * @return
     */
    public static Boolean getBoolean(String key) {
        return Boolean.valueOf(sp.getBoolean(key, false));
    }

    /**
     * 取布尔值，带默认值
     * @param key
     * @param defVal
     * @return
     */
    public static Boolean getBoolean(String key, boolean defVal) {
        return Boolean.valueOf(sp.getBoolean(key, defVal));
    }

    /**
     * 取整型
     * @param key
     * @return
     */
    public static Integer getInt(String key) {
        return Integer.valueOf(sp.getInt(key, -1));
    }

    /**
     * 取整型，带默认值
     * @param key
     * @param defVal
     * @return
     */
    public static Integer getInt(String key, int defVal) {
        return Integer.valueOf(sp.getInt(key, defVal));
    }

    /**
     * 取长整型
     * @param key
     * @return
     */
    public static Long getLong(String key) {
        return Long.valueOf(sp.getLong(key, -1L));
    }

    /**
     * 取长整型，带默认值
     * @param key
     * @param defVal
     * @return
     */
    public static Long getLong(String key, long defVal) {
        return Long.valueOf(sp.getLong(key, defVal));
    }

    /**
     * 取浮点型
     * @param key
     * @return
     */
    public static Float getFloat(String key) {
        return Float.valueOf(sp.getFloat(key, -1));
    }

    /**
     * 取浮点型，带默认值
     * @param key
     * @param defVal
     * @return
     */
    public static Float getFloat(String key, float defVal) {
        return Float.valueOf(sp.getFloat(key, defVal));
    }

    /**
     * 取字符串
     * @param key
     * @return
     */
    public static String getString(String key) {
        return sp.getString(key, "");
    }

    /**
     * 取字符串，带默认值
     * @param key
     * @param defVal
     * @return
     */
    public static String getString(String key, String defVal) {
        return sp.getString(key, defVal);
    }


    /**
     * 获取序列化对象
     * @param key
     * @return
     */
    public static Serializable getSerializable(String key) {
        String str = getString(key,"");
        if(TextUtil.isEmpty(str)) {
            return null;
        }
        // 解析对象字符串
        ByteArrayInputStream bais = new ByteArrayInputStream(Base64.encode(str.getBytes(), 0));
        try {
            ObjectInputStream ois = new ObjectInputStream(bais);
            Serializable obj = (Serializable) ois.readObject(); // 将字符串对象，反序列化成实体对象
            ois.close();
            bais.close();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 移除对象
     * @param key
     * @return
     */
    public static boolean remove(String key) {
        editor.remove(key);
        return editor.commit();
    }

    /**
     * 移除多个对象
     * @return
     */
    public static boolean removeKeys(String[] keys) {
        for (String key : keys) {
            editor.remove(key);
        }
        return editor.commit();
    }

    /**
     * 保存布尔类型
     * @param key
     * @param value
     * @return
     */
    public static boolean save(String key, Boolean value) {
        editor.putBoolean(key, value);
        return editor.commit();
    }

    /**
     * 保存整型
     * @param key
     * @param value
     * @return
     */
    public static boolean save( String key, Integer value) {
        editor.putInt(key, value);
        return editor.commit();
    }

    /**
     * 保存长整型
     * @param key
     * @param value
     * @return
     */
    public static boolean save(String key, Long value) {
        editor.putLong(key, value);
        return editor.commit();
    }

    /**
     * 保存浮点类型
     * @param key
     * @param value
     * @return
     */
    public static boolean save(String key, Float value) {
        editor.putFloat(key, value);
        return editor.commit();
    }

    /**
     * 保存字符串
     * @param key
     * @param value
     * @return
     */
    public static boolean save(String key, String value) {
        editor.putString(key, value);
        return editor.commit();
    }

    // 保存序列化对象

    public static boolean save(String key, Object value) {
        if(value instanceof Integer)
            return save(key, (Integer)value);
        if(value instanceof Long)
            return save(key, (Long)value);
        if(value instanceof Float)
            return save(key, (Float)value);
        if(value instanceof Boolean)
            return save(key, (Boolean)value);
        if(value instanceof String)
            return save(key, (String)value);
        return false;
    }
}
