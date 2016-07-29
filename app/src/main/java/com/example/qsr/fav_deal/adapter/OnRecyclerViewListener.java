package com.example.qsr.fav_deal.adapter;

/**************************************
 * FileName : com.example.qsr.fav_deal.adapter
 * Author : qsr
 * Time : 2016/7/29 16:01
 * Description : 用于RecyclerView 的 条目监听事件
 **************************************/
public interface OnRecyclerViewListener {
    void onItemClick(int position);
    boolean onItemLongClick(int position); //长按监听
}
