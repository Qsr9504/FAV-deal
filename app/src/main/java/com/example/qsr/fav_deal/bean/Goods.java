package com.example.qsr.fav_deal.bean;

/**************************************
 * FileName : com.example.qsr.fav_deal.bean
 * Author : qsr
 * Time : 2016/7/28 16:34
 * Description : 商品详情
 **************************************/
public class Goods {
    private int g_id;//唯一标示
    private String g_name;//商品名称
    private String g_pic; //商品图片url地址
    private String g_pic_big;//商品图片大图URL地址
    private String g_desc;//商品简短描述
    private String memb_price;//商品会员价格
    private String price;//商品非会员价格
    private int g_repertory;//商品库存
    private int g_sales;//商品销售量
    private int g_type;//商品类型
    private String g_detail;//商品详细（文字）
    private String g_detailUrl;//商品详细大图url地址

    public int getG_id() {
        return g_id;
    }

    public void setG_id(int g_id) {
        this.g_id = g_id;
    }

    public String getG_name() {
        return g_name;
    }

    public void setG_name(String g_name) {
        this.g_name = g_name;
    }

    public String getG_pic() {
        return g_pic;
    }

    public void setG_pic(String g_pic) {
        this.g_pic = g_pic;
    }

    public String getG_pic_big() {
        return g_pic_big;
    }

    public void setG_pic_big(String g_pic_big) {
        this.g_pic_big = g_pic_big;
    }

    public String getG_desc() {
        return g_desc;
    }

    public void setG_desc(String g_desc) {
        this.g_desc = g_desc;
    }

    public String getMemb_price() {
        return memb_price;
    }

    public void setMemb_price(String memb_price) {
        this.memb_price = memb_price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getG_repertory() {
        return g_repertory;
    }

    public void setG_repertory(int g_repertory) {
        this.g_repertory = g_repertory;
    }

    public int getG_sales() {
        return g_sales;
    }

    public void setG_sales(int g_sales) {
        this.g_sales = g_sales;
    }

    public int getG_type() {
        return g_type;
    }

    public void setG_type(int g_type) {
        this.g_type = g_type;
    }

    public String getG_detail() {
        return g_detail;
    }

    public void setG_detail(String g_detail) {
        this.g_detail = g_detail;
    }

    public String getG_detailUrl() {
        return g_detailUrl;
    }

    public void setG_detailUrl(String g_detailUrl) {
        this.g_detailUrl = g_detailUrl;
    }

    public Goods(int g_id, String g_name, String g_pic, String g_pic_big, String g_desc, String memb_price, String price, int g_repertory, int g_sales, int g_type, String g_detail, String g_detailUrl) {
        this.g_id = g_id;
        this.g_name = g_name;
        this.g_pic = g_pic;
        this.g_pic_big = g_pic_big;
        this.g_desc = g_desc;
        this.memb_price = memb_price;
        this.price = price;
        this.g_repertory = g_repertory;
        this.g_sales = g_sales;
        this.g_type = g_type;
        this.g_detail = g_detail;
        this.g_detailUrl = g_detailUrl;
    }

    public Goods() {
    }

    @Override
    public String toString() {
        return "Goods{" +
                "g_id=" + g_id +
                ", g_name='" + g_name + '\'' +
                ", g_pic='" + g_pic + '\'' +
                ", g_pic_big='" + g_pic_big + '\'' +
                ", g_desc='" + g_desc + '\'' +
                ", memb_price='" + memb_price + '\'' +
                ", price='" + price + '\'' +
                ", g_repertory=" + g_repertory +
                ", g_sales=" + g_sales +
                ", g_type=" + g_type +
                ", g_detail='" + g_detail + '\'' +
                ", g_detailUrl='" + g_detailUrl + '\'' +
                '}';
    }
}
