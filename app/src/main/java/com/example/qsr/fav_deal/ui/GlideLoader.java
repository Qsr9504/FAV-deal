package com.example.qsr.fav_deal.ui;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**************************************
 * FileName : com.example.qsr.fav_deal.ui
 * Author : qsr
 * Time : 2016/8/3 10:40
 * Description :
 **************************************/
public class GlideLoader implements com.yancy.imageselector.ImageLoader {
    @Override
    public void displayImage(Context context, String path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .placeholder(com.yancy.imageselector.R.mipmap.imageselector_photo)
                .centerCrop()
                .into(imageView);
    }
}
