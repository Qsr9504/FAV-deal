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
import com.example.qsr.fav_deal.bean.CartGoods;
import com.example.qsr.fav_deal.bean.MessageEvent;
import com.example.qsr.fav_deal.bean.ShowGoods;
import com.example.qsr.fav_deal.recycler.OnRecyclerViewListener;
import com.example.qsr.fav_deal.recycler.adapter.AllFruitAdapter;
import com.example.qsr.fav_deal.utils.LogUtil;
import com.loopj.android.http.RequestParams;

import org.greenrobot.eventbus.EventBus;

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
    private List<ShowGoods> goodsList = new ArrayList<ShowGoods>();
    private Intent intent;
    private AllFruitAdapter adapter;

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
        LogUtil.MyLog_e(getContext(), content);
//        Gson gson = new Gson();
//        goodsList = gson.fromJson(content,new TypeToken<List<Goods>>(){}.getType());
        initData();//用于没有网络的时候死数据

//        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(getContext(),goodsList) {
//            @Override
//            public void addBtnClick(View v, Goods goods) {
//                Toast.makeText(getContext(),goods.toString(),Toast.LENGTH_SHORT).show();
//                intent = new Intent(getContext(),GoodsDetailActivity.class);
//                startActivity(intent);
//            }
//
//            @Override
//            public void item_LLClick(View v, Goods goods) {
//            }
//        };
//        fruitRecyclerView.setAdapter(adapter);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
//        fruitRecyclerView.setLayoutManager(linearLayoutManager);


//        HomeListAdapter adapter = new HomeListAdapter(getContext(),goodsList) {
//            @Override
//            public void addBtnClick(View v, Goods goods) {
//                //添加至购物车操作
//                Toast.makeText(getContext(),goods.toString(),Toast.LENGTH_SHORT).show();
//            }
//        };
//        fruit_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(view.getContext(),goodsList.get(position).toString(),Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(view.getContext(),GoodsDetailActivity.class);
//                bundle.putSerializable("good",goodsList.get(position));
//                intent.putExtra("fruit_detail",bundle);
//                startActivity(intent);
//            }
//        });
        refresh.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                goodsList.add(new ShowGoods(100, "下拉刷新来的", ""+R.mipmap.ic_launcher, ""+R.mipmap.ic_launcher, "肉质鲜嫩，清脆多汁", "13.29", "13.99", 0, 0, 0, "", ""));
                fruitRecyclerView.setAdapter(adapter);
                refresh.setRefreshing(false);
            }
        });
        adapter = new AllFruitAdapter(getContext(), goodsList);
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
                addToCart(goodsList.get(position));
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

    @Override
    protected void initTitle() {

    }

    private void initData() {
        String picUrl = "http://file.bmob.cn/M01/E1/9C/oYYBAFePPU6ADBA2AAAdNdM4_BM149.jpg";
        ShowGoods goods1 = new ShowGoods(1, "小樱桃", picUrl, picUrl, "肉质鲜嫩，清脆多汁", "13.29", "13.99", 0, 0, 0, "", "");
        ShowGoods goods2 = new ShowGoods(2, "火龙果2", picUrl, picUrl, "肉质鲜嫩，清脆多汁", "13.29", "13.99", 0, 0, 0, "", "");
        ShowGoods goods3 = new ShowGoods(3, "火龙果3", picUrl, picUrl, "肉质鲜嫩，清脆多汁", "13.29", "13.99", 0, 0, 0, "", "");
        ShowGoods goods4 = new ShowGoods(4, "火龙果4", picUrl, picUrl, "肉质鲜嫩，清脆多汁", "13.29", "13.99", 0, 0, 0, "", "");
        ShowGoods goods5 = new ShowGoods(5, "火龙果5", picUrl, picUrl, "肉质鲜嫩，清脆多汁", "13.29", "13.99", 0, 0, 0, "", "");
        ShowGoods goods6 = new ShowGoods(6, "火龙果6", picUrl, picUrl, "肉质鲜嫩，清脆多汁", "13.29", "13.99", 0, 0, 0, "", "");
        ShowGoods goods7 = new ShowGoods(7, "火龙果7", picUrl, picUrl, "肉质鲜嫩，清脆多汁", "13.29", "13.99", 0, 0, 0, "", "");
        ShowGoods goods8 = new ShowGoods(8, "火龙果8", picUrl, picUrl, "肉质鲜嫩，清脆多汁", "13.29", "13.99", 0, 0, 0, "", "");
        ShowGoods goods9 = new ShowGoods(9, "火龙果9", picUrl, picUrl, "肉质鲜嫩，清脆多汁", "13.29", "13.99", 0, 0, 0, "", "");
        ShowGoods goods10 = new ShowGoods(10, "火龙果10", picUrl, picUrl, "肉质鲜嫩，清脆多汁", "13.29", "13.99", 0, 0, 0, "", "");
        goodsList.add(goods1);
        goodsList.add(goods2);
        goodsList.add(goods3);
        goodsList.add(goods4);
        goodsList.add(goods5);
        goodsList.add(goods6);
        goodsList.add(goods7);
        goodsList.add(goods8);
        goodsList.add(goods9);
        goodsList.add(goods10);
    }

    /**
     * 添加条目数据到购物车
     */
    public void addToCart(ShowGoods showGoods) {
        CartGoods cartGoods = showGoods.showGoodsToCartGoods(showGoods);
        MessageEvent event = new MessageEvent();
        event.setObject(cartGoods);
        EventBus.getDefault().post(event);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
