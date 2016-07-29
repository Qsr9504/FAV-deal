package com.example.qsr.fav_deal.bean;

/**************************************
 * FileName : com.example.qsr.fav_deal.bean
 * Author : qsr
 * Time : 2016/7/28 20:37
 * Description : 地址
 **************************************/
public class Address {
    private int a_id;//地址的id
    private int u_id;//购买者用户的id
    private String a_phone;//收货人的联系方式
    private String a_default;//是否是默认地址
    private String a_receiver;//收货人姓名

    public Address(int a_id, int u_id, String a_phone, String a_default, String a_receiver) {
        this.a_id = a_id;
        this.u_id = u_id;
        this.a_phone = a_phone;
        this.a_default = a_default;
        this.a_receiver = a_receiver;
    }

    public Address() {
    }

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getA_phone() {
        return a_phone;
    }

    public void setA_phone(String a_phone) {
        this.a_phone = a_phone;
    }

    public String getA_default() {
        return a_default;
    }

    public void setA_default(String a_default) {
        this.a_default = a_default;
    }

    public String getA_receiver() {
        return a_receiver;
    }

    public void setA_receiver(String a_receiver) {
        this.a_receiver = a_receiver;
    }

    @Override
    public String toString() {
        return "Address{" +
                "a_id=" + a_id +
                ", u_id=" + u_id +
                ", a_phone='" + a_phone + '\'' +
                ", a_default='" + a_default + '\'' +
                ", a_receiver='" + a_receiver + '\'' +
                '}';
    }
}
