package com.example.qsr.fav_deal.bean;

/**************************************
 * FileName : com.example.qsr.p2pfinance.bean
 * Author : qsr
 * Time : 2016/7/4 11:04
 * Description : 用于封装网络返回的数据和状态
 **************************************/
public class ResultState {

    private int state;

    private String content;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
