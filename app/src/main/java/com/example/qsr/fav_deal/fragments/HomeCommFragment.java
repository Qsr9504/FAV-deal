package com.example.qsr.fav_deal.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.activities.TypeActivity;
import com.example.qsr.fav_deal.bean.Goods;
import com.example.qsr.fav_deal.ui.randomLayout.StellarMap;
import com.example.qsr.fav_deal.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**************************************
 * FileName : com.example.qsr.fav_deal.fragments
 * Author : qsr
 * Time : 2016/7/26 16:59
 * Description : 推荐的种类
 **************************************/
public class HomeCommFragment extends Fragment {
    @Bind(R.id.stellar_map)
    StellarMap stellarMap;
    private Random random;
    private Intent intent;
    private Bundle bundle = new Bundle();
    private String[] one = new String[8];

    private String[] two = new String[8];
    private String[] datas = new String[]{"新鲜橙子", "霸气火龙果", "多汁雪梨", "清爽西瓜", "蜜桃", "韭菜",
            "奇异果", "百香果", "北京鲜桃", "海南大芒果", "南非葡萄", "一点红毛桃",
            "云南冰川红提", "大连飓风葡萄", "袖珍小樱桃", "番茄炒蛋"
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = UIUtils.getXmlView(R.layout.fragment_home_comm);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        for (int i = 0; i < 8; i++) {
            one[i] = datas[i];
        }

        for (int j = 0; j < 8; j++) {
            two[j] = datas[j + 8];
        }
        random = new Random();
        //将dp转为像素
        int padding = UIUtils.dpToPx(10);
        stellarMap.setInnerPadding(padding, padding, padding, padding);
        stellarMap.setAdapter(new MyAdapter());
        //每组出现的数据组的搭配规则，每组出现的个数，想加之和需要为数据总长度
        stellarMap.setRegularity(8, 8);
        //设置从哪一组开始做动画操作
        stellarMap.setGroup(0, true);
    }

    private class MyAdapter implements StellarMap.Adapter, View.OnClickListener {
        @Override
        public int getGroupCount() {
            //设置组别个数
            return 2;
        }

        @Override
        public int getCount(int group) {
            //设置每组的个数
            return 8;
        }

        @Override
        public View getView(int group, int position, View convertView) {
            TextView tv = new TextView(getActivity());
            int r = random.nextInt(210);
            int g = random.nextInt(210);
            int b = random.nextInt(210);
            tv.setTextColor(Color.rgb(r, g, b));
            tv.setTextSize(UIUtils.dpToPx(8) + random.nextInt(8));
            tv.setOnClickListener(this);
            if (group == 0) {
                tv.setText(one[position]);
            } else if (group == 1) {
                tv.setText(two[position]);
            }
            return tv;
        }

        @Override
        public int getNextGroupOnPan(int group, float degree) {
            return 0;
        }

        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            return 1;
        }

        @Override
        public void onClick(View v) {
            TextView view = (TextView) v;
            String name = view.getText().toString();//获取点击到的条目内容
            intent = new Intent(getContext(), TypeActivity.class);
            bundle.putString("type",name);
            intent.putExtra("type_bundle",bundle);
            startActivity(intent);
        }
    }
}
