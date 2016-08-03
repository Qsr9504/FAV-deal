package com.example.qsr.fav_deal.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.activities.AddressManageActivity;
import com.example.qsr.fav_deal.activities.FeedbackActivity;
import com.example.qsr.fav_deal.activities.OrderListActivity;
import com.example.qsr.fav_deal.base.BaseFragment;
import com.example.qsr.fav_deal.bean.MessageEvent;
import com.example.qsr.fav_deal.ui.IconFontTextView;
import com.example.qsr.fav_deal.ui.MyAvatarImageView;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.joy.imageselector.ImageSelectorActivity;

/**************************************
 * FileName : com.example.qsr.fav_deal.fragments
 * Author : qsr
 * Time : 2016/7/26 13:28
 * Description : 个人中心界面
 **************************************/
public class CenterFragment extends BaseFragment {
    @Bind(R.id.avatar)
    MyAvatarImageView avatar;
    @Bind(R.id.llcenter_2)
    LinearLayout llcenter2;
    @Bind(R.id.llcenter_1)
    LinearLayout llcenter1;
    @Bind(R.id.llcenter_3)
    LinearLayout llcenter3;
    @Bind(R.id.llcenter_5)
    LinearLayout llcenter5;
    @Bind(R.id.llcenter_4)
    LinearLayout llcenter4;
    @Bind(R.id.llcenter_6)
    LinearLayout llcenter6;
    @Bind(R.id.phone)
    IconFontTextView phone;
    @Bind(R.id.contact)
    TextView contact;
    @Bind(R.id.contactRela)
    RelativeLayout contactRela;
    private ArrayList<String> images;
    private static final int REQUEST_CODE = 10086;
    private Intent intent;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getImageUrl(MessageEvent event) {
        images = (ArrayList<String>) event.getObject();
        Picasso.with(getContext()).load(images.get(0)).into(avatar);
    }

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
        return R.layout.fragment_center;
    }

    @OnClick(R.id.contactRela)
    public void contactRela(View v){
        //用户反馈
        startActivity(new Intent(getContext(), FeedbackActivity.class));
    }
    @OnClick(R.id.avatar)
    public void avatar(View v){
        //点击头像之后的处理事件
    }
    @OnClick(R.id.llcenter_5)
    public void llcenter_5(View v){
        //地址管理
        intent = new Intent(getContext(), AddressManageActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.llcenter_1)
    public void llcenter_1(View v){
        //订单管理
        intent = new Intent(getContext(), OrderListActivity.class);
        startActivity(intent);
    }
    @Override
    protected void initData(String content, View successView) {
        Picasso.with(getContext()).load(R.drawable.demo4).error(R.mipmap.ic_launcher).into(avatar);
    }

    @Override
    protected void initTitle() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
