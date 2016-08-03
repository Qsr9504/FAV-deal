package com.example.qsr.fav_deal.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.bean.Order;
import com.example.qsr.fav_deal.recycler.OnEditOrDeleteListener;
import com.example.qsr.fav_deal.recycler.adapter.OrderListAdapter;
import com.example.qsr.fav_deal.ui.IconFontTextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        ButterKnife.bind(this);

        OrderListAdapter adapter = new OrderListAdapter(OrderListActivity.this, getData());
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

    public List<Order> getData() {
        data = new ArrayList<Order>();
        for (int i=0;i<10;i++){
            data.add(new Order(123465,1,456,350+i*i,"已付款","","",1,null));
        }
        return data;
    }
}
