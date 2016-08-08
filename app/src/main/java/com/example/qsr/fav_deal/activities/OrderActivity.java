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
import com.example.qsr.fav_deal.bean.User;
import com.example.qsr.fav_deal.bmobUtil.MesEventForBmob;
import com.example.qsr.fav_deal.bmobUtil.OrderTools;
import com.example.qsr.fav_deal.bmobUtil.UserTools;
import com.example.qsr.fav_deal.globle.AppConstants;
import com.example.qsr.fav_deal.recycler.OnRecyclerViewListener;
import com.example.qsr.fav_deal.recycler.adapter.CartAdapter;
import com.example.qsr.fav_deal.ui.MyLinearLayoutManager;
import com.example.qsr.fav_deal.utils.LogUtil;
import com.example.qsr.fav_deal.utils.MySPUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;

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
    private Address address;
    private OrderTools orderTools = null;
    private UserTools userTools = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_order);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        orderTools = OrderTools.getInstance(this);
        userTools = UserTools.getInstance(this);
        Bundle bundle = intent.getBundleExtra("cart_bundle");
        String cartList = bundle.getString("cartList");
        //从购物车获取数据  json转化为集合
        Gson gson = new Gson();
        cartsList = gson.fromJson(cartList, new TypeToken<List<CartGoods>>() {
        }.getType());
        LogUtil.MyLog_i(this, cartsList.size() + "");
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
                    Toast.makeText(OrderActivity.this, "最低选择一件商品", Toast.LENGTH_SHORT).show();
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
    public void setAddre(MesEventForBmob event) {
        int code = event.getStateCode();
        if(code == AddressListActivity.RETURN_ADDRESS){
            Address address = (Address) event.getObject();
            receiverAndPhone.setText("收货人:" + address.getA_receiver() + " " + "联系方式:" + address.getA_phone());
            addDetail.setText("送至:" + address.getA_detail());
        }else if(code == OrderTools.CREATE_ORDER_SUCC){
            Toast.makeText(this,"下单成功",Toast.LENGTH_SHORT).show();
            //更新用户的当前金额，增加积分
            userTools.updateUserMoney(order.getO_money());
            finish();
        }else if(code == OrderTools.CREATE_ORDER_FAIL){
            Toast.makeText(this,"下单失败",Toast.LENGTH_SHORT).show();
        }
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
        order = new Order();
        //-------------------------
        // 只能添加服务器存储，不可以创建本地存储
        //-------------------------
        //假设创建成功返回。  有一个订单状态和订单id
        order.setU_id(MySPUtil.getString(AppConstants.CONFIG.USER_ID));
        order.setA_id("");
        order.setList(CartGoods.CgListToCiList(cartsList));
        order.setO_money((int) Double.parseDouble(afterDiscount.getText().toString().substring(0,afterDiscount.getText().length()-1)));
        order.setO_state("0");//订单状态  --- 默认为已付款
        order.setTime(getCurrentTime());
        if ("=" == (MySPUtil.getString(AppConstants.CONFIG.USER_ID, "="))) {
            //如果没有登录
            Toast.makeText(OrderActivity.this, "您没登录", Toast.LENGTH_SHORT).show();
        }else if(BmobUser.getCurrentUser(this,User.class).getU_money() < order.getO_money()) {
            //如果钱不够
            Toast.makeText(OrderActivity.this, "您账户余额不足", Toast.LENGTH_SHORT).show();
        }else {
            orderTools.addOrder(order.toBmobOrder(order));
        }

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
            afterDiscount.setText("" + meg(sMoney) + "元");
            price.setText("" + meg(oriMoney) + "元");
            discount.setText("" + meg(oriMoney - sMoney) + "元");
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

    public String getCurrentTime() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd    hh:mm:ss");
        String date = sDateFormat.format(new java.util.Date());
        return date;
    }
}
