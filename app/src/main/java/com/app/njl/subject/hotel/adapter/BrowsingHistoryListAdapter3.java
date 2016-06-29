package com.app.njl.subject.hotel.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.njl.R;
import com.app.njl.subject.hotel.model.entity.homescenicspot.ScenicSpot;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiaxx on 2016/3/23 0023.
 */
public class BrowsingHistoryListAdapter3 extends RecyclerView.Adapter<BrowsingHistoryListAdapter3.CustomViewHolder> {

    private int page;
    private List<String> mImageUrls;
    private int lastPosition = -1;
    private Context mContext;
    private String titles[] = {"住宿", "餐饮", "游玩", "特产"};
    private Integer imgs[] = {R.mipmap.tp_19_1, R.mipmap.tp_19_2, R.mipmap.tp_19_3, R.mipmap.tp_19_4};
    private List<Integer[]> list;
    private List<ScenicSpot> mScenicSpotList;

    public BrowsingHistoryListAdapter3(Context context, List<ScenicSpot> list) {
//            mImageUrls = ImageUrl.imageList();
        mContext = context;
        if(mScenicSpotList == null)
            mScenicSpotList = new ArrayList<>();
        mScenicSpotList.addAll(list);
    }

    @Override
    public void onViewDetachedFromWindow(CustomViewHolder holder) {
        super.onViewDetachedFromWindow(holder);

//			holder.card.clearAnimation();

    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = R.layout.viewpager_layout;

        View v = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new CustomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {
        KLog.i("onBindViewHolder：" + holder + " " + position);
        holder.nameTv.setText(mScenicSpotList.get(position).getResortname());
        if(mScenicSpotList.get(position).getEntitys() == null)
            return;
        if(holder.mainLinnerLayout.getChildCount()>=mScenicSpotList.get(position).getEntitys().size())
            return;
        for (int i = 0; i <mScenicSpotList.get(position).getEntitys().size(); i++) {
            View additionView = LayoutInflater.from(mContext).inflate(R.layout.inner_layout_file, null, false);
            RelativeLayout innerLinnerLayout = (RelativeLayout) additionView.findViewById(R.id.inner_layout);
            ImageView img = (ImageView) additionView.findViewById(R.id.icon);
            TextView nameTv = (TextView) additionView.findViewById(R.id.name);
            TextView titleTv = (TextView) additionView.findViewById(R.id.text);
//            img.setImageResource(list.get(position)[i]);
//                img.setBackgroundResource(list.get(position)[i]);
            nameTv.setText("人气值:" + mScenicSpotList.get(position).getEntitys().get(i).getGoodNum());
            String productTypeStr = "";
            if(mScenicSpotList.get(position).getEntitys().get(i).getPruductType() == 1) {
                productTypeStr = "住宿";
            }else if(mScenicSpotList.get(position).getEntitys().get(i).getPruductType() == 2) {
                productTypeStr = "餐饮";
            }else if(mScenicSpotList.get(position).getEntitys().get(i).getPruductType() == 3) {
                productTypeStr = "游玩";
            }else if(mScenicSpotList.get(position).getEntitys().get(i).getPruductType() == 4) {
                productTypeStr = "特产";
            }
            titleTv.setText(productTypeStr);

            Glide.with(mContext)
                    .load(mScenicSpotList.get(position).getEntitys().get(i).getPicUrl())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .placeholder(R.mipmap.hotel)
                    .crossFade()
                    .into(img);

            // If the width varies for each innerLinnerLayout, then remove the if block & always calculate padding value
            // padding is an integer initialized to -1 in the constructor
        /*int padding = -1;
        if (padding == -1) {
            int width = mContext.getResources().getDisplayMetrics().widthPixels;
            innerLinnerLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            padding = width - additionView.getMeasuredWidth();
        }*/
            // I've set padding to right only, but you could center it by giving left and right padding of value=(padding/2)
            //innerLinnerLayout.setPadding(0, 0, padding, 0);
            holder.mainLinnerLayout.addView(additionView);
        }
    }

    @Override
    public int getItemCount() {
        return mScenicSpotList == null ? 0 : mScenicSpotList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        LinearLayout mainLinnerLayout;
        TextView nameTv;

        public CustomViewHolder(View contentView) {
            super(contentView);
            mainLinnerLayout=(LinearLayout)contentView.findViewById(R.id.mainLinear);
            nameTv = (TextView)contentView.findViewById(R.id.name);
        }
    }
}