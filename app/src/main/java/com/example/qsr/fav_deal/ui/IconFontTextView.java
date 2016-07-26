package com.example.qsr.fav_deal.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.qsr.fav_deal.globle.App;

/**************************************
 * FileName : com.example.qsr.fav_deal.ui
 * Author : qsr
 * Time : 2016/7/26 10:07
 * Description : iconfont的自定义TextView
 **************************************/
public class IconFontTextView extends TextView{
    private Typeface iconfont;
    public IconFontTextView(Context context) {
        super(context);
        initView();
    }

    public IconFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public IconFontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        iconfont = Typeface.createFromAsset(App.mContext.getAssets(),"iconfont/iconfont.ttf");
        this.setTypeface(iconfont);
    }
}
