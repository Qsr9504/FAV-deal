package com.example.qsr.fav_deal.recycler.holders;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.base.BaseViewHolder;
import com.example.qsr.fav_deal.bean.CartGoods;
import com.example.qsr.fav_deal.recycler.OnRecyclerViewListener;
import com.squareup.picasso.Picasso;

import butterknife.Bind;

/**************************************
 * FileName : com.example.qsr.fav_deal.recycler.holders
 * Author : qsr
 * Time : 2016/7/31 15:23
 * Description :
 **************************************/
public class CartHolder extends BaseViewHolder {
    @Bind(R.id.goodsPic)
    ImageView goodsPic;
    @Bind(R.id.goodsName)
    TextView goodsName;
    @Bind(R.id.price)
    TextView price;
    @Bind(R.id.cutGood)
    TextView cutGood;
    @Bind(R.id.count)
    TextView count;
    @Bind(R.id.addGood)
    TextView addGood;
    @Bind(R.id.goodsRelativeLayout)
    RelativeLayout goodsRelativeLayout;
    private Context context;

    public CartHolder(Context context, OnRecyclerViewListener onRecyclerViewListener, ViewGroup root) {
        super(context, onRecyclerViewListener, root, R.layout.item_cart);
        this.context = context;
    }

    @Override
    public void bindData(final Object o) {
        final CartGoods cartGoods = (CartGoods) o;
        goodsName.setText(cartGoods.getG_name());
        Picasso.with(context).load(cartGoods.getG_pic()).error(R.drawable.demo2).into(goodsPic);
        goodsName.setText(cartGoods.getG_name());
        price.setText("￥" + cartGoods.getPrice() + "/份");
        count.setText(cartGoods.getCount()+"");
        cutGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(count.getText().toString()) == 1) {
                    //不能再继续减少
                    Toast.makeText(context, "最低选择一件商品，长按可删除该条目", Toast.LENGTH_SHORT).show();
                } else {
                    int a = Integer.parseInt(count.getText().toString());
                    a--;
                    count.setText("" + a);
                    onRecyclerViewListener.onCutBtn(getAdapterPosition());
                }
            }
        });
        addGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(count.getText().toString());
                a++;
                count.setText("" + a);
                onRecyclerViewListener.onAddBtn(getAdapterPosition());
            }
        });
        goodsRelativeLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onRecyclerViewListener.onItemLongClick(v,getAdapterPosition());
                return false;
            }
        });
        goodsRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecyclerViewListener.onItemClick(getAdapterPosition());
            }
        });
    }
}
