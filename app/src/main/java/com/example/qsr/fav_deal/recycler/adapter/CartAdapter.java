package com.example.qsr.fav_deal.recycler.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.qsr.fav_deal.bean.CartGoods;
import com.example.qsr.fav_deal.recycler.OnRecyclerViewListener;
import com.example.qsr.fav_deal.recycler.holders.CartHolder;

import java.util.List;

/**************************************
 * FileName : com.example.qsr.fav_deal.recycler.adapter
 * Author : qsr
 * Time : 2016/7/31 16:06
 * Description :
 **************************************/
public class CartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<CartGoods> cartGoodsList;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    private OnRecyclerViewListener onRecyclerViewListener;
    public CartAdapter(Context context, List<CartGoods> cartGoodsList) {
        this.context = context;
        this.cartGoodsList = cartGoodsList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CartHolder(context,onRecyclerViewListener,parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((CartHolder)holder).bindData(cartGoodsList.get(position));
    }

    @Override
    public int getItemCount() {
        return cartGoodsList.size() == 0 ? 0 : cartGoodsList.size();
    }

    public void removeItem(int position){
        cartGoodsList.remove(position);
        this.notifyDataSetChanged();
    }
    public void removeAll(){
        cartGoodsList.clear();
        this.notifyDataSetChanged();
    }

}
