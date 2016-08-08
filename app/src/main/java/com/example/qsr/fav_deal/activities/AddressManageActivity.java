package com.example.qsr.fav_deal.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.qsr.fav_deal.MainActivity;
import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.bean.Address;
import com.example.qsr.fav_deal.bean.MessageEvent;
import com.example.qsr.fav_deal.bmobUtil.AddressTools;
import com.example.qsr.fav_deal.bmobUtil.MesEventForBmob;
import com.example.qsr.fav_deal.globle.AppConstants;
import com.example.qsr.fav_deal.recycler.OnEditOrDeleteListener;
import com.example.qsr.fav_deal.recycler.adapter.AddressAdapter;
import com.example.qsr.fav_deal.ui.AddressDialog;
import com.example.qsr.fav_deal.ui.IconFontTextView;
import com.example.qsr.fav_deal.ui.OnDialogListener;
import com.example.qsr.fav_deal.utils.MySPUtil;
import com.example.qsr.fav_deal.utils.TextUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collection;
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
    private AddressTools addressTools;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_address_manage);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        addressTools = AddressTools.getInstance(this);
        initData();
    }

    private void initData() {
        //获取本地收货地址
        if (addressList == null) {
            addressList = new ArrayList<Address>();
            addressTools.getAllAddress(MySPUtil.getString(AppConstants.CONFIG.USER_ID));
        }
        adapter = new AddressAdapter(AddressManageActivity.this, addressList);
        adapter.setOnEditOrDeleteListener(new OnEditOrDeleteListener() {
            @Override
            public void onDelete(int position) {
                addressTools.delete(addressList.get(position).getObjectId());
                addressList.remove(position);
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
                        addressTools.updateAdd(MySPUtil.getString(AppConstants.CONFIG.USER_ID),addressList.get(position));
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
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void addreMana(MesEventForBmob eventForBmob){
        int code = eventForBmob.getStateCode();
        if(code == AddressTools.SAVE_ADDRESS_SUCC){
            Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
            Address address = (Address) eventForBmob.getObject();
            addressList.add(address);
            MySPUtil.save(AppConstants.CONFIG.DEFAULT_ADDRESS,address.getObjectId());
            adapter.notifyDataSetChanged();
        }else if (code == AddressTools.SAVE_ADDRESS_ERROE){
            Toast.makeText(this,"保存失败",Toast.LENGTH_SHORT).show();
        }else if (code == AddressTools.ALL_ADDRESS_SUCC){
            addressList.clear();
            //获取所有的地址列表成功
            addressList.addAll((List<Address>) eventForBmob.getObject());
            adapter.notifyDataSetChanged();

        }else if (code == AddressTools.ALL_ADDRESS_ERROR){
            //获取所有的地址列表错误
            Toast.makeText(this,"获取列表失败",Toast.LENGTH_SHORT).show();

        }else if (code == AddressTools.DELETE_ADDRESS_SUCC){
            //删除成功
            adapter.notifyDataSetChanged();
        }else if (code == AddressTools.DELETE_ADDRESS_ERROE){
            Toast.makeText(this,"删除失败",Toast.LENGTH_SHORT).show();
        }else if (code == AddressTools.UPDATE_ADDRESS_SUCC){
            //更新成功
            adapter.notifyDataSetChanged();
            dialog.dismiss();
        }else if (code == AddressTools.UPDATE_ADDRESS_ERROE){
            Toast.makeText(this,"更新失败",Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }

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
                if(TextUtil.isEmpty(address.getA_detail().toString(),address.getA_phone().toString(),
                        address.getA_receiver().toString())){
                    Toast.makeText(AddressManageActivity.this,"不能为空",Toast.LENGTH_SHORT).show();
                }else{
                    address.setU_id(MySPUtil.getString(AppConstants.CONFIG.USER_ID));//设置当前用户
                    address.setA_default("1");//设置为默认
                    addressTools.addAddress(address);
                    dialog.dismiss();
                }
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
