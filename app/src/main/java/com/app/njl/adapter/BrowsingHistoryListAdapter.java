package com.app.njl.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.njl.R;
import com.app.njl.utils.Options;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiaxx on 2016/3/23 0023.
 */
public class BrowsingHistoryListAdapter extends RecyclerView.Adapter<ViewHolderCommon> {

    private int page;
    private List<String> mImageUrls;
    private int lastPosition = -1;

    public BrowsingHistoryListAdapter() {
//            mImageUrls = ImageUrl.imageList();
        mImageUrls = new ArrayList<String>();
        List<String> lists = new ArrayList<String>();
        int num = 14;
        String url = "http://192.168.1.116:8080/HttpServer/image";
        for (int i = 0; i < num; i++) {
            lists.add(url + "?name=19/tp_19_" + i);
            Log.i("img", "img:" + url + "?name=19/tp_" + i);
        }
        mImageUrls.addAll(lists);
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolderCommon holder) {
        super.onViewDetachedFromWindow(holder);

//			holder.card.clearAnimation();

    }

    @Override
    public ViewHolderCommon onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = R.layout.item_record;

        View v = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new ViewHolderCommon(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolderCommon holder, final int position) {

        final String url = mImageUrls.get(position);
        holder.getTv_title().setText("店家" + position);
        ImageLoader.getInstance().displayImage(url, holder.getImg(), Options.getListOptions());
    }

    @Override
    public int getItemCount() {
        return mImageUrls.size();
    }

}