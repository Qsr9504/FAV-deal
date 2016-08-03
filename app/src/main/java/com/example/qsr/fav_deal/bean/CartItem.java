package com.example.qsr.fav_deal.bean;

/**************************************
 * FileName : com.example.qsr.fav_deal.bean
 * Author : qsr
 * Time : 2016/7/28 20:28
 * Description : 购物车中的每一个物品
 **************************************/
public class CartItem {
    private  int g_id;//购买的物品id
    private int count;//购买的数量

    public CartItem(int g_id, int count) {
        this.g_id = g_id;
        this.count = count;
    }
    public CartItem() {
    }

    public int getG_id() {
        return g_id;
    }

    public void setG_id(int g_id) {
        this.g_id = g_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
