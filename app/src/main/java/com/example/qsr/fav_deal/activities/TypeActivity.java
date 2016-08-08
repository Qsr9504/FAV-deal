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
import com.example.qsr.fav_deal.bean.Cart;
import com.example.qsr.fav_deal.bean.Goods;
import com.example.qsr.fav_deal.bean.ShowGoods;
import com.example.qsr.fav_deal.bmobUtil.GoodsTools;
import com.example.qsr.fav_deal.bmobUtil.MesEventForBmob;
import com.example.qsr.fav_deal.recycler.OnRecyclerViewListener;
import com.example.qsr.fav_deal.recycler.adapter.NormalGoodsAdapter;
import com.example.qsr.fav_deal.recycler.holders.GoodsNormalHolder;
import com.example.qsr.fav_deal.utils.LogUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TypeActivity extends AppCompatActivity {

    @Bind(R.id.t_left)
    ImageView tLeft;
    @Bind(R.id.t_text)
    TextView tText;
    @Bind(R.id.t_right)
    ImageView tRight;
    @Bind(R.id.type_recyclerView)
    RecyclerView typeRecyclerView;
    private AsyncHttpClient client;
    private Intent intent;
    private  Bundle bundle;
    private GoodsTools goodsTools;
    private NormalGoodsAdapter adapter;
    private List<ShowGoods> goodsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_type);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        intent = getIntent();
        bundle = intent.getBundleExtra("type_bundle");
        String param = bundle.getString("type");
        tRight.setVisibility(View.INVISIBLE);
        //返回主界面
        tLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initData(param);
    }

    private void initData(String p) {
        goodsTools = GoodsTools.getInstance(this);
        RequestParams param = new RequestParams();
        param.put("type",p);
        if(p.equals("橙子"))
            goodsTools.getAllOran();//获取全部的橙子
        tText.setText(p);//设置标题
        goodsList = new ArrayList<ShowGoods>();
        //用作测试数据
        adapter = new NormalGoodsAdapter(TypeActivity.this,goodsList);
        adapter.setOnRecyclerViewListener(new OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(TypeActivity.this, goodsList.get(position).toString(), Toast.LENGTH_SHORT).show();
                bundle.putSerializable("showGoods", goodsList.get(position));
                Intent intent = new Intent(TypeActivity.this, GoodsDetailActivity.class);
                intent.putExtra("showBundle", bundle);
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View v, int position) {
                Toast.makeText(TypeActivity.this, "点击了长按按钮" + position, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onAddBtn(int position) {
                adapter.addToCart(goodsList.get(position));
                Toast.makeText(TypeActivity.this, "点击了添加按钮" + position, Toast.LENGTH_SHORT).show();
                //添加 按钮的飞入购物车动画效果
            }

            @Override
            public void onCutBtn(int position) {
                //此处不需要处理
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TypeActivity.this, LinearLayoutManager.VERTICAL, false);
        typeRecyclerView.setLayoutManager(linearLayoutManager);

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void fruitList(MesEventForBmob eventForBmob){
        int code = eventForBmob.getStateCode();
        if(code == GoodsTools.ALL_ORAN_SUCC){
            LogUtil.MyLog_e(this,"已经准备转换");
            List<Goods> list = (List<Goods>) eventForBmob.getObject();
            LogUtil.MyLog_e(this,list.toString());
            goodsList.addAll(Goods.GoodsToShowGoods(list));
            LogUtil.MyLog_e(this,goodsList.toString());
            typeRecyclerView.setAdapter(adapter);
        }else if (code == GoodsTools.ALL_ORAN_ERROR){
            Toast.makeText(this,"获取橙子列表失败",Toast.LENGTH_SHORT).show();
        }
    }
}
