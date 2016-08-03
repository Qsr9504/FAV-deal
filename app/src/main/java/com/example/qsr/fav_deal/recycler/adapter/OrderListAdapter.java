package com.example.qsr.fav_deal.recycler.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.qsr.fav_deal.bean.Order;
import com.example.qsr.fav_deal.recycler.OnEditOrDeleteListener;
import com.example.qsr.fav_deal.recycler.holders.OrderListHolder;

import java.util.List;

/**************************************
 * FileName : com.example.qsr.fav_deal.recycler.adapter
 * Author : qsr
 * Time : 2016/8/3 10:01
 * Description :
 **************************************/
public class OrderListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Order> orderList;

    public void setOnEditOrDeleteListener(OnEditOrDeleteListener onEditOrDeleteListener) {
        this.onEditOrDeleteListener = onEditOrDeleteListener;
    }

    private OnEditOrDeleteListener onEditOrDeleteListener;
    public OrderListAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderListHolder(context,onEditOrDeleteListener,parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((OrderListHolder)holder).bindData(orderList.get(position));
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
