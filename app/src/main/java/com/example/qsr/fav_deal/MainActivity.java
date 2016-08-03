package com.example.qsr.fav_deal;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qsr.fav_deal.activities.TypeActivity;
import com.example.qsr.fav_deal.base.BaseActivity;
import com.example.qsr.fav_deal.bean.GridViewItem;
import com.example.qsr.fav_deal.bean.MessageEvent;
import com.example.qsr.fav_deal.fragments.CartFragment;
import com.example.qsr.fav_deal.fragments.CenterFragment;
import com.example.qsr.fav_deal.fragments.DeliverFragment;
import com.example.qsr.fav_deal.fragments.HomePageFragment;
import com.example.qsr.fav_deal.recycler.adapter.SlidingGridViewAdapter;
import com.example.qsr.fav_deal.ui.IconFontTextView;
import com.example.qsr.fav_deal.utils.UIUtils;
import com.yancy.imageselector.ImageSelector;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.joy.imageselector.ImageSelectorActivity;
import cn.joy.imageselector.Logs;

public class MainActivity extends BaseActivity {
    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;
    @Bind(R.id.home_icon)
    IconFontTextView homeIcon;
    @Bind(R.id.home_text)
    TextView homeText;
    @Bind(R.id.ll_homePage)
    LinearLayout llHomePage;
    @Bind(R.id.deliver_icon)
    IconFontTextView deliverIcon;
    @Bind(R.id.deliver_text)
    TextView deliverText;
    @Bind(R.id.ll_deliver)
    LinearLayout llDeliver;
    @Bind(R.id.cart_icon)
    IconFontTextView cartIcon;
    @Bind(R.id.cart_text)
    TextView cartText;
    @Bind(R.id.ll_cart)
    LinearLayout llCart;
    @Bind(R.id.center_icon)
    IconFontTextView centerIcon;
    @Bind(R.id.center_text)
    TextView centerText;
    @Bind(R.id.ll_center)
    LinearLayout llCenter;
    @Bind(R.id.footer_bar)
    LinearLayout footerBar;
    @Bind(R.id.fruit_text)
    TextView fruitText;
    @Bind(R.id.fruit_type)
    LinearLayout fruitType;
    @Bind(R.id.veg_text)
    TextView vegText;
    @Bind(R.id.veg_type)
    LinearLayout vegType;
    @Bind(R.id.type_gridV)
    GridView typeGridV;
    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    private CartFragment cartFragment;
    private CenterFragment centerFragment;
    private DeliverFragment deliverFragment;
    private HomePageFragment homeFragment;
    private FragmentTransaction ft;
    private List<GridViewItem> itemList = new ArrayList<GridViewItem>();;
    private SlidingGridViewAdapter adapter = null;
    private Intent intent;
    private Bundle bundle = new Bundle();
    private String avatarUrl;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        //设置首页
        setSelect(0);
        //设置抽屉中的条目
        adapter = new SlidingGridViewAdapter(getData(), MainActivity.this);
        typeGridV.setAdapter(adapter);
        typeGridV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(MainActivity.this, TypeActivity.class);
                bundle.putString("type",itemList.get(position).getName());
                intent.putExtra("type_bundle",bundle);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "点击了" + itemList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        //左侧抽屉两个分类的切换
        fruitType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fruitType.setBackgroundColor(getResources().getColor(R.color.white));//设置背景为白色
                vegType.setBackgroundColor(getResources().getColor(R.color.footer_press));//设置背景为黄色
                vegText.setTextColor(getResources().getColor(R.color.white));
                fruitText.setTextColor(getResources().getColor(R.color.footer_press));
                itemList = getData();
                typeGridV.setAdapter(adapter);
            }
        });
        //左侧抽屉中蔬菜分类的点击按钮
        vegType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fruitText.setTextColor(getResources().getColor(R.color.white));
                vegText.setTextColor(getResources().getColor(R.color.footer_press));
                vegType.setBackgroundColor(getResources().getColor(R.color.white));//设置背景为白色
                fruitType.setBackgroundColor(getResources().getColor(R.color.footer_press));//设置背景为黄色
                itemList = getData2();
                typeGridV.setAdapter(adapter);
            }
        });
    }

    private List<GridViewItem> getData() {
        itemList.clear();
        String[] name = UIUtils.getStringArr(R.array.slide_fruit);
        int[] ids = {R.drawable.f1,R.drawable.f2,R.drawable.f3,R.drawable.f4,R.drawable.f5,
                R.drawable.f6,R.drawable.f7,R.drawable.f8,R.drawable.f9,R.drawable.f10,R.drawable.f11,
                R.drawable.f12,R.drawable.f13,R.drawable.f14,R.drawable.f15};
        for(int i = 0;i<name.length;i++){
            itemList.add(new GridViewItem(ids[i],name[i]));
        }
        return itemList;
    }
    private List<GridViewItem> getData2() {
        itemList.clear();
        String[] name2 = UIUtils.getStringArr(R.array.slide_veg);
        int[] ids2 = {R.drawable.v1,R.drawable.v2,R.drawable.v3,R.drawable.v4,R.drawable.v5,
                R.drawable.v6,R.drawable.v7,R.drawable.v8,R.drawable.v9};
        for(int i = 0;i<name2.length;i++){
            itemList.add(new GridViewItem(ids2[i],name2[i]));
        }
        return itemList;
    }

    //打开侧面抽屉
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void open(MessageEvent event) {
        if (event.getString().equals("1")) {
            drawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.ll_homePage, R.id.ll_deliver, R.id.ll_cart, R.id.ll_center
            , R.id.home_icon, R.id.home_text, R.id.deliver_icon, R.id.deliver_text,
            R.id.cart_icon, R.id.cart_text, R.id.center_icon, R.id.center_text})
    public void changeTab(View view) {
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
                    ft.add(R.id.frameLayout, homeFragment);
                }
                ft.show(homeFragment);
                //更改Tab的点击样式
                llHomePage.setBackgroundColor(UIUtils.getColorId(R.color.footer_press));
                homeIcon.setTextColor(getResources().getColor(R.color.footer_pressBlack));
                homeText.setTextColor(getResources().getColor(R.color.footer_pressBlack));
                break;
            case 1://物流
                if (deliverFragment == null) {
                    deliverFragment = new DeliverFragment();
                    ft.add(R.id.frameLayout, deliverFragment);
                }
                ft.show(deliverFragment);
                //更改Tab的点击样式
                llDeliver.setBackgroundColor(UIUtils.getColorId(R.color.footer_press));
                deliverIcon.setTextColor(getResources().getColor(R.color.footer_pressBlack));
                deliverText.setTextColor(getResources().getColor(R.color.footer_pressBlack));
                break;
            case 2://购物车
                if (cartFragment == null) {
                    cartFragment = new CartFragment();
                    ft.add(R.id.frameLayout, cartFragment);
                }
                ft.show(cartFragment);
                //更改Tab的点击样式
                llCart.setBackgroundColor(UIUtils.getColorId(R.color.footer_press));
                cartIcon.setTextColor(getResources().getColor(R.color.footer_pressBlack));
                cartText.setTextColor(getResources().getColor(R.color.footer_pressBlack));
                break;
            case 3://个人资料
                if (centerFragment == null) {
                    centerFragment = new CenterFragment();
                    ft.add(R.id.frameLayout, centerFragment);
                }
                ft.show(centerFragment);
                //更改Tab的点击样式
                llCenter.setBackgroundColor(UIUtils.getColorId(R.color.footer_press));
                centerIcon.setTextColor(getResources().getColor(R.color.footer_pressBlack));
                centerText.setTextColor(getResources().getColor(R.color.footer_pressBlack));
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
        centerIcon.setTextColor(getResources().getColor(R.color.footer_normal));
        centerText.setTextColor(getResources().getColor(R.color.footer_normal));
        cartIcon.setTextColor(getResources().getColor(R.color.footer_normal));
        cartText.setTextColor(getResources().getColor(R.color.footer_normal));
        deliverIcon.setTextColor(getResources().getColor(R.color.footer_normal));
        deliverText.setTextColor(getResources().getColor(R.color.footer_normal));
        homeIcon.setTextColor(getResources().getColor(R.color.footer_normal));
        homeText.setTextColor(getResources().getColor(R.color.footer_normal));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //判断头像返回的值
        if (requestCode == ImageSelector.IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            // Get Image Path List
            List<String> pathList = data.getStringArrayListExtra(com.yancy.imageselector.ImageSelectorActivity.EXTRA_RESULT);
            for (String path : pathList) {
                avatarUrl = path;
                Toast.makeText(MainActivity.this, "ImagePathList" + path, Toast.LENGTH_SHORT).show();
            }
            //设置头像预览
            if (avatarUrl != null) {
                //发送数据到fragment
                MessageEvent event = new MessageEvent();
                event.setString(avatarUrl);
                EventBus.getDefault().post(event);
            }
        }
    }

}