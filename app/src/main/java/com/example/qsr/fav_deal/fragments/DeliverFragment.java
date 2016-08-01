package com.example.qsr.fav_deal.fragments;

import android.view.View;

import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.base.BaseFragment;
import com.loopj.android.http.RequestParams;

/**************************************
 * FileName : com.example.qsr.fav_deal.fragments
 * Author : qsr
 * Time : 2016/7/26 13:26
 * Description : 当前已经购买的物流详情的界面
 **************************************/
public class DeliverFragment extends BaseFragment {

    @Override
    protected RequestParams getParams() {
        return new RequestParams();
    }

    @Override
    protected String getUrl() {
        return "";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_deliver;
    }

    @Override
    protected void initData(String content, View successView) {

    }

    @Override
    protected void initTitle() {

    }
}
