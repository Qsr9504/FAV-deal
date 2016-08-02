package com.example.qsr.fav_deal.recycler.holders;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.base.BaseViewHolder;
import com.example.qsr.fav_deal.bean.Address;
import com.example.qsr.fav_deal.recycler.OnEditOrDeleteListener;

import butterknife.Bind;

/**************************************
 * FileName : com.example.qsr.fav_deal.recycler.holders
 * Author : qsr
 * Time : 2016/8/2 13:47
 * Description :
 **************************************/
public class AddressHolder extends BaseViewHolder {
    @Bind(R.id.addre_receiver)
    TextView addreReceiver;
    @Bind(R.id.addre_phone)
    TextView addrePhone;
    @Bind(R.id.addre_detail)
    TextView addreDetail;
    @Bind(R.id.editAddress)
    LinearLayout editAddress;
    @Bind(R.id.deleteAddress)
    LinearLayout deleteAddress;
    @Bind(R.id.addre_item)
    CardView addreItem;
    private Context context;
    private OnEditOrDeleteListener onEditOrDeleteListener;

    public AddressHolder(Context context, OnEditOrDeleteListener onEditOrDeleteListener, ViewGroup root) {
        super(context, null, root, R.layout.item_address);
        this.onEditOrDeleteListener = onEditOrDeleteListener;
        this.context = context;
    }

    @Override
    public void bindData(Object o) {
        Address address = (Address) o;
        addreReceiver.setText("收货人:" + address.getA_receiver());
        addrePhone.setText("联系电话" + address.getA_phone());
        addreDetail.setText("送至:" + address.getA_detail());
        editAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditOrDeleteListener.onEdit(getAdapterPosition());
            }
        });
        deleteAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditOrDeleteListener.onDelete(getAdapterPosition());
            }
        });
        addreItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditOrDeleteListener.onItemClick(getAdapterPosition());
            }
        });
    }
}
