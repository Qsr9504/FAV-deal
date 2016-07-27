package com.example.qsr.fav_deal.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qsr.fav_deal.MainActivity;
import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.adapter.GuidePagerAdapter;
import com.example.qsr.fav_deal.globle.AppConstants;
import com.example.qsr.fav_deal.utils.MySPUtil;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GuideActivity extends AppCompatActivity {

    @Bind(R.id.welcome_pager)
    ViewPager welcome_pager;
    private List<View> viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
    private List<Integer> picList = new ArrayList<Integer>();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        LayoutInflater lf = getLayoutInflater().from(this);
        View view1 = lf.inflate(R.layout.welcome_1, null);
        View view2 = lf.inflate(R.layout.welcome_2, null);
        TextView tv = (TextView) view2.findViewById(R.id.enter);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySPUtil.save(AppConstants.CONFIG.IS_GUIDE,true);
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
            }
        });
        viewList.add(view1);
        viewList.add(view2);
        GuidePagerAdapter adapter = new GuidePagerAdapter(viewList);
        welcome_pager.setAdapter(adapter);
    }

}
