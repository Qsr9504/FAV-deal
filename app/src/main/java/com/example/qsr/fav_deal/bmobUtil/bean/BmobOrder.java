package com.example.qsr.fav_deal.bmobUtil.bean;

import com.example.qsr.fav_deal.bean.CartItem;
import com.example.qsr.fav_deal.bean.Order;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;

/**************************************
 * FileName : com.example.qsr.fav_deal.bmobUtil.bean
 * Author : qsr
 * Time : 2016/8/8 13:01
 * Description :
 **************************************/
public class BmobOrder extends BmobObject {
    private String o_id;//订单的唯一标识
    private String u_id;//订单所属的用户id
    private String time;//订单时间
    private Integer o_money;//订单总金额
    private String o_state = "0";//订单进度 - 0 已付款 - 1 商家已接单 -  2 已发货  - 3 交易结束
    private String o_judge;//本订单的用户评价
    private String o_note;//本订单的用户备注
    private String a_id;//地址的id
    private String GoodsDetail;//购物车中物品json字符串

    @Override
    public String toString() {
        return "BmobOrder{" +
                "o_id=" + o_id +
                ", u_id='" + u_id + '\'' +
                ", time=" + time +
                ", o_money=" + o_money +
                ", o_state='" + o_state + '\'' +
                ", o_judge='" + o_judge + '\'' +
                ", o_note='" + o_note + '\'' +
                ", a_id=" + a_id +
                ", GoodsDetail='" + GoodsDetail + '\'' +
                '}';
    }

    public String getO_id() {
        return o_id;
    }

    public void setO_id(String o_id) {
        this.o_id = o_id;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getO_money() {
        return o_money;
    }

    public void setO_money(Integer o_money) {
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

    public String getA_id() {
        return a_id;
    }

    public void setA_id(String a_id) {
        this.a_id = a_id;
    }

    public String getGoodsDetail() {
        return GoodsDetail;
    }

    public void setGoodsDetail(String goodsDetail) {
        GoodsDetail = goodsDetail;
    }

    public BmobOrder() {

    }

    public BmobOrder(String o_id, String u_id, String time, Integer o_money, String o_state, String o_judge, String o_note, String a_id, String goodsDetail) {

        this.o_id = o_id;
        this.u_id = u_id;
        this.time = time;
        this.o_money = o_money;
        this.o_state = o_state;
        this.o_judge = o_judge;
        this.o_note = o_note;
        this.a_id = a_id;
        GoodsDetail = goodsDetail;
    }
    public static List<Order> BmobOrderToOrder(List<BmobOrder> list){
        List<Order> orders = new ArrayList<Order>();
        for (BmobOrder b:list) {
            Order order = new Order();
            order.setU_id(b.getU_id());
            order.setO_id(b.getO_id());
            order.setTime(b.getTime());
            order.setO_money(b.getO_money());
            order.setO_state(b.getO_state());
            order.setO_judge(b.getO_judge());
            order.setO_note(b.getO_note());
            order.setA_id(b.getA_id());
            Gson gson = new Gson();
            order.setList((List<CartItem>) gson.fromJson(b.getGoodsDetail(),new TypeToken<List<CartItem>>(){}.getType()));
            orders.add(order);
        }
        return orders;
    }
}
