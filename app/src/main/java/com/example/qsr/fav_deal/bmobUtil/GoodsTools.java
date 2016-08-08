package com.example.qsr.fav_deal.bmobUtil;

import android.content.Context;

import com.example.qsr.fav_deal.bean.Goods;
import com.example.qsr.fav_deal.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**************************************
 * FileName : com.example.qsr.fav_deal.bmobUtil.bean
 * Author : qsr
 * Time : 2016/8/5 10:27
 * Description : 商品链接bmob后端云的工具类
 **************************************/
public class GoodsTools {
    private Context context;
    private MesEventForBmob eventForBmob;
    private static GoodsTools goodsTools = null;
    public static final int ALL_FRUIT_SUCC = 7;//获取水果列表成功
    public static final int ALL_FRUIT_ERROR = 8;//获取水果列表失败
    public static final int ALL_VEG_SUCC = 9;//获取水果列表成功
    public static final int ALL_VEG_ERROR = 10;//获取水果列表失败
    public static final int ALL_ORAN_SUCC = 11;//获取水果列表成功
    public static final int ALL_ORAN_ERROR = 12;//获取水果列表失败
    //第一层同步
    public static synchronized GoodsTools getInstance(Context context) {
        //第二层同步
        synchronized (GoodsTools.class) {
            if(goodsTools == null){
                goodsTools = new GoodsTools(context);
            }
        }
        return goodsTools;
    }
    private GoodsTools (Context context){
        this.context = context;
    }

    /**
     * 全部水果
     */
    public void getAllFruit() {
        eventForBmob = new MesEventForBmob();
        BmobQuery<Goods> query = new BmobQuery<Goods>();
        query.addWhereLessThanOrEqualTo("g_type", 100);//小于等于100都是水果
        query.findObjects(context, new FindListener<Goods>() {
            @Override
            public void onSuccess(List<Goods> list) {
                eventForBmob.setObject(list);
                eventForBmob.setStateCode(ALL_FRUIT_SUCC);
                EventBus.getDefault().post(eventForBmob);
                LogUtil.MyLog_e(context, list.toString());
            }

            @Override
            public void onError(int i, String s) {
                eventForBmob.setString(s);
                eventForBmob.setStateCode(ALL_FRUIT_ERROR);
                EventBus.getDefault().post(eventForBmob);
                LogUtil.MyLog_e(context, s);
            }
        });
    }

    /**
     * 全部蔬菜
     */
    public void getAllVeg(){
        eventForBmob = new MesEventForBmob();
        BmobQuery<Goods> query = new BmobQuery<Goods>();
        query.addWhereGreaterThan("g_type",100);//大于100都是蔬菜
        query.findObjects(context, new FindListener<Goods>() {
            @Override
            public void onSuccess(List<Goods> list) {
                eventForBmob.setObject(list);
                eventForBmob.setStateCode(ALL_VEG_SUCC);
                EventBus.getDefault().post(eventForBmob);
                LogUtil.MyLog_e(context,list.toString());
            }

            @Override
            public void onError(int i, String s) {
                eventForBmob.setString(s);
                eventForBmob.setStateCode(ALL_VEG_ERROR);
                EventBus.getDefault().post(eventForBmob);
                LogUtil.MyLog_e(context,s);
            }
        });
    }
    /**
     * 全部橙子
     */
    public void getAllOran(){
        eventForBmob = new MesEventForBmob();
        BmobQuery<Goods> query = new BmobQuery<Goods>();
        query.addWhereEqualTo("g_type",1);// 等于1是橙子
        query.findObjects(context, new FindListener<Goods>() {
            @Override
            public void onSuccess(List<Goods> list) {
                eventForBmob.setObject(list);
                eventForBmob.setStateCode(ALL_ORAN_SUCC);
                EventBus.getDefault().post(eventForBmob);
                LogUtil.MyLog_e(context,list.toString());
            }

            @Override
            public void onError(int i, String s) {
                eventForBmob.setString(s);
                eventForBmob.setStateCode(ALL_ORAN_ERROR);
                EventBus.getDefault().post(eventForBmob);
                LogUtil.MyLog_e(context,s);
            }
        });
    }

}
