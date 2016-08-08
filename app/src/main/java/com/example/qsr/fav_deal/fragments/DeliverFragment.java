package com.example.qsr.fav_deal.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.activities.DeliverActivity;
import com.example.qsr.fav_deal.base.BaseFragment;
import com.example.qsr.fav_deal.bean.Order;
import com.example.qsr.fav_deal.bmobUtil.MesEventForBmob;
import com.example.qsr.fav_deal.bmobUtil.OrderTools;
import com.loopj.android.http.RequestParams;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**************************************
 * FileName : com.example.qsr.fav_deal.fragments
 * Author : qsr
 * Time : 2016/7/26 13:26
 * Description : 当前已经购买的物流详情的界面
 **************************************/
public class DeliverFragment extends BaseFragment {

    @Bind(R.id.deliver_1)
    TextView deliver1;
    @Bind(R.id.deliver_2)
    TextView deliver2;
    @Bind(R.id.deliver_3)
    TextView deliver3;
    @Bind(R.id.deliver_4)
    TextView deliver4;
    private OrderTools orderTools;
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
        return R.layout.fragment_deliver;
    }

    @Override
    protected void initData(String content, View successView) {
        ButterKnife.bind(this, successView);
        orderTools = OrderTools.getInstance(getContext());
    }

    @Override
    protected void initTitle() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    @OnClick(R.id.deliver_1)
    public void deliver_1(View view){
        orderTools.getTypeOrder(0);
        startActivity(new Intent(getActivity(), DeliverActivity.class));
    }
    @OnClick(R.id.deliver_2)
    public void deliver_2(View view){
        orderTools.getTypeOrder(1);
        startActivity(new Intent(getActivity(), DeliverActivity.class));
    }
    @OnClick(R.id.deliver_3)
    public void deliver_3(View view){
        orderTools.getTypeOrder(2);
        startActivity(new Intent(getActivity(), DeliverActivity.class));
    }
    @OnClick(R.id.deliver_4)
    public void deliver_4(View view){
        orderTools.getTypeOrder(3);
        startActivity(new Intent(getActivity(), DeliverActivity.class));
    }

}
