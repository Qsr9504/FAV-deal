package com.example.qsr.fav_deal.recycler.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.qsr.fav_deal.base.BaseViewHolder;
import com.example.qsr.fav_deal.bean.CartGoods;
import com.example.qsr.fav_deal.bean.MessageEvent;
import com.example.qsr.fav_deal.bean.ShowGoods;
import com.example.qsr.fav_deal.globle.AppConstants;
import com.example.qsr.fav_deal.recycler.OnRecyclerViewListener;
import com.example.qsr.fav_deal.recycler.holders.GoodsNormalHolder;
import com.example.qsr.fav_deal.utils.LogUtil;
import com.example.qsr.fav_deal.utils.MySPUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**************************************
 * FileName : com.example.qsr.fav_deal.recycler.adapter
 * Author : qsr
 * Time : 2016/7/31 0:59
 * Description : 主页水果列表适配器
 * 使其泛型为自定义ViewHolder的父类，方便传入多种类型的ViewHolder
 **************************************/
public class NormalGoodsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int TYPE_NORMAL = 0;//表示使用的是一般的横向的一个商品
    private final int TYPE_ADVERT = 1;//表示使用的横向的一个广告栏展示
    private List<ShowGoods> goodsList;
    private ShowGoods showGoods;
    private Context context;
    private OnRecyclerViewListener onRecyclerViewListener;
    public NormalGoodsAdapter(Context context, List<ShowGoods> goodsList) {
        this.context = context;
        this.goodsList = goodsList;
    }

    /**
     * 让用户有需求的时候再使用该方法
     * @param onRecyclerViewListener
     */
    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }
    @Override
    public int getItemViewType(int position) {
        //重写这个函数，让View的类型返回值按照自定义的取值走
        return goodsList.get(position).getShowType();//获取该条目的数据展示方式
    }

    /**
     * 判断View的类型，采取不同的ViewHolder的展现方式
     * @return 需要使用的ViewHolder处理
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(TYPE_NORMAL == viewType){
            return new GoodsNormalHolder(context,onRecyclerViewListener,parent);
        }else if(TYPE_ADVERT == viewType) {
            //这是一个广告位---未完待续
            return null;
        }else
            return null;
    }

    /**
     * 将绑定数据的事情交回给ViewHolder去做
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BaseViewHolder)holder).bindData(goodsList.get(position));
        LogUtil.MyLog_e(context,"进来了绑定数据地方");
        /***************如果有需要使用holder中的其他方法******************/
        if(holder instanceof GoodsNormalHolder){
            //调用其中的办法: 如让某些控件的不可见
        }else if(holder instanceof GoodsNormalHolder) {
            //这是一个广告位---未完待续
        }else{

        }
    }
    @Override
    public int getItemCount() {
        return goodsList.size();
    }

    /**
     * 添加条目数据到购物车
     */
    public void addToCart(ShowGoods showGoods) {
        if(-1 == (MySPUtil.getInt(AppConstants.CONFIG.USER_ID,-1))){
            //当前没登录，使其登录
            Toast.makeText(context,"你好像没登录，谁tm知道你谁",Toast.LENGTH_SHORT).show();
        }
        else {
            CartGoods cartGoods = showGoods.showGoodsToCartGoods(showGoods);
            MessageEvent event = new MessageEvent();
            event.setObject(cartGoods);
            EventBus.getDefault().post(event);
        }
    }
}
