package com.example.qsr.fav_deal.bean;

import java.util.ArrayList;
import java.util.List;

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

    public CartGoods(int g_id, String g_name, String g_pic, String g_pic_big, String g_desc, String memb_price, String price,
                     int g_repertory, int g_sales, int g_type, String g_detail, String g_detailUrl) {
        super(g_id, g_name, g_pic, g_pic_big, g_desc, memb_price, price, g_repertory, g_sales, g_type, g_detail, g_detailUrl);
    }
    public CartGoods() {
    }
    public ShowGoods CharGoodsToShowGoods(CartGoods cartGoods){
        ShowGoods showGoods = new ShowGoods();
        showGoods.setG_id(cartGoods.getG_id());
        showGoods.setG_name(cartGoods.getG_name());
        showGoods.setG_pic(cartGoods.getG_pic());
        showGoods.setG_pic_big(cartGoods.getG_pic_big());
        showGoods.setG_desc(cartGoods.getG_desc());
        showGoods.setPrice(cartGoods.getPrice());
        showGoods.setMemb_price(cartGoods.getMemb_price());
        showGoods.setG_repertory(cartGoods.getG_repertory());
        showGoods.setG_sales(cartGoods.getG_sales());
        showGoods.setG_type(cartGoods.getG_type());
        showGoods.setG_detail(cartGoods.getG_detail());
        showGoods.setG_detailUrl(cartGoods.getG_detailUrl());
        return showGoods;
    }
    public CartItem CartGoodsToOrder(CartGoods cartGoods){
        CartItem cartItem = new CartItem();
        cartItem.setG_id(cartGoods.getG_id());
        cartItem.setCount(cartGoods.count);
        return cartItem;
    }
    public static List<CartItem> CgListToCiList(List<CartGoods> cartGoodsList){
        List<CartItem> cartItemList = new ArrayList<CartItem>();
        for (CartGoods c:cartGoodsList) {
            cartItemList.add(new CartItem(c.getG_id(),c.count));
        }
        return cartItemList;
    }
}
