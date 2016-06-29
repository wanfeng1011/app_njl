package com.app.njl.subject.hotel.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.app.njl.R;
import com.app.njl.base.BaseActivity;
import com.app.njl.utils.SharedPreferences;

import butterknife.Bind;

/**
 * 导游介绍
 * Created by jiaxx on 2016/4/16 0016.
 */
public class ShopPlayShowTourGuideActivity extends BaseActivity {
    @Bind(R.id.tourGuide_playTimeTv)
    TextView playTimeTv; //游玩时间
    @Bind(R.id.shopPlayTourGuideDetail_backTv)
    TextView backTv; //返回
    @Bind(R.id.shopPlayTourGuideDetail_addShoppingCartTv)
    TextView addShoppingCartTv; //加入购物车
    @Bind(R.id.shopPlayTourGuideDetail_buyNowTv)
    TextView buyNowTv; //立即购买
    @Override
    protected void initContentView() {
        setContentView(R.layout.shop_play_tourguide_detail_layout);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setStayLiveTime();
        backTv.setOnClickListener(this);
        addShoppingCartTv.setOnClickListener(this);
        buyNowTv.setOnClickListener(this);
    }

    @Override
    protected void initControl() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shopPlayTourGuideDetail_backTv:
                finish();
                break;
        }
    }

    public void setStayLiveTime() {
//        String stay_in = SharedPreferences.getInstance().getString("live_in", "住店:");
        String live_out = SharedPreferences.getInstance().getString("live_out", "离店:");
        playTimeTv.setText("游玩时间：" + live_out);
    }
}
