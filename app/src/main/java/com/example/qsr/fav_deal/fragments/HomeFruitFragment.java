package com.example.qsr.fav_deal.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.activities.GoodsDetailActivity;
import com.example.qsr.fav_deal.base.BaseFragment;
import com.example.qsr.fav_deal.bean.Goods;
import com.example.qsr.fav_deal.bean.ShowGoods;
import com.example.qsr.fav_deal.bmobUtil.GoodsTools;
import com.example.qsr.fav_deal.bmobUtil.MesEventForBmob;
import com.example.qsr.fav_deal.recycler.OnRecyclerViewListener;
import com.example.qsr.fav_deal.recycler.adapter.NormalGoodsAdapter;
import com.example.qsr.fav_deal.utils.LogUtil;
import com.loopj.android.http.RequestParams;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**************************************
 * FileName : com.example.qsr.fav_deal.fragments
 * Author : qsr
 * Time : 2016/7/26 16:57
 * Description : 水果列表
 **************************************/
public class HomeFruitFragment extends BaseFragment {
    @Bind(R.id.fruit_recyclerView)
    RecyclerView fruitRecyclerView;
    @Bind(R.id.refresh)
    SwipeRefreshLayout refresh;
    private List<ShowGoods> goodsList = null;
    private Intent intent;
    private NormalGoodsAdapter adapter;
    private GoodsTools goodsTools;

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
        return R.layout.fragment_home_fruit;
    }

    @Override
    protected void initData(String content, View successView) {
        LogUtil.MyLog_e(getContext(), "---***" + "进来了oncreat");
        ButterKnife.bind(this, successView);
        goodsTools = GoodsTools.getInstance(getContext());
        LogUtil.MyLog_e(getContext(), content);
        EventBus.getDefault().register(this);
        if (goodsList == null) {
            initData();//用于没有网络的时候死数据
        }
        refresh.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        //下拉刷新监听
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                goodsList.clear();
                goodsTools.getAllFruit();
                refresh.setRefreshing(false);
            }
        });
        adapter = new NormalGoodsAdapter(getContext(), goodsList);
        //设置监听事件
        adapter.setOnRecyclerViewListener(new OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getContext(), goodsList.get(position).toString(), Toast.LENGTH_SHORT).show();
                bundle.putSerializable("showGoods", goodsList.get(position));
                Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
                intent.putExtra("showBundle", bundle);
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View v, int position) {
                Toast.makeText(getContext(), "点击了长按按钮" + position, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onAddBtn(int position) {
                Toast.makeText(getContext(), "点击了添加按钮" + position, Toast.LENGTH_SHORT).show();
                adapter.addToCart(goodsList.get(position));
                //添加 按钮的飞入购物车动画效果
            }

            @Override
            public void onCutBtn(int position) {
                //此处不需要处理
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        fruitRecyclerView.setLayoutManager(linearLayoutManager);
        fruitRecyclerView.setAdapter(adapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void fruitList(MesEventForBmob eventForBmob) {
        int code = eventForBmob.getStateCode();
        if (code == GoodsTools.ALL_FRUIT_SUCC) {
            LogUtil.MyLog_e(getContext(), "已经准备转换");
            List<Goods> list = (List<Goods>) eventForBmob.getObject();
            LogUtil.MyLog_e(getContext(), list.toString());
            goodsList.addAll(Goods.GoodsToShowGoods(list));
            LogUtil.MyLog_e(getContext(), goodsList.toString());
            fruitRecyclerView.setAdapter(adapter);
        } else if (code == GoodsTools.ALL_FRUIT_ERROR) {
            Toast.makeText(getContext(), "获取水果列表失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void initTitle() {

    }

    private void initData() {
        goodsList = new ArrayList<ShowGoods>();
        goodsTools.getAllFruit();
        //可添加sqlite数据库缓存
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }
}
