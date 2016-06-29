package com.app.njl.activity.scenerypicker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.njl.activity.scenerypicker.bean.Destination;
import com.citypicker.model.City;
import com.lostad.applib.R;

import java.util.List;


/**
 * author zaaach on 2016/1/26.
 */
public class ResultListAdapter extends BaseAdapter {
    private Context mContext;
    private List<Destination> mDestinations;

    public ResultListAdapter(Context mContext, List<Destination> destinations) {
        this.mDestinations = destinations;
        this.mContext = mContext;
    }

    public void changeData(List<Destination> list){
        if (mDestinations == null){
            mDestinations = list;
        }else{
            mDestinations.clear();
            mDestinations.addAll(list);
        }
        notifyDataSetChanged();
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
        ResultViewHolder holder;
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_search_result_listview, parent, false);
            holder = new ResultViewHolder();
            holder.name = (TextView) view.findViewById(R.id.tv_item_result_listview_name);
            view.setTag(holder);
        }else{
            holder = (ResultViewHolder) view.getTag();
        }
        holder.name.setText(mDestinations.get(position).getName());
        return view;
    }

    public static class ResultViewHolder{
        TextView name;
    }
}
