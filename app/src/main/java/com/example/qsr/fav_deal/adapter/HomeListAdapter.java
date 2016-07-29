package com.example.qsr.fav_deal.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.bean.Goods;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**************************************
 * FileName : com.example.qsr.fav_deal.adapter
 * Author : qsr
 * Time : 2016/7/28 9:26
 * Description :
 **************************************/
public abstract class HomeListAdapter extends BaseAdapter {
    private List<Goods> goodsList;
    private Context context;
    public HomeListAdapter(Context context,List<Goods> goodsList) {
        this.goodsList = goodsList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return goodsList.size();
    }

    @Override
    public Object getItem(int position) {
        return goodsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Goods goods = goodsList.get(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_goods, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //设置数据

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
        return convertView;
    }

    static class ViewHolder {
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
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
    public abstract void addBtnClick(View v,Goods goods);
}
