package com.example.qsr.fav_deal.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qsr.fav_deal.bean.ResultState;
import com.example.qsr.fav_deal.ui.LoadingPage;
import com.example.qsr.fav_deal.utils.UIUtils;
import com.loopj.android.http.RequestParams;

import butterknife.ButterKnife;

/**************************************
 * FileName : com.example.qsr.fav_deal.base
 * Author : qsr
 * Time : 2016/7/26 11:32
 * Description : fragment的基类
 **************************************/
public abstract class BaseFragment extends Fragment {
    private LoadingPage loadingPage;
    protected Bundle bundle = new Bundle();
    //声明加载界面
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loadingPage = new LoadingPage(container.getContext()) {
            @Override
            public int LayoutId() {
                //交给继承的fragment实现
                return getLayoutId();
            }

            @Override
            protected void OnSuccess(ResultState resultState, View successView) {
                ButterKnife.bind(BaseFragment.this,successView);
                initTitle();
                initData(resultState.getContent(),successView);
            }

            @Override
            protected RequestParams params() {
                return getParams();
            }

            @Override
            protected String url() {
                return getUrl();
            }
        };
        initEvent();
        return loadingPage;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        UIUtils.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                show();
            }
        }, 1000);
    }

    protected abstract void initEvent();

    protected abstract RequestParams getParams();

    protected abstract String getUrl();

    protected abstract int getLayoutId();

    protected abstract void initData(String content, View successView);

    protected abstract void initTitle();

    public void show() {
        loadingPage.show();
    }
}
