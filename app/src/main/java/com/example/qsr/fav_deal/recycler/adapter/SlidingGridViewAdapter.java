package com.example.qsr.fav_deal.recycler.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.bean.GridViewItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**************************************
 * FileName : com.example.qsr.fav_deal.recycler.adapter
 * Author : qsr
 * Time : 2016/8/1 17:23
 * Description :
 **************************************/
public class SlidingGridViewAdapter extends BaseAdapter {
    private List<GridViewItem> itemList;
    private Context context;

    public SlidingGridViewAdapter(List<GridViewItem> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SlidingGridViewAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_gridview_sliding, null);

            viewHolder = new SlidingGridViewAdapter.ViewHolder(convertView);

            viewHolder.itemImage = (ImageView) convertView.findViewById(R.id.item_image);
            viewHolder.itemName = (TextView) convertView.findViewById(R.id.item_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (SlidingGridViewAdapter.ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(itemList.get(position).getImageUrl()).error(R.mipmap.ic_launcher)
                .into(viewHolder.itemImage);
        viewHolder.itemName.setText(itemList.get(position).getName());
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.item_image)
        ImageView itemImage;
        @Bind(R.id.item_name)
        TextView itemName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
