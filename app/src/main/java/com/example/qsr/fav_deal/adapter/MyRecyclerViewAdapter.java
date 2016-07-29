package com.example.qsr.fav_deal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.bean.Goods;
import com.example.qsr.fav_deal.globle.App;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;

/**************************************
 * FileName : com.example.qsr.fav_deal.adapter
 * Author : qsr
 * Time : 2016/7/29 0:07
 * Description : recyclerView 适配器
 **************************************/
public abstract class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder>{
    private Context context;
    private LayoutInflater inflater;
    private List<Goods> goodsList;
    public MyRecyclerViewAdapter(Context context,List<Goods> goodsList) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.goodsList = goodsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_goods,viewGroup,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, final int i) {
        final Goods goods = goodsList.get(i);
        Picasso.with(context).load(R.drawable.demo4).placeholder(R.drawable.demo4).resize(150,100).centerCrop()
                .into(viewHolder.goodsPic);
        viewHolder.goodsName.setText(goods.getG_name());
        viewHolder.goodsDesc.setText(goods.getG_desc());
        viewHolder.memPrice.setText("会员价:￥" + goods.getMemb_price() + "/份");
        viewHolder.norPrice.setText("非会员价:￥" + goods.getPrice() + "/份");
        viewHolder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBtnClick(v,goods);
            }
        });
    }

    @Override
    public int getItemCount() {
        return goodsList.size();
    }
    public abstract void addBtnClick(View v,Goods goods);
}
class MyViewHolder extends RecyclerView.ViewHolder{
    @Bind(R.id.goodsPic)
    ImageView goodsPic;
    @Bind(R.id.goodsName)
    TextView goodsName;
    @Bind(R.id.goodsDesc)
    TextView goodsDesc;
    @Bind(R.id.memPrice)
    TextView memPrice;
    @Bind(R.id.norPrice)
    TextView norPrice;
    @Bind(R.id.btn_add)
    ImageView btnAdd;
    public MyViewHolder(View itemView) {
        super(itemView);
        goodsPic = (ImageView) itemView.findViewById(R.id.goodsPic);
        goodsName = (TextView) itemView.findViewById(R.id.goodsName);
        goodsDesc = (TextView) itemView.findViewById(R.id.goodsDesc);
        memPrice = (TextView) itemView.findViewById(R.id.memPrice);
        norPrice = (TextView) itemView.findViewById(R.id.norPrice);
        btnAdd = (ImageView) itemView.findViewById(R.id.btn_add);
    }
}