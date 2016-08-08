package com.example.qsr.fav_deal.bean;

/**************************************
 * FileName : com.example.qsr.fav_deal.bean
 * Author : qsr
 * Time : 2016/7/31 1:31
 * Description : 商品列表展示的实体类
 **************************************/
public class ShowGoods extends Goods{
    private final int TYPE_NORMAL = 0;//表示使用的是一般的横向的一个商品
    private final int TYPE_ADVERT = 1;//表示使用的横向的一个广告栏展示
    private int showType = 0;//设置商品展示的类型

    public ShowGoods(int showType) {
        super();
        this.showType = showType;
    }

    public int getShowType() {
        return showType;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }

    public ShowGoods(int g_id, String g_name, String g_pic, String g_pic_big, String g_desc, String memb_price, String price, int g_repertory, int g_sales, int g_type, String g_detail, String g_detailUrl) {
        super(g_id, g_name, g_pic, g_pic_big, g_desc, memb_price, price, g_repertory, g_sales, g_type, g_detail, g_detailUrl);
    }

    public ShowGoods() {
    }
    public CartGoods showGoodsToCartGoods(ShowGoods showGoods){
        CartGoods cartGoods = new CartGoods();
        cartGoods.setG_id(showGoods.getG_id());
        cartGoods.setG_name(showGoods.getG_name());
        cartGoods.setG_desc(showGoods.getG_desc());
        cartGoods.setG_pic(showGoods.getG_pic());
        cartGoods.setG_pic_big(showGoods.getG_pic_big());
        cartGoods.setMemb_price(showGoods.getMemb_price());
        cartGoods.setPrice(showGoods.getPrice());
        cartGoods.setG_repertory(showGoods.getG_repertory());
        cartGoods.setG_sales(showGoods.getG_sales());
        cartGoods.setG_type(showGoods.getG_type());
        cartGoods.setG_detail(showGoods.getG_detail());
        cartGoods.setG_detailUrl(showGoods.getG_detailUrl());
        cartGoods.setCount(1);
        return cartGoods;
    }
}
