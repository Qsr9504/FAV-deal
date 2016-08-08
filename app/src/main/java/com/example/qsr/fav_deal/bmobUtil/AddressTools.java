package com.example.qsr.fav_deal.bmobUtil;

import android.content.Context;
import android.util.Log;

import com.example.qsr.fav_deal.bean.Address;
import com.example.qsr.fav_deal.bean.Goods;
import com.example.qsr.fav_deal.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**************************************
 * FileName : com.example.qsr.fav_deal.bmobUtil
 * Author : qsr
 * Time : 2016/8/7 1:05
 * Description : 地址管理的工具类
 **************************************/
public class AddressTools {
    private Context context;
    private MesEventForBmob eventForBmob;
    private static AddressTools addressTools = null;
    public static final int ALL_ADDRESS_ERROR = 13;//获取所有地址列表失败
    public static final int ALL_ADDRESS_SUCC = 14;//获取所有地址列表成功

    public static final int DEFAULT_ADDRESS_SUCC = 15;//获取默认地址列表成功
    public static final int DEFAULT_ADDRESS_ERROE = 16;//获取默认地址列表失败

    public static final int SAVE_ADDRESS_SUCC = 17;//保存地址成功
    public static final int SAVE_ADDRESS_ERROE = 18;//保存地址失败

    public static final int DELETE_ADDRESS_SUCC = 19;//删除地址成功
    public static final int DELETE_ADDRESS_ERROE = 20;//删除地址失败

    public static final int UPDATE_ADDRESS_SUCC = 21;//更新地址成功
    public static final int UPDATE_ADDRESS_ERROE = 22;//更新地址失败
    //第一层同步
    public static synchronized AddressTools getInstance(Context context) {
        //第二层同步
        synchronized (AddressTools.class) {
            if(addressTools == null){
                addressTools = new AddressTools(context);
            }
        }
        return addressTools;
    }
    private AddressTools (Context context){
        this.context = context;
    }
    /**
     * 全部地址
     */
    public void getAllAddress(String id) {
        eventForBmob = new MesEventForBmob();
        BmobQuery<Address> query = new BmobQuery<Address>();
        query.addWhereEqualTo("u_id",id);
        query.findObjects(context, new FindListener<Address>() {
            @Override
            public void onSuccess(List<Address> list) {
                eventForBmob.setObject(list);
                eventForBmob.setStateCode(ALL_ADDRESS_SUCC);
                EventBus.getDefault().post(eventForBmob);
                LogUtil.MyLog_e(context, list.toString());
            }

            @Override
            public void onError(int i, String s) {
                eventForBmob.setString(s);
                eventForBmob.setStateCode(ALL_ADDRESS_ERROR);
                EventBus.getDefault().post(eventForBmob);
                LogUtil.MyLog_e(context, s);
            }
        });
    }
    /**
     * 获取默认地址
     */
    public void getDefaultAddress() {
        eventForBmob = new MesEventForBmob();
        BmobQuery<Address> query = new BmobQuery<Address>();
        query.findObjects(context, new FindListener<Address>() {
            @Override
            public void onSuccess(List<Address> list) {
                eventForBmob.setObject(list);
                eventForBmob.setStateCode(DEFAULT_ADDRESS_SUCC);
                EventBus.getDefault().post(eventForBmob);
                LogUtil.MyLog_e(context, list.toString());
            }

            @Override
            public void onError(int i, String s) {
                eventForBmob.setString(s);
                eventForBmob.setStateCode(DEFAULT_ADDRESS_ERROE);
                EventBus.getDefault().post(eventForBmob);
                LogUtil.MyLog_e(context, s);
            }
        });
    }

    public void addAddress(final Address address) {
        eventForBmob = new MesEventForBmob();
        //注意：不能调用gameScore.setObjectId("")方法
        address.save(context, new SaveListener() {
            @Override
            public void onSuccess() {
                eventForBmob.setStateCode(SAVE_ADDRESS_SUCC);
                eventForBmob.setObject(address);
                EventBus.getDefault().post(eventForBmob);
            }

            @Override
            public void onFailure(int i, String s) {
                eventForBmob.setString(s);
                eventForBmob.setStateCode(SAVE_ADDRESS_ERROE);
                EventBus.getDefault().post(eventForBmob);
                LogUtil.MyLog_e(context, s);
            }
        });
    }

    public void delete(String id) {
        Address address = new Address();
        address.setObjectId(id);
        address.delete(context, new DeleteListener() {
            @Override
            public void onSuccess() {
                eventForBmob.setStateCode(DELETE_ADDRESS_SUCC);
                EventBus.getDefault().post(eventForBmob);
            }

            @Override
            public void onFailure(int i, String s) {
                eventForBmob.setString(s);
                eventForBmob.setStateCode(DELETE_ADDRESS_ERROE);
                EventBus.getDefault().post(eventForBmob);
                LogUtil.MyLog_e(context, s);
            }
        });
    }

    public void updateAdd(String id, Address address) {
        address.update(context, new UpdateListener() {
            @Override
            public void onSuccess() {
                eventForBmob.setStateCode(UPDATE_ADDRESS_SUCC);
                EventBus.getDefault().post(eventForBmob);
            }

            @Override
            public void onFailure(int i, String s) {
                eventForBmob.setStateCode(UPDATE_ADDRESS_ERROE);
                EventBus.getDefault().post(eventForBmob);
            }
        });
    }
}
