package com.example.qsr.fav_deal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.bean.Address;
import com.example.qsr.fav_deal.bean.CartGoods;
import com.example.qsr.fav_deal.bean.MessageEvent;
import com.example.qsr.fav_deal.bean.Order;
import com.example.qsr.fav_deal.recycler.OnRecyclerViewListener;
import com.example.qsr.fav_deal.recycler.adapter.CartAdapter;
import com.example.qsr.fav_deal.ui.MyLinearLayoutManager;
import com.example.qsr.fav_deal.utils.LogUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderActivity extends AppCompatActivity {
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.receiverAndPhone)
    TextView receiverAndPhone;
    @Bind(R.id.add_detail)
    TextView addDetail;
    @Bind(R.id.order_recycler)
    RecyclerView orderRecycler;
    @Bind(R.id.changeAddresee)
    TextView changeAddresee;
    @Bind(R.id.price)
    TextView price;
    @Bind(R.id.discount)
    TextView discount;
    @Bind(R.id.afterDiscount)
    TextView afterDiscount;
    @Bind(R.id.creatOrder)
    TextView creatOrder;
    private Intent intent;
    private Order order;
    private List<CartGoods> cartsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_order);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("cart_bundle");
        String cartList = bundle.getString("cartList");
        //从购物车获取数据  json转化为集合
        Gson gson = new Gson();
        cartsList = gson.fromJson(cartList, new TypeToken<List<CartGoods>>() {
        }.getType());
        LogUtil.MyLog_i(this, cartsList.size()+"");
        //声明初始化适配器
        initData();
    }

    private void initData() {
        CartAdapter adapter = new CartAdapter(OrderActivity.this, cartsList);
        adapter.setOnRecyclerViewListener(new OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
            }

            @Override
            public boolean onItemLongClick(View v, int position) {
                return false;
            }

            @Override
            public void onAddBtn(int position) {
                //使list集合中的数量变化
                cartsList.get(position).setCount(cartsList.get(position).getCount() + 1);
                reckonSumMoney();
            }

            @Override
            public void onCutBtn(int position) {
                if (cartsList.get(position).getCount() == 1) {
                    Toast.makeText(OrderActivity.this, "最低选择一件商品，长按可删除该条目", Toast.LENGTH_SHORT).show();
                } else {
                    //使list集合中的数量变化
                    cartsList.get(position).setCount(cartsList.get(position).getCount() - 1);
                    reckonSumMoney();
                }
            }
        });
        MyLinearLayoutManager linearLayoutManager = new MyLinearLayoutManager(OrderActivity.this, LinearLayoutManager.VERTICAL, false);
        orderRecycler.setLayoutManager(linearLayoutManager);
        orderRecycler.setAdapter(adapter);
        reckonSumMoney();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setAddre(MessageEvent event){
        Address address = (Address)event.getObject();
        receiverAndPhone.setText("收货人:" + address.getA_receiver() + " " + "联系方式:"+address.getA_phone());
        addDetail.setText("送至:" + address.getA_detail());
    }
    @OnClick(R.id.back)
    public void back(View v) {
        //销毁当前订单，返回购物车
        finish();
    }

    @OnClick(R.id.changeAddresee)
    public void changeAddresee(View view) {
        //更改当前地址,跳转到地址列表界面
        intent = new Intent(OrderActivity.this, AddressListActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.creatOrder)
    public void creatOrder(View view) {
        //生成订单,添加到订单列表
        // 只能添加服务器存储，不可以创建本地存储
        order = new Order();
    }

    /**
     * 计算当前购物车中的总价
     */
    private void reckonSumMoney() {
        Double sMoney = 0.0;
        Double oriMoney = 0.0;
        afterDiscount.setText("" + 0.00);
        discount.setText("" + 0.00 + " " + "" + 0.00);
        if (cartsList != null) {
            for (CartGoods g : cartsList) {
                sMoney += (Double.parseDouble(g.getMemb_price()) * g.getCount());
            }
            for (CartGoods ori : cartsList) {
                oriMoney += (Double.parseDouble(ori.getPrice()) * ori.getCount());
            }
            afterDiscount.setText("" + meg(sMoney)+"元");
            price.setText("" + meg(oriMoney)+"元");
            discount.setText("" + meg(oriMoney - sMoney)+"元");
        }
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
}
