package com.example.qsr.fav_deal.recycler;

/**************************************
 * FileName : com.example.qsr.fav_deal.recycler
 * Author : qsr
 * Time : 2016/8/2 13:51
 * Description : 删除和修改的接口
 **************************************/
public interface OnEditOrDeleteListener {
    void onDelete(int position);//删除某一个
    void onEdit(int position);//修改某一个
    void onItemClick(int position);//整个条目点击
}
