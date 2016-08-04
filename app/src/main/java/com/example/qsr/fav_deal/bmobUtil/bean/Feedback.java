package com.example.qsr.fav_deal.bmobUtil.bean;

import cn.bmob.v3.BmobObject;

/**************************************
 * FileName : com.example.qsr.fav_deal.bmobUtil.bean
 * Author : qsr
 * Time : 2016/8/4 17:00
 * Description : 反馈的实体类
 **************************************/
public class Feedback extends BmobObject {
    private String fb_id;//反馈的唯一标识
    private String fb_owner;//反馈者的id
    private String fb_title;//反馈标题
    private String fb_content;//反馈内容

    @Override
    public String toString() {
        return "Feedback{" +
                "fb_id='" + fb_id + '\'' +
                ", fb_owner='" + fb_owner + '\'' +
                ", fb_title='" + fb_title + '\'' +
                ", fb_content='" + fb_content + '\'' +
                '}';
    }

    public String getFb_id() {
        return fb_id;
    }

    public void setFb_id(String fb_id) {
        this.fb_id = fb_id;
    }

    public String getFb_owner() {
        return fb_owner;
    }

    public void setFb_owner(String fb_owner) {
        this.fb_owner = fb_owner;
    }

    public String getFb_title() {
        return fb_title;
    }

    public void setFb_title(String fb_title) {
        this.fb_title = fb_title;
    }

    public String getFb_content() {
        return fb_content;
    }

    public void setFb_content(String fb_content) {
        this.fb_content = fb_content;
    }

    public Feedback() {

    }

    public Feedback(String fb_id, String fb_owner, String fb_title, String fb_content) {

        this.fb_id = fb_id;
        this.fb_owner = fb_owner;
        this.fb_title = fb_title;
        this.fb_content = fb_content;
    }
}
