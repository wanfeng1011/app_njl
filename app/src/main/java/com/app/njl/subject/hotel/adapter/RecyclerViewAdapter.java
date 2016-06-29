package com.app.njl.subject.hotel.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.njl.R;
import com.app.njl.subject.hotel.model.entity.Bean;

import java.util.List;

/**
 * Created by jiaxx on 2016/5/5 0005.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> implements View.OnClickListener {

    private List<Bean> beans;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    //define interface
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , String data);
    }

    public RecyclerViewAdapter(List<Bean> beans) {
        super();
        this.beans = beans;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v,(String)v.getTag());
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    /**
     * 内部TextHoler
     *
     * @author edsheng
     *
     */
    public class TitleHoler extends RecyclerView.ViewHolder {
        public TextView textView;

        public TitleHoler(View textview) {
            super(textview);
            this.textView = (TextView) textview.findViewById(R.id.shop_title);
        }
    }

    /**
     * ContextHoler
     *
     * @author edsheng
     *
     */
    public class ContextHoler extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView imageView;

        public ContextHoler(View textview) {
            super(textview);
            this.textView = (TextView) textview.findViewById(R.id.shop_content);
            this.imageView = (ImageView) textview.findViewById(R.id.recycler_item_y_iv);
        }
    }

    /**
     * 按钮的holder
     *
     * @author edsheng
     *
     */
    public class PriceHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public PriceHolder(View textview) {
            super(textview);
            this.textView = (TextView) textview.findViewById(R.id.shop_price);
        }
    }

    @Override
    public int getItemCount() {
        // TODO Auto-generated method stub
        return beans.size();
    }

    /**
     * 获取消息的类型
     */
    @Override
    public int getItemViewType(int position) {
        // TODO Auto-generated method stub
        return beans.get(position).getType();
    }

    /**
     * 创建VIewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewtype) {
        // TODO Auto-generated method stub
        View v = null;
        ViewHolder holer = null;
        switch (viewtype) {
            case Bean.X_TYPE:
                v = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.recylce_item_x, null);
                holer = new TitleHoler(v);
                v.setOnClickListener(this);
                break;
            case Bean.Y_TYPE:
                v = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.recylce_item_y, null);
                holer = new ContextHoler(v);
                v.setOnClickListener(this);
                break;
            case Bean.Z_TYPE:
                v = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.recylce_item_z, null);
                holer = new PriceHolder(v);
                v.setOnClickListener(this);
                break;
        }

        return holer;
    }

    /**
     * 绑定viewholder
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // TODO Auto-generated method stub
        switch (getItemViewType(position)) {
            case Bean.X_TYPE:
                TitleHoler textholer = (TitleHoler) holder;
                textholer.textView.setText(beans.get(position).getText());
                break;
            case Bean.Y_TYPE:
                ContextHoler buttonHolder = (ContextHoler) holder;
                buttonHolder.textView.setText(beans.get(position).getText());
                buttonHolder.imageView.setImageResource(R.mipmap.house_background);
                break;
            case Bean.Z_TYPE:
                PriceHolder imageHoler = (PriceHolder) holder;
                //imageHoler.Imageview.setImageResource(android.R.drawable.checkbox_on_background);
                break;
        }
    }
}
