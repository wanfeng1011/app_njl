package com.app.njl.subject.hotel.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.njl.R;
import com.app.njl.subject.hotel.model.entity.Fruit;
import com.app.njl.utils.Options;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiaxx on 2016/4/12 0012.
 */
public class MainFragmentShopListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Fruit> dataList = new ArrayList<>();
    public MainFragmentShopListAdapter(Context context, List<Fruit> fruits) {
        this.mContext = context;
        this.dataList.addAll(fruits);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View v = LayoutInflater.from(mContext).
                    inflate(R.layout.library_recycler_title_item, parent, false);
            TitleHolder holder = new TitleHolder(v);
            return holder;
        } else {
            View v = LayoutInflater.from(mContext).
                    inflate(R.layout.item_beauty, parent, false);
            MyViewHolder holder = new MyViewHolder(v);
            return holder;
        }
    }

    public void setData(List<Fruit> fruits) {
        dataList.addAll(fruits);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof TitleHolder) {
            ((TitleHolder)holder).tv_item.setText(dataList.get(position).getName());
        }else if(holder instanceof MyViewHolder) {
            Log.i("img", "img:---" + dataList.get(position).getUrl());
            if (dataList.get(position).getUrl().equals(((MyViewHolder) holder).img.getTag())) {

            } else {
                ((MyViewHolder)holder).tv_title.setText(dataList.get(position).getContent());
                ((MyViewHolder)holder).tv_info.setText(dataList.get(position).getPrice());
                ((MyViewHolder)holder).tv_views.setText(dataList.get(position).getStar() + "星");
                // 如果不相同，就加载。现在在这里来改变闪烁的情况
                ImageLoader.getInstance().displayImage(dataList.get(position).getUrl(), ((MyViewHolder) holder).img,
                        Options.getListOptions());
                ((MyViewHolder) holder).img.setTag(dataList.get(position).getUrl());
            }
            //ImageLoader.getInstance().displayImage(dataList.get(position).getUrl(), ((MyViewHolder) holder).img, Options.getListOptions());
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(dataList.get(position).getType().equals("title")) {
            return 1;
        }else {
            return 2;
        }
    }

    /**
     * 自定义ViewHolder
     */
    private class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tv_info;
        public ImageView img;
        public TextView tv_title;
        public TextView tv_views;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_info = (TextView) itemView.findViewById(R.id.beauty_tv_info);
            img = (ImageView) itemView.findViewById(R.id.beauty_img);
            tv_title = (TextView) itemView.findViewById(R.id.beauty_tv_title);
            tv_views = (TextView) itemView.findViewById(R.id.beauty_tv_views);
        }

    }

    /**
     * 自定义ViewHolder
     */
    private class TitleHolder extends RecyclerView.ViewHolder{

        public TextView tv_item;

        public TitleHolder(View itemView) {
            super(itemView);
            tv_item = (TextView) itemView.findViewById(R.id.tv_item);
        }

    }
}
