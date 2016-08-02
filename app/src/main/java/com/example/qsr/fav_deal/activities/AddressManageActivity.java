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
import com.example.qsr.fav_deal.ui.AddressDialog;
import com.example.qsr.fav_deal.ui.IconFontTextView;
import com.example.qsr.fav_deal.ui.OnDialogListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressManageActivity extends AppCompatActivity {
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.addAddress)
    IconFontTextView addAddress;
    @Bind(R.id.addreManageRecycle)
    RecyclerView addreManageRecycle;
    private List<Address> addressList = null;
    private AddressDialog dialog;
    private AddressAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_address_manage);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        //获取本地收货地址
        if (addressList == null) {//如果是空的，就显示测试数据
            addressList = new ArrayList<Address>();
            addressList.add(new Address(1, 1, "15171858", "1", "李清照", "江省南昌市双港路1180号"));
            addressList.add(new Address(1, 1, "15655858", "", "李清照1", "江西省南昌市双港路1180号"));
            addressList.add(new Address(1, 1, "16494188", "", "李清照2", "江西南昌市双港路1180号"));
            addressList.add(new Address(1, 1, "15994618", "", "李清照3", "江西省南市双港路1180号"));
            addressList.add(new Address(1, 1, "946158", "", "李清照4", "江省南昌市港路1180号"));
            addressList.add(new Address(1, 1, "15616158", "", "李清照5", "江西省南昌市港路1180号"));
            addressList.add(new Address(1, 1, "159616158", "", "李清照6", "江西南昌市双港路1180号"));
            addressList.add(new Address(1, 1, "1599466158", "", "李清照7", "江西省南昌双港路1180号"));
        }
        adapter = new AddressAdapter(AddressManageActivity.this, addressList);
        adapter.setOnEditOrDeleteListener(new OnEditOrDeleteListener() {
            @Override
            public void onDelete(int position) {
                addressList.remove(position);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onEdit(final int position) {
                //弹出自定义dialog并且初始化这个item信息
                dialog = new AddressDialog(AddressManageActivity.this);
                dialog.setOnDialogListener(new OnDialogListener() {
                    @Override
                    public void onSubmit() {
                        Address address = dialog.getData();
                        addressList.get(position).setA_receiver(address.getA_receiver());
                        addressList.get(position).setA_phone(address.getA_phone());
                        addressList.get(position).setA_detail(address.getA_detail());
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }

                    @Override
                    public void onCancle() {
                        //什么都不需要做
                    }
                });
                dialog.setData(addressList.get(position));
                dialog.show();
            }

            @Override
            public void onItemClick(int position) {
                //不需要有操作
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AddressManageActivity.this, LinearLayoutManager.VERTICAL, false);
        addreManageRecycle.setLayoutManager(linearLayoutManager);
        addreManageRecycle.setAdapter(adapter);
    }

    @OnClick(R.id.back)
    public void back() {
        finish();
    }

    @OnClick(R.id.addAddress)
    public void addAddress() {
        //弹出自定义的dialog
        //弹出自定义dialog并且初始化这个item信息
        dialog = new AddressDialog(AddressManageActivity.this);
        dialog.setOnDialogListener(new OnDialogListener() {
            @Override
            public void onSubmit() {
                Address address = dialog.getData();
                addressList.add(address);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancle() {
                //什么都不需要做
            }
        });
        dialog.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //将数据存入本地
    }
}
