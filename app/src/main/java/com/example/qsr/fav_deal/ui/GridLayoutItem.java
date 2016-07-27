package com.example.qsr.fav_deal.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.example.qsr.fav_deal.R;

/**************************************
 * FileName : com.example.qsr.fav_deal.ui
 * Author : qsr
 * Time : 2016/7/27 22:24
 * Description :
 **************************************/
public class GridLayoutItem extends LinearLayout {

    public GridLayoutItem(Context context) {
        super(context);
        initView(context);
    }

    public GridLayoutItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public GridLayoutItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.item_gridview,this);
    }
}
