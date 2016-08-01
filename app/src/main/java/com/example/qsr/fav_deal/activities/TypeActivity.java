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
import com.example.qsr.fav_deal.recycler.OnRecyclerViewListener;
import com.example.qsr.fav_deal.recycler.adapter.NormalGoodsAdapter;
import com.example.qsr.fav_deal.recycler.holders.GoodsNormalHolder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_type);
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
        RequestParams param = new RequestParams();
        param.put("type",p);
        tText.setText(p);//设置标题
//        client = new AsyncHttpClient();
//        client.get("",param,new AsyncHttpResponseHandler(){
//            @Override
//            public void onSuccess(String content) {
//                super.onSuccess(content);
//                //json转化集合并且初始化列表
//            }
//
//            @Override
//            public void onFailure(Throwable error, String content) {
//                super.onFailure(error, content);
//                //数据获取失败
//            }
//        });
        final List<ShowGoods> goodsList = new ArrayList<ShowGoods>();
        //用作测试数据
        String picUrl = "http://file.bmob.cn/M01/E1/9C/oYYBAFePPU6ADBA2AAAdNdM4_BM149.jpg";
        ShowGoods goods1 = new ShowGoods(101, p+1, picUrl, picUrl, "肉质鲜嫩，清脆多汁", "13.99", "15.31", 0, 0, 0, "", "");
        ShowGoods goods2 = new ShowGoods(102, p+2, picUrl, picUrl, "肉质鲜嫩，清脆多汁", "13.99", "15.31", 0, 0, 0, "", "");
        ShowGoods goods3 = new ShowGoods(103, p+3, picUrl, picUrl, "肉质鲜嫩，清脆多汁", "13.99", "15.31", 0, 0, 0, "", "");
        goodsList.add(goods1);
        goodsList.add(goods2);
        goodsList.add(goods3);
        final NormalGoodsAdapter adapter = new NormalGoodsAdapter(TypeActivity.this,goodsList);
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
        typeRecyclerView.setAdapter(adapter);
    }

}
