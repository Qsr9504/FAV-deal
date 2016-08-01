package com.example.qsr.fav_deal.fragments;

import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.activities.CartDetailActivity;
import com.example.qsr.fav_deal.base.BaseFragment;
import com.example.qsr.fav_deal.bean.CartGoods;
import com.example.qsr.fav_deal.bean.MessageEvent;
import com.example.qsr.fav_deal.globle.AppConstants;
import com.example.qsr.fav_deal.recycler.OnRecyclerViewListener;
import com.example.qsr.fav_deal.recycler.adapter.CartAdapter;
import com.example.qsr.fav_deal.ui.IconFontTextView;
import com.example.qsr.fav_deal.utils.LogUtil;
import com.example.qsr.fav_deal.utils.MySPUtil;
import com.loopj.android.http.RequestParams;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.security.PolicySpi;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**************************************
 * FileName : com.example.qsr.fav_deal.fragments
 * Author : qsr
 * Time : 2016/7/26 13:27
 * Description : 购物车的界面
 **************************************/
public class CartFragment extends BaseFragment {
    @Bind(R.id.edit)
    IconFontTextView edit;
    @Bind(R.id.back)
    IconFontTextView back;
    @Bind(R.id.cart_recyclerView)
    RecyclerView cartRecyclerView;
    @Bind(R.id.sumMoney)
    TextView sumMoney;
    @Bind(R.id.discount)
    TextView discount;
    @Bind(R.id.check)
    TextView check;
    @Bind(R.id.norBar)
    LinearLayout norBar;
    @Bind(R.id.clearAllCart)
    TextView clearAllCart;
    @Bind(R.id.deleteAll)
    LinearLayout deleteAll;
    private List<CartGoods> goodsList = new ArrayList<CartGoods>();
    private PopupWindow pw;
    private View view;
    private CartAdapter adapter;

    @Override
    protected void initEvent() {
        //从本地数据库中获取并且初始化购物车列表
        goodsList = MySPUtil.getCartGoodsJson(AppConstants.CONFIG.CART);
        if(MySPUtil.getCartGoodsJson(AppConstants.CONFIG.CART).size() ==0) {
            String picUrl = "http://file.bmob.cn/M01/E1/9C/oYYBAFePPU6ADBA2AAAdNdM4_BM149.jpg";
            CartGoods goods1 = new CartGoods(1, "小樱桃", picUrl, picUrl, "肉质鲜嫩，清脆多汁", "13.29", "13.99", 0, 0, 0, "", "");
            CartGoods goods2 = new CartGoods(2, "火龙果2", picUrl, picUrl, "肉质鲜嫩，清脆多汁", "13.29", "13.99", 0, 0, 0, "", "");
            CartGoods goods3 = new CartGoods(3, "火龙果3", picUrl, picUrl, "肉质鲜嫩，清脆多汁", "13.29", "13.99", 0, 0, 0, "", "");
            goodsList.add(goods1);
            goodsList.add(goods2);
            goodsList.add(goods3);
        }
    }

    @Override
    protected RequestParams getParams() {
        return new RequestParams();
    }

    @Override
    protected String getUrl() {
        return "";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cart;
    }

    @Override
    protected void initData(String content, View successView) {
        EventBus.getDefault().register(this);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeUI();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backUI();
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goCheck();
            }
        });
        clearAllCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清空购物车中数据
                adapter.removeAll();
            }
        });
        initD();//用于没有网络的时候死数据

    }

    /**
     * 生成订单
     */
    private void goCheck() {
        LogUtil.MyLog_i(getContext(), "--*******************--\n" + goodsList.toString() + "\n--*******************--");

    }

    private void backUI() {
        edit.setVisibility(View.VISIBLE);
        back.setVisibility(View.GONE);
        norBar.setVisibility(View.VISIBLE);
        deleteAll.setVisibility(View.GONE);
    }

    private void changeUI() {
        edit.setVisibility(View.GONE);
        back.setVisibility(View.VISIBLE);
        norBar.setVisibility(View.GONE);
        deleteAll.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initTitle() {

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void addCart(MessageEvent event){
        CartGoods cartGoods = (CartGoods) event.getObject();
        int flag = isExit(cartGoods.getG_id());
        //判断购物车是否已经存在
        if(flag == -1){//购物车中不存在
            goodsList.add(cartGoods);
        }else {//购物车中存在
            goodsList.get(flag).setCount(goodsList.get(flag).getCount()+1);
        }
        adapter.notifyDataSetChanged();
        Toast.makeText(getContext(), "添加购物车成功", Toast.LENGTH_SHORT).show();
        reckonSumMoney();
    }

    /**
     * 判断购物车中是否已经存在该物品
     * @param id
     * @return
     */
    private int isExit(int id) {
        for(int i = 0; i<goodsList.size();i++){
            if(goodsList.get(i).getG_id() == id)
                return i;//说明购物车中已经存在
        }
        return -1;
    }

    private void initD() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        cartRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CartAdapter(getContext(), goodsList);
        adapter.setOnRecyclerViewListener(new OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                bundle.putSerializable("cartGoods", goodsList.get(position));
                Intent intent = new Intent(getContext(), CartDetailActivity.class);
                intent.putExtra("cartBundle", bundle);
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, int position) {
                initPop(view, position);
                return false;
            }

            @Override
            public void onAddBtn(int position) {
                //使list集合中的数量变化
                goodsList.get(position).setCount(goodsList.get(position).getCount() + 1);
                reckonSumMoney();
            }

            @Override
            public void onCutBtn(int position) {
                if (goodsList.get(position).getCount() == 1) {
                    Toast.makeText(getContext(), "最低选择一件商品，长按可删除该条目", Toast.LENGTH_SHORT).show();
                } else {
                    //使list集合中的数量变化
                    goodsList.get(position).setCount(goodsList.get(position).getCount() - 1);
                    reckonSumMoney();
                }
            }
        });
        cartRecyclerView.setAdapter(adapter);
        reckonSumMoney();
    }

    /**
     * 计算当前购物车中的总价
     */
    private void reckonSumMoney() {
        Double sMoney = 0.0;
        Double oriMoney = 0.0;
        sumMoney.setText("实付:￥" + 0.00);
        discount.setText("原价总计:￥" + 0.00 + " " + "优惠 :￥" + 0.00);
        if(goodsList != null){
            for (CartGoods g : goodsList) {
                sMoney += (Double.parseDouble(g.getMemb_price()) * g.getCount());
            }
            for (CartGoods ori : goodsList) {
                oriMoney += (Double.parseDouble(ori.getPrice()) * ori.getCount());
            }
            sumMoney.setText("实付:￥" + meg(sMoney));
            discount.setText("原价总计:￥" + meg(oriMoney) + " " + "优惠 :￥" + meg(oriMoney - sMoney));
        }
    }

    private void initPop(View v, final int position) {

//		View pv= (View)MainActivity.this.getLayoutInflater().inflate(R.layout.pv, null);
        RelativeLayout pv = (RelativeLayout) LayoutInflater.from(getContext()).inflate(
                R.layout.pv, null);

        pw = new PopupWindow(getContext());
        pw.setBackgroundDrawable(new BitmapDrawable());
        pw.setContentView(pv);

        TextView tvDel = (TextView) pv.findViewById(R.id.pv_tv_del);

        pw.setWidth(getActivity().getWindowManager().getDefaultDisplay().getWidth() / 5);
        pw.setHeight(120);

        pw.setOutsideTouchable(true);
        pw.setFocusable(true);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        pw.showAtLocation(v,
                Gravity.LEFT | Gravity.TOP,
                v.getWidth() / 3,
                getStateBar() + v.getHeight() * (position + 1) - (v.getHeight()/3));

        tvDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.removeItem(position);
                reckonSumMoney();
                pw.dismiss();
                pw = null;
            }
        });
    }

    private int getStateBar() {
        Rect frame = new Rect();
        getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        System.out.println("---------------------->" + statusBarHeight);
        return statusBarHeight;
    }

    public static double meg(double i) {
        int b = (int) Math.round(i * 10); //小数点后两位前移，并四舍五入
        double c = ((double) b / 10.0); //还原小数点后两位
        if ((c * 10) % 5 != 0) {
            int d = (int) Math.round(c); //小数点前移，并四舍五入
            c = ((double) d); //还原小数点
        }
        return c;
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.MyLog_e(getContext(),"onPause方法调用");
        //保存当前购物车信息到Sp中
        MySPUtil.saveCartGoods(AppConstants.CONFIG.CART, goodsList);
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.MyLog_e(getContext(),"onStop方法调用");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        LogUtil.MyLog_e(getContext(),"onDestory方法调用");
    }
}
