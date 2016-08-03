package com.example.qsr.fav_deal.bean;

import java.util.List;

/**************************************
 * FileName : com.example.qsr.fav_deal.bean
 * Author : qsr
 * Time : 2016/7/28 20:30
 * Description : 订单
 **************************************/
public class Order {
    private int o_id;//订单的唯一标识
    private int u_id;//订单所属的用户id
    private int time;//订单时间
    private int o_money;//订单总金额
    private String o_state = "10";//订单进度 - 0 已付款 - 1 商家已接单 -  2 已发货  - 3 交易结束
    private String o_judge;//本订单的用户评价
    private String o_note;//本订单的用户备注
    private int a_id;//地址的id
    private List<CartItem> list;//订单中的物品详情

    public int getO_id() {
        return o_id;
    }

    public void setO_id(int o_id) {
        this.o_id = o_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getO_money() {
        return o_money;
    }

    public void setO_money(int o_money) {
        this.o_money = o_money;
    }

    public String getO_state() {
        return o_state;
    }

    public void setO_state(String o_state) {
        this.o_state = o_state;
    }

    public String getO_judge() {
        return o_judge;
    }

    public void setO_judge(String o_judge) {
        this.o_judge = o_judge;
    }

    public String getO_note() {
        return o_note;
    }

    public void setO_note(String o_note) {
        this.o_note = o_note;
    }

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public List<CartItem> getList() {
        return list;
    }

    public void setList(List<CartItem> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Order{" +
                "o_id=" + o_id +
                ", u_id=" + u_id +
                ", time=" + time +
                ", o_money=" + o_money +
                ", o_state='" + o_state + '\'' +
                ", o_judge='" + o_judge + '\'' +
                ", o_note='" + o_note + '\'' +
                ", a_id=" + a_id +
                ", list=" + list +
                '}';
    }

    public Order(int o_id, int u_id, int time, int o_money, String o_state, String o_judge, String o_note, int a_id, List<CartItem> list) {
        this.o_id = o_id;
        this.u_id = u_id;
        this.time = time;
        this.o_money = o_money;
        this.o_state = o_state;
        this.o_judge = o_judge;
        this.o_note = o_note;
        this.a_id = a_id;
        this.list = list;
    }

    public Order() {
    }
}
