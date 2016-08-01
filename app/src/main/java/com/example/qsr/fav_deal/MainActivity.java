package com.example.qsr.fav_deal;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.qsr.fav_deal.base.BaseActivity;
import com.example.qsr.fav_deal.bean.MessageEvent;
import com.example.qsr.fav_deal.fragments.CartFragment;
import com.example.qsr.fav_deal.fragments.CenterFragment;
import com.example.qsr.fav_deal.fragments.DeliverFragment;
import com.example.qsr.fav_deal.fragments.HomePageFragment;
import com.example.qsr.fav_deal.utils.UIUtils;
import org.greenrobot.eventbus.EventBus;
import java.util.ArrayList;
import butterknife.Bind;
import butterknife.OnClick;
import cn.joy.imageselector.ImageSelectorActivity;
import cn.joy.imageselector.Logs;

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
    @OnClick({R.id.ll_homePage,R.id.ll_deliver,R.id.ll_cart,R.id.ll_center
    ,R.id.home_icon,R.id.home_text,R.id.deliver_icon,R.id.deliver_text,
    R.id.cart_icon,R.id.cart_text,R.id.center_icon,R.id.center_text})
    public void changeTab(View view){
        switch (view.getId()) {
            case R.id.ll_homePage:
            case R.id.home_icon:
            case R.id.home_text:
                setSelect(0);
                break;
            case R.id.ll_deliver:
            case R.id.deliver_icon:
            case R.id.deliver_text:
                setSelect(1);
                break;
            case R.id.ll_cart:
            case R.id.cart_icon:
            case R.id.cart_text:
                setSelect(2);
                break;
            case R.id.ll_center:
            case R.id.center_icon:
            case R.id.center_text:
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10086 && resultCode == RESULT_OK && data.hasExtra(ImageSelectorActivity.RESULT_IMAGE_SELECTED_PATH)) {
            ArrayList<String> images = new ArrayList<>();
            if (3 != ImageSelectorActivity.IMAGE_SELECTOR_MODE_MULTI) {
                images.add(data.getStringExtra(ImageSelectorActivity.RESULT_IMAGE_SELECTED_PATH));
            } else {
                images.addAll(data.getStringArrayListExtra(ImageSelectorActivity.RESULT_IMAGE_SELECTED_PATH));
            }
            Logs.e("MainActivity", "onActivityResult " + data.getExtras().get(ImageSelectorActivity.RESULT_IMAGE_SELECTED_PATH).toString());
            Toast.makeText(this,images.toString(),Toast.LENGTH_SHORT).show();
            MessageEvent event = new MessageEvent();
            event.setObject(images);
            EventBus.getDefault().post(event);
        }
    }
}