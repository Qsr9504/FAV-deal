package com.example.qsr.fav_deal.recycler;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**************************************
 * FileName : com.example.qsr.fav_deal.adapter
 * Author : qsr
 * Time : 2016/7/27 15:22
 * Description : 欢迎界面的适配器
 **************************************/
public class GuidePagerAdapter extends PagerAdapter {
    private List<View> viewList;
    public GuidePagerAdapter(List<View> viewList) {
        this.viewList = viewList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
