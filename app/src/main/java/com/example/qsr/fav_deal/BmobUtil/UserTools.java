package com.example.qsr.fav_deal.bmobUtil;

import android.content.Context;
import android.widget.Toast;

import com.example.qsr.fav_deal.bean.User;
import com.example.qsr.fav_deal.bmobUtil.bean.BmobOrder;
import com.example.qsr.fav_deal.utils.LogUtil;
import com.example.qsr.fav_deal.utils.Md5;
import com.example.qsr.fav_deal.utils.TextUtil;

import org.greenrobot.eventbus.EventBus;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**************************************
 * FileName : com.example.qsr.fav_deal.BmobUtil
 * Author : qsr
 * Time : 2016/8/4 9:28
 * Description : bomb sdk3.4.7  版本下的用户登录注册,单例模式
 *  双层同步影响性能，可以考虑用静态内部类，既保证线程安全避免性能影响
 *  但是此处为了耦合度降低，我依旧采用双层同步
 **************************************/
public class UserTools {

    public static final int REGISTER_SUCCESS = 0;//注册用户成功
    public static final int REGISTER_FAIL = 1;//注册用户失败

    public static final int LOGIN_SUCCESS = 2;//登录用户成功
    public static final int LOGIN_FAIL = 3;//登录用户失败

    private Context context;
    private BmobUser bmobUser;
    private MesEventForBmob eventForBmob;
    private static UserTools userTools = null;
    //第一层同步
    public static synchronized UserTools getInstance(Context context) {
        //第二层同步
        synchronized (UserTools.class) {
            if(userTools == null){
                userTools = new UserTools(context);
            }
        }
        return userTools;
    }
    private UserTools (Context context){
        this.context = context;
    }

    /**
     * 注册操作
     * 必须要有设置好账户和密码
     * @param user
     * @return
     */
    public void registerUser(User user){
        if(!TextUtil.isEmpty(user.getU_account(),user.getU_pwd())){
            try {
                bmobUser = new BmobUser();
                eventForBmob = new MesEventForBmob();
                bmobUser.setUsername(user.getU_account());
                //密码进行MD5加密
                bmobUser.setPassword(Md5.encoder(user.getU_pwd()));
                bmobUser.setEmail(null);
                bmobUser.signUp(context, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        eventForBmob.setStateCode(REGISTER_SUCCESS);
                        //可以通过BmobUser.getCurrentUser(context)获取当前用户信息
                        EventBus.getDefault().post(eventForBmob);
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        eventForBmob.setStateCode(REGISTER_FAIL);
                        eventForBmob.setString(s);
                        EventBus.getDefault().post(eventForBmob);
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            Toast.makeText(context,"用户名或密码为空",Toast.LENGTH_SHORT).show();
        }
    }

    public void loginUser(User user){
        if(!TextUtil.isEmpty(user.getU_account(),user.getU_pwd())){
            try {
                bmobUser = new BmobUser();
                eventForBmob = new MesEventForBmob();
                bmobUser.setUsername(user.getU_account());
                bmobUser.setPassword(user.getU_pwd());
                bmobUser.setEmail(null);
                bmobUser.login(context, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        eventForBmob.setStateCode(LOGIN_SUCCESS);
                        EventBus.getDefault().post(eventForBmob);
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        eventForBmob.setStateCode(LOGIN_FAIL);
                        eventForBmob.setString(s);
                        EventBus.getDefault().post(eventForBmob);
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            Toast.makeText(context,"用户名或密码为空",Toast.LENGTH_SHORT).show();
        }
    }
    public void updateUserMoney(int money){
        User currentUser = BmobUser.getCurrentUser(context,User.class);
        User user = new User();
        user.setU_money(currentUser.getU_money() - money);//减少钱
        LogUtil.MyLog_e(context,user.getU_money()+"");
        LogUtil.MyLog_e(context,money + "");
        user.setU_integra(currentUser.getU_integra() + (money/10));//增加积分
        user.update(context);
        user.update(context,bmobUser.getObjectId(),new UpdateListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(context,"扣钱成功",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(int code, String msg) {

            }
        });
    }

}
