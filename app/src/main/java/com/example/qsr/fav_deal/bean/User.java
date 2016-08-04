package com.example.qsr.fav_deal.bean;

import cn.bmob.v3.BmobUser;

/**************************************
 * FileName : com.example.qsr.fav_deal.bean
 * Author : qsr
 * Time : 2016/7/28 16:46
 * Description : 用户表
 **************************************/
public class User extends BmobUser{
    private String u_id;//用户的唯一标识
    private String u_avatar;//用户的头像url地址
    private String u_account;//用户的账户
    private String u_pwd;//用户的密码
    private int u_integra;//用户积分
    private String u_realName;//用户真实姓名
    private String u_phone;//用户常用手机号码
    private int u_role;//用户的角色
    private int u_money;//用户的当前资产
    private String u_email;//用户邮箱

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getU_avatar() {
        return u_avatar;
    }

    public void setU_avatar(String u_avatar) {
        this.u_avatar = u_avatar;
    }

    public String getU_account() {
        return u_account;
    }

    public void setU_account(String u_account) {
        this.u_account = u_account;
    }

    public String getU_pwd() {
        return u_pwd;
    }

    public void setU_pwd(String u_pwd) {
        this.u_pwd = u_pwd;
    }

    public int getU_integra() {
        return u_integra;
    }

    public void setU_integra(int u_integra) {
        this.u_integra = u_integra;
    }

    public String getU_realName() {
        return u_realName;
    }

    public void setU_realName(String u_realName) {
        this.u_realName = u_realName;
    }

    public String getU_phone() {
        return u_phone;
    }

    public void setU_phone(String u_phone) {
        this.u_phone = u_phone;
    }

    public int getU_role() {
        return u_role;
    }

    public void setU_role(int u_role) {
        this.u_role = u_role;
    }

    public int getU_money() {
        return u_money;
    }

    public void setU_money(int u_money) {
        this.u_money = u_money;
    }

    public User(String u_id, String u_avatar, String u_account, String u_pwd, int u_integra, String u_realName, String u_phone, int u_role, int u_money) {
        this.u_id = u_id;
        this.u_avatar = u_avatar;
        this.u_account = u_account;
        this.u_pwd = u_pwd;
        this.u_integra = u_integra;
        this.u_realName = u_realName;
        this.u_phone = u_phone;
        this.u_role = u_role;
        this.u_money = u_money;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "u_id=" + u_id +
                ", u_avatar='" + u_avatar + '\'' +
                ", u_account='" + u_account + '\'' +
                ", u_pwd='" + u_pwd + '\'' +
                ", u_integra=" + u_integra +
                ", u_realName='" + u_realName + '\'' +
                ", u_phone='" + u_phone + '\'' +
                ", u_role=" + u_role +
                ", u_money=" + u_money +
                '}';
    }
}
