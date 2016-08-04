package com.example.qsr.fav_deal.bmobUtil.bean;

import cn.bmob.v3.BmobObject;

/**************************************
 * FileName : com.example.qsr.fav_deal.bmobUtil.bean
 * Author : qsr
 * Time : 2016/8/4 12:42
 * Description : 版本信息的实体类
 **************************************/
public class Version extends BmobObject{
    private Integer version_code;//版本号
    private String version_desc;//版本的描述
    private String version_url;//版本下载地址
    private String version_name;//版本名称

    @Override
    public String toString() {
        return "Version{" +
                "version_code=" + version_code +
                ", version_desc='" + version_desc + '\'' +
                ", version_url='" + version_url + '\'' +
                ", version_name='" + version_name + '\'' +
                '}';
    }

    public Integer getVersion_code() {
        return version_code;
    }

    public void setVersion_code(Integer version_code) {
        this.version_code = version_code;
    }

    public String getVersion_desc() {
        return version_desc;
    }

    public void setVersion_desc(String version_desc) {
        this.version_desc = version_desc;
    }

    public String getVersion_url() {
        return version_url;
    }

    public void setVersion_url(String version_url) {
        this.version_url = version_url;
    }

    public String getVersion_name() {
        return version_name;
    }

    public void setVersion_name(String version_name) {
        this.version_name = version_name;
    }

    public Version(String tableName) {
        super(tableName);
    }

    public Version() {
    }

    public Version(Integer version_code, String version_desc, String version_url, String version_name) {
        this.version_code = version_code;
        this.version_desc = version_desc;
        this.version_url = version_url;
        this.version_name = version_name;
    }

    public Version(String tableName, Integer version_code, String version_desc, String version_url, String version_name) {
        super(tableName);
        this.version_code = version_code;
        this.version_desc = version_desc;
        this.version_url = version_url;
        this.version_name = version_name;
    }
}
