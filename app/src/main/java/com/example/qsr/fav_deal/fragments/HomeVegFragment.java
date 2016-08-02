package com.example.qsr.fav_deal.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.activities.GoodsDetailActivity;
import com.example.qsr.fav_deal.base.BaseFragment;
import com.example.qsr.fav_deal.bean.ShowGoods;
import com.example.qsr.fav_deal.recycler.OnRecyclerViewListener;
import com.example.qsr.fav_deal.recycler.adapter.NormalGoodsAdapter;
import com.example.qsr.fav_deal.utils.UIUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

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
    private List<ShowGoods> goodsList = null;
    private Intent intent;
    private NormalGoodsAdapter adapter;
    private AsyncHttpClient client;
    private Bundle bundle = new Bundle();

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
        if (goodsList == null)//测试数据使用
            getData();
        adapter = new NormalGoodsAdapter(getContext(),goodsList);
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

    @Override
    protected void initTitle() {

    }

    private void getData() {
        goodsList = new ArrayList<ShowGoods>();
        //用作测试数据
        String picUrl = "http://file.bmob.cn/M01/E1/9C/oYYBAFePPU6ADBA2AAAdNdM4_BM149.jpg";
        ShowGoods goods1 = new ShowGoods(101, "蔬菜"+1, picUrl, picUrl, "肉质鲜嫩，清脆多汁", "9.99", "20.1", 0, 0, 0, "", "");
        ShowGoods goods2 = new ShowGoods(102, "蔬菜"+2, picUrl, picUrl, "肉质鲜嫩，清脆多汁", "9.99", "25.3", 0, 0, 0, "", "");
        ShowGoods goods3 = new ShowGoods(103, "蔬菜"+3, picUrl, picUrl, "肉质鲜嫩，清脆多汁", "3.99", "18.31", 0, 0, 0, "", "");
        goodsList.add(goods1);
        goodsList.add(goods2);
        goodsList.add(goods3);
    }
}
