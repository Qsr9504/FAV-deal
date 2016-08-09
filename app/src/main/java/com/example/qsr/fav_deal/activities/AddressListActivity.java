package com.example.qsr.fav_deal.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.bean.Address;
import com.example.qsr.fav_deal.bean.MessageEvent;
import com.example.qsr.fav_deal.bmobUtil.AddressTools;
import com.example.qsr.fav_deal.bmobUtil.MesEventForBmob;
import com.example.qsr.fav_deal.globle.AppConstants;
import com.example.qsr.fav_deal.recycler.OnEditOrDeleteListener;
import com.example.qsr.fav_deal.recycler.adapter.AddressAdapter;
import com.example.qsr.fav_deal.ui.IconFontTextView;
import com.example.qsr.fav_deal.utils.MySPUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    private AddressTools addressTools;
    private AddressAdapter adapter;
    public static final int RETURN_ADDRESS = 50;//返回数据
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        ButterKnife.bind(this);
        addressTools = AddressTools.getInstance(this);
        EventBus.getDefault().register(this);
        initData();
    }

    private void initData() {
        //获取本地收货地址
        if(addressList == null){
            addressList = new ArrayList<Address>();
            addressTools.getAllAddress(MySPUtil.getString(AppConstants.CONFIG.USER_ID));
        }
        adapter = new AddressAdapter(AddressListActivity.this,addressList);
        adapter.setOnEditOrDeleteListener(new OnEditOrDeleteListener() {
            @Override
            public void onDelete(int position) {
            }

            @Override
            public void onEdit(int position) {
            }

            @Override
            public void onItemClick(int position) {
                MesEventForBmob event = new MesEventForBmob();
                event.setObject(addressList.get(position));
                event.setStateCode(RETURN_ADDRESS);
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
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void addreMana(MesEventForBmob eventForBmob) {
        int code = eventForBmob.getStateCode();
        if (code == AddressTools.ALL_ADDRESS_SUCC) {
            addressList.clear();
            //获取所有的地址列表成功
            addressList.addAll((List<Address>) eventForBmob.getObject());
            adapter.notifyDataSetChanged();

        } else if (code == AddressTools.ALL_ADDRESS_ERROR) {
            //获取所有的地址列表错误
            Toast.makeText(this, "获取列表失败", Toast.LENGTH_SHORT).show();

        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        //将数据存入本地
    }
}
