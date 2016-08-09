package com.example.qsr.fav_deal.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.bean.Order;
import com.example.qsr.fav_deal.bmobUtil.MesEventForBmob;
import com.example.qsr.fav_deal.bmobUtil.OrderTools;
import com.example.qsr.fav_deal.bmobUtil.bean.BmobOrder;
import com.example.qsr.fav_deal.recycler.OnEditOrDeleteListener;
import com.example.qsr.fav_deal.recycler.adapter.OrderListAdapter;
import com.example.qsr.fav_deal.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DeliverActivity extends AppCompatActivity {

    @Bind(R.id.t_left)
    ImageView tLeft;
    @Bind(R.id.t_text)
    TextView tText;
    @Bind(R.id.t_right)
    ImageView tRight;
    @Bind(R.id.deliver_recycler)
    RecyclerView deliverRecycler;
    private List<Order> orderList;
    private OrderListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_deliver);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        orderList = new ArrayList<Order>();
        tRight.setVisibility(View.GONE);
        adapter = new OrderListAdapter(this,orderList);
        adapter.setOnEditOrDeleteListener(new OnEditOrDeleteListener() {
            @Override
            public void onDelete(int position) {

            }

            @Override
            public void onEdit(int position) {

            }

            @Override
            public void onItemClick(int position) {
                Toast.makeText(DeliverActivity.this,orderList.get(position).toString(),Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DeliverActivity.this, LinearLayoutManager.VERTICAL, false);
        deliverRecycler.setLayoutManager(linearLayoutManager);
        deliverRecycler.setAdapter(adapter);
    }
    @OnClick(R.id.t_left)
    public void t_left(View v){
        finish();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getDeliver(MesEventForBmob eventForBmob){
        int code = eventForBmob.getStateCode();
        if(code == OrderTools.GET_ORDER_SUCC){
            LogUtil.MyLog_e(this, eventForBmob.getObject().toString());
            //获取类型成功
            orderList.clear();
            orderList.addAll((List<Order>) eventForBmob.getObject());
            changeText(eventForBmob.getString());
            adapter.notifyDataSetChanged();
        }else if(code == OrderTools.GET_ORDER_FAIL){
            //获取类型失败
        }
    }

    private void changeText(String type) {
        if(type.equals("0")){
            tText.setText("已付款");
        }else if(type.equals("1")){
            tText.setText("已接单");
        }else if(type.equals("2")){
            tText.setText("已发货");
        }else if(type.equals("3")){
            tText.setText("订单结束");
        }
    }


}
