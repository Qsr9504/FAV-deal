package com.example.qsr.fav_deal.bean;

/**************************************
 * FileName : com.example.qsr.fav_deal.bean
 * Author : qsr
 * Time : 2016/8/1 17:23
 * Description : 侧滑中的每一个条目
 **************************************/
public class GridViewItem {
    private String ImageUrl;//图片的url地址
    private String name;//分类物品名称

    public GridViewItem(String imageUrl, String name) {
        ImageUrl = imageUrl;
        this.name = name;
    }

    public GridViewItem() {
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GridViewItem{" +
                "ImageUrl='" + ImageUrl + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
