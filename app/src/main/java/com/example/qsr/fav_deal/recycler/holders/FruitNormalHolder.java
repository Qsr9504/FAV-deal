package com.example.qsr.fav_deal.recycler.holders;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.bean.ShowGoods;
import com.example.qsr.fav_deal.recycler.OnRecyclerViewListener;
import com.example.qsr.fav_deal.base.BaseViewHolder;
import com.example.qsr.fav_deal.bean.Goods;
import com.example.qsr.fav_deal.utils.LogUtil;
import com.squareup.picasso.Picasso;

import butterknife.Bind;

/**************************************
 * FileName : com.example.qsr.fav_deal.adapter.holders
 * Author : qsr
 * Time : 2016/7/30 14:52
 * Description :
 **************************************/
public class FruitNormalHolder extends BaseViewHolder<Goods> {
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
    @Bind(R.id.goodsRelativeLayout)
    RelativeLayout goodsRelativeLayout;
    @Bind(R.id.divider_line)
    View dividerLine;

    private ShowGoods goods;

    /**
     * 构造参数，供adapter调用时初始化
     *
     * @param context                上下文对象
     * @param onRecyclerViewListener 点击事件监听接口
     * @param root
     */
    public FruitNormalHolder(Context context, OnRecyclerViewListener onRecyclerViewListener, ViewGroup root) {
        super(context, onRecyclerViewListener, root, R.layout.item_goods);
    }

    @Override
    public void bindData(Goods goods) {
        LogUtil.MyLog_e(context,"绑定数据");
        Picasso.with(context).load(goods.getG_pic()).into(goodsPic);//绑定列表中的缩略图
        goodsName.setText(goods.getG_name());//绑定物品名称
        goodsDesc.setText(goods.getG_desc());//绑定物品的描述
        memPrice.setText("会员价:" + goods.getMemb_price() + "￥/份");
        norPrice.setText("非会员价:" + goods.getPrice() + "￥/份");
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.MyLog_e(context,"进来了添加按钮的点击事件");
                if(onRecyclerViewListener!=null) {
                    onRecyclerViewListener.onAddBtn(getAdapterPosition());
                }
            }
        });
        goodsRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.MyLog_e(context,"进来了整个条目的点击事件");
                if(onRecyclerViewListener!=null){
                    onRecyclerViewListener.onItemClick(getAdapterPosition());
                }
            }
        });
        goodsRelativeLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onRecyclerViewListener != null) {
                    onRecyclerViewListener.onItemLongClick(v,getAdapterPosition());
                }
                return true;
            }
        });
    }
}
