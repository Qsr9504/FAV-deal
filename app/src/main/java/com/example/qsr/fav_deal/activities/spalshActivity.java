package com.example.qsr.fav_deal.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.qsr.fav_deal.MainActivity;
import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.bmobUtil.MesEventForBmob;
import com.example.qsr.fav_deal.bmobUtil.VersionCheck;
import com.example.qsr.fav_deal.bmobUtil.bean.Version;
import com.example.qsr.fav_deal.globle.App;
import com.example.qsr.fav_deal.globle.AppConstants;
import com.example.qsr.fav_deal.globle.AppNetConfig;
import com.example.qsr.fav_deal.utils.LogUtil;
import com.example.qsr.fav_deal.utils.MySPUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;

public class SpalshActivity extends Activity implements View.OnClickListener {
    /**
     * 进入应用程序主界面状态码
     */
    protected static final int ENTER_HOME = 101;

    private boolean flag = true;
    @Bind(R.id.spalsh)
    RelativeLayout spalsh;
    private int currentVersionCode;
    private VersionCheck versionCheck;
    private Message message;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ENTER_HOME:
                    //进入应用程序主界面,activity跳转过程
                    enterHome();
                    break;
            }

        }
    };

    private void enterHome() {
        if(flag) {
            Intent intent;
            //判断是否第一次进入app
            if (!MySPUtil.getBoolean(AppConstants.CONFIG.IS_GUIDE)) {
                intent = new Intent(this, GuideActivity.class);
            }else if("".equals(MySPUtil.getString(AppConstants.CONFIG.USER_ID,""))){
                //如果用户登陆过，用户信息在本地找得到
                intent = new Intent(this,MainActivity.class);
            }
            else {
                intent = new Intent(this, LoginActivity.class);
            }
            startActivity(intent);
            flag = false;
            //在开启一个新的界面后,将导航界面关闭(导航界面只可见一次)
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_spalsh);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        versionCheck = VersionCheck.getInstance(this);
        setFinishOnTouchOutside(false);
        initData();
    }

    private void initData() {
        //判断用户是否开启版本更新检测
        if (MySPUtil.getBoolean(AppConstants.CONFIG.OPEN_UPDATE,true)) {//默认开启
            //----------------------------------
            //-----我太聪明了---解决bug，（屏幕点击快速进入将会不能检测新版本）
            //-----将它设置为不可点击就可以了
            //----------------------------------
            //如果进来了判断检测，就先将 快速点击屏幕进入  这个东西关掉，
            spalsh.setClickable(false);
            LogUtil.MyLog_e(SpalshActivity.this, "进入版本检测");
            versionCheck.goCheck();
        } else {
            LogUtil.MyLog_e(SpalshActivity.this, "准备进入");
            handler.sendEmptyMessageDelayed(ENTER_HOME, 3000);
        }
    }

    //检测新版本的返回消息处理
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void hasNew(MesEventForBmob eventForBmob){
        if(eventForBmob.getStateCode() == VersionCheck.VERSION_EXIST)//有新版本
        {
            spalsh.setClickable(false);
            showUpdateDialog((Version) eventForBmob.getObject());
        }
        else if(eventForBmob.getStateCode() == VersionCheck.VERSION_NO)//已经是最新
            enterHome();
        else if(eventForBmob.getStateCode() == VersionCheck.VERSION_ERROE)
            Toast.makeText(this,"版本检测失败"+eventForBmob.getString(),Toast.LENGTH_SHORT).show();
    }

    /**
     * 弹出对话框,提示用户更新
     */
    protected void showUpdateDialog(final Version version) {
        //对话框,是依赖于activity存在的
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        setFinishOnTouchOutside(false);
        //设置左上角图标
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("检测到新版本: " + version.getVersion_name());
        //设置描述内容
        builder.setMessage(version.getVersion_desc());
        //确定按钮,立即更新
        builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //下载apk,apk链接地址,downloadUrl
                downloadApk(version.getVersion_url());
            }
        });

        builder.setNegativeButton("以后再说", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LogUtil.MyLog_e(SpalshActivity.this,"以后再说进入");
                //取消对话框,进入主界面
                enterHome();
            }
        });
        //点击取消事件监听
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                LogUtil.MyLog_e(SpalshActivity.this,"取消按钮进入");
                //即使用户点击取消,跳转到注册界面
                startActivity(new Intent(SpalshActivity.this,LoginActivity.class));
                dialog.dismiss();
                finish();
            }
        });
        if(flag)
            builder.show();
    }
    /**
     * apk下载
     * -----------------------------
     * -----------------------------
     * -----------------------------
     * */
    //后台下载apk
    private void downloadApk(String url) {

    }

    @Override
    @OnClick(R.id.spalsh)
    public void onClick(View v) {
        LogUtil.MyLog_e(this,"点击屏幕快速进入");
        //点击屏幕快速进入
        message = handler.obtainMessage();
        message.what = ENTER_HOME;
        handler.sendMessage(message);
    }
}
