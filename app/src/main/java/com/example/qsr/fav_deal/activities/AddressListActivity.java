package com.example.qsr.fav_deal.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.bean.Address;
import com.example.qsr.fav_deal.bean.MessageEvent;
import com.example.qsr.fav_deal.recycler.OnEditOrDeleteListener;
import com.example.qsr.fav_deal.recycler.adapter.AddressAdapter;
import com.example.qsr.fav_deal.ui.IconFontTextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressListActivity extends AppCompatActivity {

    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.addAddress)
    IconFontTextView addAddress;
    @Bind(R.id.addreRecycle)
    RecyclerView addreRecycle;
    private List<Address> addressList = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        //获取本地收货地址
        if(addressList == null){//如果是空的，就显示测试数据
            addressList = new ArrayList<Address>();
            addressList.add(new Address(1,1,"15171858","1","李清照","江省南昌市双港路1180号"));
            addressList.add(new Address(1,1,"15655858","","李清照1","江西省南昌市双港路1180号"));
            addressList.add(new Address(1,1,"16494188","","李清照2","江西南昌市双港路1180号"));
            addressList.add(new Address(1,1,"15994618","","李清照3","江西省南市双港路1180号"));
            addressList.add(new Address(1,1,"946158","","李清照4","江省南昌市港路1180号"));
            addressList.add(new Address(1,1,"15616158","","李清照5","江西省南昌市港路1180号"));
            addressList.add(new Address(1,1,"159616158","","李清照6","江西南昌市双港路1180号"));
            addressList.add(new Address(1,1,"1599466158","","李清照7","江西省南昌双港路1180号"));
        }
        AddressAdapter adapter = new AddressAdapter(AddressListActivity.this,addressList);
        adapter.setOnEditOrDeleteListener(new OnEditOrDeleteListener() {
            @Override
            public void onDelete(int position) {
            }

            @Override
            public void onEdit(int position) {
            }

            @Override
            public void onItemClick(int position) {
                MessageEvent event = new MessageEvent();
                event.setObject(addressList.get(position));
                EventBus.getDefault().post(event);
                finish();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AddressListActivity.this, LinearLayoutManager.VERTICAL, false);
        addreRecycle.setLayoutManager(linearLayoutManager);
        addreRecycle.setAdapter(adapter);
    }

    @OnClick(R.id.back)
    public void back(){
        finish();
    }
    @OnClick(R.id.addAddress)
    public void addAddress(){
        //弹出自定义的dialog
    }

    @Override
    protected void onPause() {
        super.onPause();
        //将数据存入本地
    }
}
