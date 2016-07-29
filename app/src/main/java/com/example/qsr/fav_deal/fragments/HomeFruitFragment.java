package com.example.qsr.fav_deal.fragments;

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
import com.example.qsr.fav_deal.adapter.MyRecyclerViewAdapter;
import com.example.qsr.fav_deal.bean.Goods;
import com.example.qsr.fav_deal.utils.UIUtils;

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
public class HomeFruitFragment extends Fragment {
    private List<Goods> goodsList = new ArrayList<Goods>();
    @Bind(R.id.fruit_recyclerView)
    RecyclerView fruitRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = UIUtils.getXmlView(R.layout.fragment_home_fruit);
        ButterKnife.bind(this, view);
        initData();
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(getContext(),goodsList) {
            @Override
            public void addBtnClick(View v, Goods goods) {
                Toast.makeText(getContext(),goods.toString(),Toast.LENGTH_SHORT).show();
            }
        };
        fruitRecyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        fruitRecyclerView.setLayoutManager(linearLayoutManager);

        return view;
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
