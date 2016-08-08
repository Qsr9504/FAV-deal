package com.example.qsr.fav_deal.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.bean.ShowGoods;
import com.loopj.android.http.AsyncHttpClient;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GoodsDetailActivity extends Activity {
    @Bind(R.id.goods_pic)
    ImageView goodsPic;
    @Bind(R.id.goods_name)
    TextView goodsName;
    @Bind(R.id.goods_desc)
    TextView goodsDesc;
    @Bind(R.id.mem_price)
    TextView memPrice;
    @Bind(R.id.nor_price)
    TextView norPrice;
    @Bind(R.id.detailPic)
    ImageView detailPic;
    private ShowGoods goods = new ShowGoods();
    private AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("showBundle");
        goods = (ShowGoods) bundle.getSerializable("showGoods");
        initData();
    }

    private void initData() {
        Picasso.with(GoodsDetailActivity.this).load(goods.getG_pic()).into(goodsPic);
        Picasso.with(GoodsDetailActivity.this).load(goods.getG_detailUrl()).into(detailPic);
        goodsName.setText(goods.getG_name());
        goodsDesc.setText(goods.getG_desc());
        memPrice.setText("会员价:" + goods.getMemb_price() + "￥/份");
        norPrice.setText("非会员价:" + goods.getPrice() + "￥/份");
    }

}
