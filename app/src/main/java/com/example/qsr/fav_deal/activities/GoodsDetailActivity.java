package com.example.qsr.fav_deal.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.bean.Goods;
import com.example.qsr.fav_deal.bean.MessageEvent;
import com.example.qsr.fav_deal.bean.ShowGoods;
import com.example.qsr.fav_deal.utils.LogUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GoodsDetailActivity extends Activity {
    @Bind(R.id.goods_pic)
    ImageView goodsPic;
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
        goodsName = (TextView) findViewById(R.id.goods_name);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent  messageEvent) {
        String str = messageEvent.getString();
        goodsName.setText(str);
        LogUtil.MyLog_e(this,"---" + str);
    }

    private void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
