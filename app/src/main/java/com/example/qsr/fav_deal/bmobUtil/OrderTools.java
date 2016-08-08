package com.example.qsr.fav_deal.bmobUtil;

import android.content.Context;

import com.example.qsr.fav_deal.bean.Address;
import com.example.qsr.fav_deal.bean.Goods;
import com.example.qsr.fav_deal.bean.Order;
import com.example.qsr.fav_deal.bmobUtil.bean.BmobOrder;
import com.example.qsr.fav_deal.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**************************************
 * FileName : com.example.qsr.fav_deal.bmobUtil
 * Author : qsr
 * Time : 2016/8/7 1:08
 * Description :订单管理的工具类
 **************************************/
public class OrderTools {
    private Context context;
    private MesEventForBmob eventForBmob;
    private static OrderTools orderTools = null;
    public static final int CREATE_ORDER_SUCC = 35;//订单创建成功
    public static final int CREATE_ORDER_FAIL = 36;//订单创建失败
    public static final int ALL_ORDER_SUCC = 37;//获取全部订单成功
    public static final int ALL_ORDER_FAIL = 38;//获取全部订单失败
    public static final int GET_ORDER_SUCC = 39;//获取指定类型订单成功
    public static final int GET_ORDER_FAIL = 40;//获取指定类型订单失败
    //第一层同步
    public static synchronized OrderTools getInstance(Context context) {
        //第二层同步
        synchronized (AddressTools.class) {
            if(orderTools == null){
                orderTools = new OrderTools(context);
            }
        }
        return orderTools;
    }
    private OrderTools (Context context){
        this.context = context;
    }

    public void addOrder(final BmobOrder order) {
        eventForBmob = new MesEventForBmob();
        //注意：不能调用gameScore.setObjectId("")方法
        order.save(context, new SaveListener() {
            @Override
            public void onSuccess() {
                LogUtil.MyLog_e(context,"添加订单成功，准备进入更改用户");
                eventForBmob.setStateCode(CREATE_ORDER_SUCC);
                EventBus.getDefault().post(eventForBmob);
            }

            @Override
            public void onFailure(int i, String s) {
                eventForBmob.setString(s);
                eventForBmob.setStateCode(CREATE_ORDER_FAIL);
                EventBus.getDefault().post(eventForBmob);
                LogUtil.MyLog_e(context, s);
            }
        });
    }

    /**
     * 获取所有的订单
     */
    public void allOrder() {
        eventForBmob = new MesEventForBmob();
        BmobQuery<BmobOrder> query = new BmobQuery<BmobOrder>();
        query.addWhereEqualTo("u_id",BmobUser.getCurrentUser(context).getObjectId());
        query.findObjects(context, new FindListener<BmobOrder>() {
            @Override
            public void onSuccess(List<BmobOrder> list) {
                eventForBmob.setObject(BmobOrder.BmobOrderToOrder(list));
                eventForBmob.setStateCode(ALL_ORDER_SUCC);
                EventBus.getDefault().post(eventForBmob);
                LogUtil.MyLog_e(context, list.toString());
            }

            @Override
            public void onError(int i, String s) {
                eventForBmob.setString(s);
                eventForBmob.setStateCode(ALL_ORDER_FAIL);
                EventBus.getDefault().post(eventForBmob);
                LogUtil.MyLog_e(context, s);
            }
        });
    }
    /**
     * 获取指定类型的订单
     */
    public void getTypeOrder(final int type) {
        eventForBmob = new MesEventForBmob();
        BmobQuery<BmobOrder> query = new BmobQuery<BmobOrder>();
        query.addWhereEqualTo("o_state",type+"");//获取指定的类型订单
        query.addWhereEqualTo("u_id", BmobUser.getCurrentUser(context).getObjectId());
        query.findObjects(context, new FindListener<BmobOrder>() {
            @Override
            public void onSuccess(List<BmobOrder> list) {
                eventForBmob.setObject(BmobOrder.BmobOrderToOrder(list));
                eventForBmob.setStateCode(GET_ORDER_SUCC);
                eventForBmob.setString(type+"");
                EventBus.getDefault().post(eventForBmob);
                LogUtil.MyLog_e(context, list.toString()+"成功了");
            }

            @Override
            public void onError(int i, String s) {
                eventForBmob.setString(s);
                eventForBmob.setStateCode(GET_ORDER_FAIL);
                EventBus.getDefault().post(eventForBmob);
                LogUtil.MyLog_e(context, s);
            }
        });
    }
}
