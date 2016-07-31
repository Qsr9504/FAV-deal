package com.example.qsr.fav_deal.bean;

/**************************************
 * FileName : com.example.qsr.fav_deal.bean
 * Author : qsr
 * Time : 2016/7/31 15:26
 * Description :
 **************************************/
public class CartGoods extends Goods {
    private int count = 1;//购买某一物品的数量

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public CartGoods(int g_id, String g_name, String g_pic, String g_pic_big, String g_desc, String memb_price, String price, int g_repertory, int g_sales, int g_type, String g_detail, String g_detailUrl) {
        super(g_id, g_name, g_pic, g_pic_big, g_desc, memb_price, price, g_repertory, g_sales, g_type, g_detail, g_detailUrl);
    }
    public CartGoods() {
    }
}
