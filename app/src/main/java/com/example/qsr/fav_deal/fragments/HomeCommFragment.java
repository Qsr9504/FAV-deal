package com.example.qsr.fav_deal.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.utils.UIUtils;

import butterknife.ButterKnife;

/**************************************
 * FileName : com.example.qsr.fav_deal.fragments
 * Author : qsr
 * Time : 2016/7/26 16:59
 * Description : 推荐的种类
 **************************************/
public class HomeCommFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = UIUtils.getXmlView(R.layout.fragment_home_comm);
        ButterKnife.bind(this,view);
        initData();
        return view;
    }

    private void initData() {

    }
}
