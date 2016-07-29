package com.example.qsr.fav_deal.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.activities.GoodsDetailActivity;
import com.example.qsr.fav_deal.adapter.HomeListAdapter;
import com.example.qsr.fav_deal.adapter.MyRecyclerViewAdapter;
import com.example.qsr.fav_deal.base.BaseFragment;
import com.example.qsr.fav_deal.bean.Goods;
import com.example.qsr.fav_deal.globle.App;
import com.example.qsr.fav_deal.globle.AppNetConfig;
import com.example.qsr.fav_deal.utils.LogUtil;
import com.example.qsr.fav_deal.utils.UIUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;

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
    private List<Goods> goodsList = new ArrayList<Goods>();
    private Intent intent;
    @Bind(R.id.fruit_listView)
    ListView fruit_listView;
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
        LogUtil.MyLog_e(getContext(),content);
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

        HomeListAdapter adapter = new HomeListAdapter(getContext(),goodsList) {
            @Override
            public void addBtnClick(View v, Goods goods) {
                //添加至购物车操作
                Toast.makeText(getContext(),goods.toString(),Toast.LENGTH_SHORT).show();
            }
        };
        fruit_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext(),goodsList.get(position).toString(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(),GoodsDetailActivity.class);
                bundle.putSerializable("good",goodsList.get(position));
                intent.putExtra("fruit_detail",bundle);
                startActivity(intent);
            }
        });
        fruit_listView.setAdapter(adapter);

    }

    @Override
    protected void initTitle() {

    }

    private void initData() {
        String picUrl = "http://file.bmob.cn/M01/E1/9C/oYYBAFePPU6ADBA2AAAdNdM4_BM149.jpg";
        Goods goods1 = new Goods(1, "小樱桃", picUrl, picUrl, "肉质鲜嫩，清脆多汁", "13.29", "13.99", 0, 0, 0, "", "");
        Goods goods2 = new Goods(2, "火龙果2", picUrl, picUrl,  "肉质鲜嫩，清脆多汁", "13.29", "13.99", 0, 0, 0, "", "");
        Goods goods3 = new Goods(3,"火龙果3", picUrl, picUrl,  "肉质鲜嫩，清脆多汁", "13.29", "13.99", 0, 0, 0, "", "");
        Goods goods4 = new Goods(4,"火龙果4",  picUrl, picUrl, "肉质鲜嫩，清脆多汁", "13.29", "13.99", 0, 0, 0, "", "");
        Goods goods5 = new Goods(5,"火龙果5", picUrl, picUrl,  "肉质鲜嫩，清脆多汁", "13.29", "13.99", 0, 0, 0, "", "");
        Goods goods6 = new Goods(6,"火龙果6",  picUrl, picUrl, "肉质鲜嫩，清脆多汁", "13.29", "13.99", 0, 0, 0, "", "");
        Goods goods7 = new Goods(7,"火龙果7", picUrl, picUrl,  "肉质鲜嫩，清脆多汁", "13.29", "13.99", 0, 0, 0, "", "");
        Goods goods8 = new Goods(8,"火龙果8", picUrl, picUrl,  "肉质鲜嫩，清脆多汁", "13.29", "13.99", 0, 0, 0, "", "");
        Goods goods9 = new Goods(9,"火龙果9", picUrl, picUrl,  "肉质鲜嫩，清脆多汁", "13.29", "13.99", 0, 0, 0, "", "");
        Goods goods10 = new Goods(10,"火龙果10",  picUrl, picUrl, "肉质鲜嫩，清脆多汁", "13.29", "13.99", 0, 0, 0, "", "");
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

}
