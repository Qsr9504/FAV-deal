package com.example.qsr.fav_deal.bean;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**************************************
 * FileName : EventBus传递数据的实体类工具
 * Author : qsr
 * Time : 2016/7/31 2:46
 * Description : EventBus使用
 **************************************/
public class MessageEvent {
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
