package com.example.qsr.fav_deal.recycler.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.qsr.fav_deal.bean.Address;
import com.example.qsr.fav_deal.bean.CartGoods;
import com.example.qsr.fav_deal.recycler.OnEditOrDeleteListener;
import com.example.qsr.fav_deal.recycler.OnRecyclerViewListener;
import com.example.qsr.fav_deal.recycler.holders.AddressHolder;

import java.util.List;

/**************************************
 * FileName : com.example.qsr.fav_deal.recycler.adapter
 * Author : qsr
 * Time : 2016/8/2 14:02
 * Description :
 **************************************/
public class AddressAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private OnEditOrDeleteListener onEditOrDeleteListener;
    private List<Address> addressList;

    public AddressAdapter(Context context, List<Address> addressList) {
        this.context = context;
        this.addressList = addressList;
    }
    public void setOnEditOrDeleteListener(OnEditOrDeleteListener onEditOrDeleteListener) {
        this.onEditOrDeleteListener = onEditOrDeleteListener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddressHolder(context,onEditOrDeleteListener,parent);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((AddressHolder)holder).bindData(addressList.get(position));
    }
    @Override
    public int getItemCount() {
        return addressList.size();
    }
    public void removeItem(int position){
        addressList.remove(position);
        this.notifyDataSetChanged();
    }
    public void removeAll(){
        addressList.clear();
        this.notifyDataSetChanged();
    }
}
