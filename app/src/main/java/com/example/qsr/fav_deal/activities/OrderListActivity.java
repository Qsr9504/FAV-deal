package com.example.qsr.fav_deal.activities;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.bean.MessageEvent;
import com.example.qsr.fav_deal.bean.Order;
import com.example.qsr.fav_deal.bmobUtil.MesEventForBmob;
import com.example.qsr.fav_deal.bmobUtil.OrderTools;
import com.example.qsr.fav_deal.recycler.OnEditOrDeleteListener;
import com.example.qsr.fav_deal.recycler.adapter.OrderListAdapter;
import com.example.qsr.fav_deal.ui.IconFontTextView;
import com.example.qsr.fav_deal.utils.ImageUtil;

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

public class OrderListActivity extends AppCompatActivity {

    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.orderlistRecycle)
    RecyclerView orderlistRecycle;
    private List<Order> data;
    private OrderListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_order_list);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        data = new ArrayList<Order>();
        adapter = new OrderListAdapter(OrderListActivity.this, data);
        adapter.setOnEditOrDeleteListener(new OnEditOrDeleteListener() {
            @Override
            public void onDelete(int position) {
            }

            @Override
            public void onEdit(int position) {
            }

            @Override
            public void onItemClick(int position) {
                Toast.makeText(OrderListActivity.this,data.get(position).toString(),Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OrderListActivity.this,LinearLayoutManager.VERTICAL,false);
        orderlistRecycle.setLayoutManager(linearLayoutManager);
        orderlistRecycle.setAdapter(adapter);
    }
    @OnClick(R.id.back)
    public void back(View view){
        finish();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setAvatarFromRes(MesEventForBmob event) {
        int code = event.getStateCode();
        if(code == OrderTools.ALL_ORDER_SUCC){
            data.clear();
            data.addAll((List<Order>) event.getObject());
            orderlistRecycle.setAdapter(adapter);
        }else if(code == OrderTools.ALL_ORDER_FAIL){
            Toast.makeText(getApplicationContext(),"获取失败",Toast.LENGTH_SHORT).show();
        }
    }
}
