package com.example.qsr.fav_deal.bmobUtil;

/**************************************
 * FileName : com.example.qsr.fav_deal.BmobUtil
 * Author : qsr
 * Time : 2016/8/4 10:00
 * Description : 用于bmob后端云使用的eventbus数据类
 **************************************/
public class MesEventForBmob {
    private String string;

    private Object object;

    private Integer stateCode;

    public Integer getStateCode() {
        return stateCode;
    }

    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
    }
    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
