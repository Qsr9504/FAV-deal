package com.example.qsr.fav_deal.bean;

/**************************************
 * FileName : com.example.qsr.fav_deal.bean
 * Author : qsr
 * Time : 2016/7/27 10:28
 * Description : 本地数据库model
 **************************************/
public class DBBean {
    private int _id;
    private String url;
    private String content;
    private String time;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public DBBean(int _id, String url, String content, String time) {
        this._id = _id;
        this.url = url;
        this.content = content;
        this.time = time;
    }

    public DBBean() {
    }

    @Override
    public String toString() {
        return "DBBean{" +
                "_id=" + _id +
                ", url='" + url + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
