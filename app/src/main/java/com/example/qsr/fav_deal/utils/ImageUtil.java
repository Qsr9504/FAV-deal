package com.example.qsr.fav_deal.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.qsr.fav_deal.globle.App;

import java.io.InputStream;

/**************************************
 * FileName : com.example.qsr.fav_deal.utils
 * Author : qsr
 * Time : 2016/8/1 23:00
 * Description :
 **************************************/
public class ImageUtil {
    /**
     * 以最节省内存的方法读取本地图片
     * @param resId
     * @return
     */
    public static Bitmap readBitMap(int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        //获取资源图片
        InputStream is = App.mContext.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }
}
