package com.app.njl.activity.scenerypicker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.njl.activity.scenerypicker.bean.Destination;
import com.lostad.applib.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author zaaach on 2016/1/26.
 */
public class HotCityGridAdapter extends BaseAdapter {
    private Context mContext;
    private List<Destination> mDestinations;

    public HotCityGridAdapter(Context context, List<Destination> destinations) {
        this.mContext = context;
        this.mDestinations = destinations;
    }

    @Override
    public int getCount() {
        return mDestinations == null ? 0 : mDestinations.size();
    }

    @Override
    public Destination getItem(int position) {
        return mDestinations == null ? null : mDestinations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        HotCityViewHolder holder;
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_hot_city_gridview, parent, false);
            holder = new HotCityViewHolder();
            holder.name = (TextView) view.findViewById(R.id.tv_hot_city_name);
            view.setTag(holder);
        }else{
            holder = (HotCityViewHolder) view.getTag();
        }
        holder.name.setText(mDestinations.get(position).getName());
        return view;
    }

    public static class HotCityViewHolder{
        TextView name;
    }
}
