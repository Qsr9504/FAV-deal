package com.example.qsr.fav_deal.bmobUtil;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.example.qsr.fav_deal.bmobUtil.bean.Version;
import com.example.qsr.fav_deal.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

/**************************************
 * FileName : com.example.qsr.fav_deal.bmobUtil
 * Author : qsr
 * Time : 2016/8/4 12:39
 * Description : 版本检测
 **************************************/
public class VersionCheck {
    public static final int VERSION_EXIST = 4;//存在新版本
    public static final int VERSION_NO = 5;//查询新版本成功，已经是最新版本
    public static final int VERSION_ERROE = 6;//查询新版本失败
    private Context context;
    private BmobUser bmobUser;
    private MesEventForBmob eventForBmob;
    private static VersionCheck versionCheck;
    //第一层同步
    public static synchronized VersionCheck getInstance(Context context) {
        //第二层同步
        synchronized (VersionCheck.class) {
            if(versionCheck == null){
                versionCheck = new VersionCheck(context);
            }
        }
        return versionCheck;
    }
    private VersionCheck (Context context){
        this.context = context;
    }

    /**
     * 去bmob后端云检测版本信息
     */
    public void goCheck(){
        BmobQuery<Version> query = new BmobQuery<Version>();
        eventForBmob = new MesEventForBmob();
        //查询比当前版本号更大的是否存在
        query.addWhereGreaterThan("version_code",getCurrentPhoneMessage());
        query.findObjects(context, new FindListener<Version>() {
            @Override
            public void onSuccess(List<Version> list) {
                if(list.size() != 0){
                    eventForBmob.setStateCode(VERSION_EXIST);
                    eventForBmob.setObject(list.get(list.size()-1));//获取最新版本
                } else
                eventForBmob.setStateCode(VERSION_NO);//已经是最新版本
                EventBus.getDefault().post(eventForBmob);
            }

            @Override
            public void onError(int i, String s) {
                LogUtil.MyLog_e(context,"---"+s);
                eventForBmob.setStateCode(VERSION_ERROE);//查询出错
                eventForBmob.setString(s);
                EventBus.getDefault().post(eventForBmob);
            }
        });
    }

    /**
     * 获取当前app的版本号
     * @return
     */
    private int getCurrentPhoneMessage(){
        PackageManager manager = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(context.getPackageName(),0);
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        return info.versionCode;
        //info.versionNama;
        //info.packageName;
        //info.signatures;
    }
}
