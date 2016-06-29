package com.app.njl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.app.njl.R;

/**
 * 购物车界面 住宿列表的适配器
 * Created by Administrator on 2016/4/8.
 */
public class AccommodationListAdapter extends BaseAdapter {
    private Context context;

    public AccommodationListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.accommodation_item_layout, null);

        return view;
    }
}