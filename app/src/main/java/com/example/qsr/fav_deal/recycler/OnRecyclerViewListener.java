package com.example.qsr.fav_deal.recycler;

import android.view.View;

/**************************************
 * FileName : com.example.qsr.fav_deal.adapter
 * Author : qsr
 * Time : 2016/7/29 16:01
 * Description : 用于RecyclerView 的 条目监听事件
 **************************************/
public interface OnRecyclerViewListener {
    void onItemClick(int position);//整个条目的监听事件
    boolean onItemLongClick(View v, int position); //长按监听

    void onAddBtn(int position);//点击添加按钮事件监听
    void onCutBtn(int position);//点击减少按钮事件监听

}
