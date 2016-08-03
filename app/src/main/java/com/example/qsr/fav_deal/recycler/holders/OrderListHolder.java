package com.example.qsr.fav_deal.recycler.holders;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.base.BaseViewHolder;
import com.example.qsr.fav_deal.bean.Order;
import com.example.qsr.fav_deal.recycler.OnEditOrDeleteListener;
import com.example.qsr.fav_deal.recycler.OnRecyclerViewListener;

import butterknife.Bind;

/**************************************
 * FileName : com.example.qsr.fav_deal.recycler.holders
 * Author : qsr
 * Time : 2016/8/3 9:51
 * Description : 订单的holder
 **************************************/
public class OrderListHolder extends BaseViewHolder {
    @Bind(R.id.order_code)
    TextView orderCode;
    @Bind(R.id.order_time)
    TextView orderTime;
    @Bind(R.id.order_state)
    TextView orderState;
    @Bind(R.id.order_sum)
    TextView orderSum;
    @Bind(R.id.order_card)
    CardView orderCard;
    private Context context;
    private OnEditOrDeleteListener onEditOrDeleteListener;
    public OrderListHolder(Context context, OnEditOrDeleteListener onEditOrDeleteListener, ViewGroup root) {
        super(context, null, root, R.layout.item_orderlist);
        this.context = context;
        this.onEditOrDeleteListener = onEditOrDeleteListener;
    }

    @Override
    public void bindData(Object o) {
        Order order = (Order) o;
        orderCode.setText("订单编号为:" + order.getO_id());
        orderTime.setText("下单时间为:" + order.getTime());
        orderState.setText("订单状态为:" + order.getO_state());
        orderSum.setText(order.getO_money() + "元");
        orderCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditOrDeleteListener.onItemClick(getAdapterPosition());
            }
        });
    }
}
