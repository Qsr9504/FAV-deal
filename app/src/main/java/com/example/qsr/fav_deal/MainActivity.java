package com.example.qsr.fav_deal;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.qsr.fav_deal.base.BaseActivity;
import com.example.qsr.fav_deal.fragments.CartFragment;
import com.example.qsr.fav_deal.fragments.CenterFragment;
import com.example.qsr.fav_deal.fragments.DeliverFragment;
import com.example.qsr.fav_deal.fragments.HomePageFragment;
import com.example.qsr.fav_deal.utils.UIUtils;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @Bind(R.id.ll_homePage)
    LinearLayout llHomePage;
    @Bind(R.id.ll_deliver)
    LinearLayout llDeliver;
    @Bind(R.id.ll_cart)
    LinearLayout llCart;
    @Bind(R.id.ll_center)
    LinearLayout llCenter;
    @Bind(R.id.footer_bar)
    LinearLayout footerBar;
    @Bind(R.id.frameLayout)

    FrameLayout frameLayout;

    private CartFragment cartFragment ;
    private CenterFragment centerFragment;
    private DeliverFragment deliverFragment;
    private HomePageFragment homeFragment;
    private FragmentTransaction ft;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        //设置首页
        setSelect(0);
    }

    @Override
    protected void initView() {

    }
    @OnClick({R.id.ll_homePage,R.id.ll_deliver,R.id.ll_cart,R.id.ll_center})
    public void changeTab(View view){
        switch (view.getId()) {
            case R.id.ll_homePage:
                setSelect(0);
                break;
            case R.id.ll_deliver:
                setSelect(1);
                break;
            case R.id.ll_cart:
                setSelect(2);
                break;
            case R.id.ll_center:
                setSelect(3);
                break;
        }
    }
    private void setSelect(int i) {
        FragmentManager fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        reSetTab();
        //隐藏当前的fragment
        hideFragment();
        switch (i) {
            case 0://首页
                if (homeFragment == null) {
                    homeFragment = new HomePageFragment();
                    ft.add(R.id.frameLayout,homeFragment);
                }
                ft.show(homeFragment);
                //更改Tab的点击样式
                llHomePage.setBackgroundColor(UIUtils.getColorId(R.color.footer_press));
                break;
            case 1://物流
                if (deliverFragment == null) {
                    deliverFragment = new DeliverFragment();
                    ft.add(R.id.frameLayout,deliverFragment);
                }
                ft.show(deliverFragment);
                //更改Tab的点击样式
                llDeliver.setBackgroundColor(UIUtils.getColorId(R.color.footer_press));
                break;
            case 2://购物车
                if (cartFragment == null) {
                    cartFragment = new CartFragment();
                    ft.add(R.id.frameLayout,cartFragment);
                }
                ft.show(cartFragment);
                //更改Tab的点击样式
                llCart.setBackgroundColor(UIUtils.getColorId(R.color.footer_press));
                break;
            case 3://个人资料
                if (centerFragment == null) {
                    centerFragment = new CenterFragment();
                    ft.add(R.id.frameLayout,centerFragment);
                }
                ft.show(centerFragment);
                //更改Tab的点击样式
                llCenter.setBackgroundColor(UIUtils.getColorId(R.color.footer_press));
                break;
        }
        ft.commit();
    }
    private void hideFragment() {
        if (homeFragment != null) {
            ft.hide(homeFragment);
        }
        if (cartFragment != null) {
            ft.hide(cartFragment);
        }
        if (deliverFragment != null) {
            ft.hide(deliverFragment);
        }
        if (centerFragment != null) {
            ft.hide(centerFragment);
        }
    }
    private void reSetTab() {
        //将底部导航栏全部重置
        llCart.setBackgroundColor(UIUtils.getColorId(R.color.footer_back));
        llCenter.setBackgroundColor(UIUtils.getColorId(R.color.footer_back));
        llDeliver.setBackgroundColor(UIUtils.getColorId(R.color.footer_back));
        llHomePage.setBackgroundColor(UIUtils.getColorId(R.color.footer_back));
    }
}