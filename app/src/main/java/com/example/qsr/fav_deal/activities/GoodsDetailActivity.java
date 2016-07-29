package com.example.qsr.fav_deal.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.bean.Goods;
import com.example.qsr.fav_deal.utils.LogUtil;
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
    @Bind(R.id.goods_detailsList)
    RecyclerView goodsDetailsList;
    private Goods goods = new Goods();
    private AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.bind(this);
        Bundle bundle = this.getIntent().getBundleExtra("fruit_detail");
        goods = (Goods) bundle.getSerializable("good");
        LogUtil.MyLog_e(this, goods.getG_desc());
        Picasso.with(this).load(R.drawable.demo4).into(goodsPic);
        goodsDesc.setText(goods.getG_desc());
        goodsName.setText(goods.getG_name());
        memPrice.setText("会员价:" + goods.getMemb_price() + "/份");
        norPrice.setText("非会员价" + goods.getPrice() + "/份");
    }
}
