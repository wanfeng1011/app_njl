package com.app.njl.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.njl.R;


/**
 * Created by jiaxx on 2016/3/23 0023.
 */
//ViewHolder
public class ViewHolderCommon extends RecyclerView.ViewHolder {

    private ImageView img;
    private CardView card;
    private TextView tv_title;

    public ImageView getImg() {
        return img;
    }

    public TextView getTv_title() {
        return tv_title;
    }

    public CardView getCard() {
        return card;
    }

    public ViewHolderCommon(View contentView) {
        super(contentView);
        img = (ImageView) contentView.findViewById(R.id.img);
        card = (CardView) contentView.findViewById(R.id.card);
        tv_title = (TextView) contentView.findViewById(R.id.tv_title);
    }
}
