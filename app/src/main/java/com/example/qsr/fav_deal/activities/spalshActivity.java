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
import com.example.qsr.fav_deal.globle.AppConstants;
import com.example.qsr.fav_deal.globle.AppNetConfig;
import com.example.qsr.fav_deal.utils.LogUtil;
import com.example.qsr.fav_deal.utils.MySPUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SpalshActivity extends Activity implements View.OnClickListener {
    /**
     * 更新新版本的状态码
     */
    protected static final int UPDATE_VERSION = 100;
    /**
     * 进入应用程序主界面状态码
     */
    protected static final int ENTER_HOME = 101;

    /**
     * url地址出错状态码
     */
    protected static final int URL_ERROR = 102;
    protected static final int IO_ERROR = 103;
    protected static final int JSON_ERROR = 104;
    private boolean flag = true;
    @Bind(R.id.spalsh)
    RelativeLayout spalsh;
    private int currentVersionCode;
    private Message message;
    private String mDownloadUrl;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_VERSION:
                    //弹出对话框,提示用户更新
                    showUpdateDialog();
                    break;
                case ENTER_HOME:
                    //进入应用程序主界面,activity跳转过程
                    if(flag)//判断用户是否已经点击了屏幕
                        enterHome();
                    break;
                case URL_ERROR:
                    Toast.makeText(getApplicationContext(), "url异常", Toast.LENGTH_SHORT).show();
                    enterHome();
                    break;
                case IO_ERROR:
                    Toast.makeText(getApplicationContext(), "读取异常", Toast.LENGTH_SHORT).show();
                    enterHome();
                    break;
                case JSON_ERROR:
                    Toast.makeText(getApplicationContext(), "json解析异常", Toast.LENGTH_SHORT).show();
                    enterHome();
                    break;
            }

        }
    };

    private void enterHome() {

        Intent intent;
        //判断是否第一次进入app
        if (!MySPUtil.getBoolean(AppConstants.CONFIG.IS_GUIDE)) {
            intent = new Intent(this, GuideActivity.class);
        } else {
            intent = new Intent(this, MainActivity.class);
        }
        startActivity(intent);
        flag = false;
        //在开启一个新的界面后,将导航界面关闭(导航界面只可见一次)
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_spalsh);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        //判断用户是否开启版本更新检测
        if (MySPUtil.getBoolean(AppConstants.CONFIG.OPEN_UPDATE)) {//默认不开启
//        if (true){//测试使用
            LogUtil.MyLog_e(SpalshActivity.this, "进入版本检测");
            checkVersion();
        } else {
            LogUtil.MyLog_e(SpalshActivity.this, "准备进入");
            handler.sendEmptyMessageDelayed(ENTER_HOME, 3000);
        }
        currentVersionCode = MySPUtil.getInt(AppConstants.CONFIG.VERSION_CODE);
    }

    /**
     * 版本检查
     */
    private void checkVersion() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(AppNetConfig.BASE_HOST + AppNetConfig.UPDATE, new RequestParams(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                //访问成功  解析json
                JSONObject jsonObject = null;
                message = handler.obtainMessage();
                try {
                    LogUtil.MyLog_e(SpalshActivity.this,"服务器返回：" + content);
//                    jsonObject = new JSONObject(content);
//                    //debug调试,解决问题
//                    String versionName = jsonObject.getString("versionName");//版本名称
//                    int versionCode = jsonObject.getInt("versionCode");//版本号
//                    mDownloadUrl = jsonObject.getString("downloadUrl");//新版本下载地址
//                    if (versionCode > currentVersionCode) {//检测当前版本是否小于网络版本
                        message.what = UPDATE_VERSION;
//                    }
                } catch (Exception e) {
                    message.what = JSON_ERROR;
                }
                handler.sendMessage(message);

            }

            @Override
            public void onFailure(Throwable error, String content) {

            }
        });
    }

    /**
     * 弹出对话框,提示用户更新
     */
    protected void showUpdateDialog() {
        //对话框,是依赖于activity存在的
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置左上角图标
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("版本更新");
        //设置描述内容
        builder.setMessage("新版本更棒啦");
        //积极按钮,立即更新
        builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //下载apk,apk链接地址,downloadUrl
//                downloadApk();
            }
        });

        builder.setNegativeButton("稍后再说", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //取消对话框,进入主界面
                enterHome();
            }
        });

        //点击取消事件监听
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                //即使用户点击取消,也需要让其进入应用程序主界面
                enterHome();
                dialog.dismiss();
            }
        });
        if(flag)
            builder.show();
    }

    @Override
    @OnClick(R.id.spalsh)
    public void onClick(View v) {
        //点击屏幕快速进入
        message = handler.obtainMessage();
        message.what = ENTER_HOME;
        handler.sendMessage(message);
    }
}
