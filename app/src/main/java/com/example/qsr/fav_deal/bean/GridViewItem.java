package com.example.qsr.fav_deal.bean;

/**************************************
 * FileName : com.example.qsr.fav_deal.bean
 * Author : qsr
 * Time : 2016/8/1 17:23
 * Description : 侧滑中的每一个条目
 **************************************/
public class GridViewItem {
    private int ImageId;//图片的id
    private String name;//分类物品名称

    @Override
    public String toString() {
        return "GridViewItem{" +
                "ImageId=" + ImageId +
                ", name='" + name + '\'' +
                '}';
    }

    public GridViewItem(int imageId, String name) {
        ImageId = imageId;
        this.name = name;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
