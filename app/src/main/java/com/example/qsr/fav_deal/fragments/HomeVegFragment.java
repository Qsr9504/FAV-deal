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
import com.loopj.android.http.AsyncHttpClient;
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
 * Time : 2016/7/26 16:58
 * Description :
 **************************************/
public class HomeVegFragment extends BaseFragment {
    @Bind(R.id.veg_recyclerView)
    RecyclerView vegRecyclerView;
    @Bind(R.id.refresh)
    SwipeRefreshLayout refresh;
    private List<ShowGoods> goodsList = null;
    private Intent intent;
    private NormalGoodsAdapter adapter;
    private AsyncHttpClient client;
    private Bundle bundle = new Bundle();
    private GoodsTools goodsTools;

    @Override
    protected void initEvent() {

    }

    @Override
    protected RequestParams getParams() {
//        RequestParams params = new RequestParams();
//        params.put("all","allVeg");//标记
        return new RequestParams();
    }

    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_veg;
    }

    @Override
    protected void initData(String content, View successView) {
        ButterKnife.bind(this, successView);
        EventBus.getDefault().register(this);
        goodsTools = GoodsTools.getInstance(getContext());
        if (goodsList == null)
            getData();
        adapter = new NormalGoodsAdapter(getContext(), goodsList);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                goodsList.clear();
                goodsTools.getAllVeg();
                refresh.setRefreshing(false);
            }
        });
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
                adapter.addToCart(goodsList.get(position));
                Toast.makeText(getContext(), "点击了添加按钮" + position, Toast.LENGTH_SHORT).show();
                //添加 按钮的飞入购物车动画效果
            }

            @Override
            public void onCutBtn(int position) {
                //此处不需要处理
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        vegRecyclerView.setLayoutManager(linearLayoutManager);
        vegRecyclerView.setAdapter(adapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void vegList(MesEventForBmob eventForBmob) {
        int code = eventForBmob.getStateCode();
        if (code == GoodsTools.ALL_VEG_SUCC) {
            LogUtil.MyLog_e(getContext(), "已经准备转换");
            List<Goods> list = (List<Goods>) eventForBmob.getObject();
            LogUtil.MyLog_e(getContext(), list.toString());
            goodsList.addAll(Goods.GoodsToShowGoods(list));
            LogUtil.MyLog_e(getContext(), goodsList.toString());
            vegRecyclerView.setAdapter(adapter);
        } else if (code == GoodsTools.ALL_VEG_ERROR){
            Toast.makeText(getContext(), "获取蔬菜列表失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void initTitle() {

    }

    private void getData() {
        goodsList = new ArrayList<ShowGoods>();
        goodsTools.getAllVeg();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
