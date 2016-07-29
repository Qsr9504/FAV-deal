package com.example.qsr.fav_deal.bean;

import java.util.List;

/**************************************
 * FileName : com.example.qsr.fav_deal.bean
 * Author : qsr
 * Time : 2016/7/28 20:26
 * Description : 购物车
 **************************************/
public class Cart {
    private int c_id;//购物车的唯一标识
    private int u_id;//所属的用户
    private List<CartItem> list;//购物车中的内容

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public List<CartItem> getList() {
        return list;
    }

    public void setList(List<CartItem> list) {
        this.list = list;
    }

    public Cart(int c_id, int u_id, List<CartItem> list) {
        this.c_id = c_id;
        this.u_id = u_id;
        this.list = list;
    }

    public Cart() {
    }

    @Override
    public String toString() {
        return "Cart{" +
                "c_id=" + c_id +
                ", u_id=" + u_id +
                ", list=" + list +
                '}';
    }
}
