package com.example.qsr.fav_deal.fragments;

import android.view.View;

import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.base.BaseFragment;
import com.loopj.android.http.RequestParams;

/**************************************
 * FileName : com.example.qsr.fav_deal.fragments
 * Author : qsr
 * Time : 2016/7/26 13:27
 * Description : 购物车的界面
 **************************************/
public class CartFragment extends BaseFragment {
    @Override
    protected void initEvent() {

    }

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
        return R.layout.fragment_cart;
    }

    @Override
    protected void initData(String content, View successView) {

    }

    @Override
    protected void initTitle() {

    }
}
