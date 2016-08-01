package com.example.qsr.fav_deal.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.base.BaseFragment;
import com.example.qsr.fav_deal.bean.MessageEvent;
import com.example.qsr.fav_deal.ui.IconFontTextView;
import com.example.qsr.fav_deal.utils.LogUtil;
import com.example.qsr.fav_deal.utils.UIUtils;
import com.loopj.android.http.RequestParams;
import com.viewpagerindicator.TabPageIndicator;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**************************************
 * FileName : com.example.qsr.fav_deal.fragments
 * Author : qsr
 * Time : 2016/7/26 12:26
 * Description : 主页
 **************************************/
public class HomePageFragment extends BaseFragment {
    @Bind(R.id.t_left)
    IconFontTextView tLeft;
    @Bind(R.id.tab_indicator)
    TabPageIndicator tabIndicator;
    @Bind(R.id.pager)
    ViewPager pager;
    //viewpager中的fragment的集合
    private List<Fragment> fragmentList = new ArrayList<Fragment>();


    @Override
    protected void initEvent() {

    }

    @Override
    protected RequestParams getParams() {
        return new RequestParams();
    }

    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData(String content, View successView) {
        LogUtil.MyLog_e(getContext(), "--来到了initEvent");
        initFragments();//初始化fragment
        tLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageEvent event = new MessageEvent();
                event.setString("1");
                EventBus.getDefault().post(event);
            }
        });
        pager.setAdapter(new MyAdapter(getFragmentManager()));
        tabIndicator.setVisibility(View.VISIBLE);
        tabIndicator.setViewPager(pager);//将viewpager交给pager指示器
    }

    private void initFragments() {
        HomeFruitFragment homeFruitFragment = new HomeFruitFragment();
        HomeCommFragment homeCommFragment = new HomeCommFragment();
        HomeVegFragment homeVegFragment = new HomeVegFragment();
        fragmentList.add(homeFruitFragment);
        fragmentList.add(homeVegFragment);
        fragmentList.add(homeCommFragment);
    }

    @Override
    protected void initTitle() {
//        title_left.setVisibility(View.INVISIBLE);
//        title_right.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return UIUtils.getStringArr(R.array.home_title)[position];
        }
    }
}
