package com.example.qsr.fav_deal.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;

import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.bean.ResultState;
import com.example.qsr.fav_deal.ui.LoadingPage;
import com.example.qsr.fav_deal.utils.MySPUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**************************************
 * FileName : com.example.qsr.fav_deal.base
 * Author : qsr
 * Time : 2016/7/26 11:31
 * Description : activity的基类
 **************************************/
public abstract class BaseActivity extends FragmentActivity{
    protected Message message;//用于消息发送
    protected Bundle activity_bundle;//用于传递数据使用
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //透到状态栏
        getWindow().addFlags(67108864);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        message = Message.obtain();
        activity_bundle = new Bundle();
        initView(savedInstanceState);
        initData();
    }

    protected abstract int getLayoutId();

    protected abstract void initData();

    protected abstract void initView(@Nullable Bundle savedInstanceState);
}
