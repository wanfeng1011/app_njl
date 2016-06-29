package com.app.njl.subject.hotel.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.app.njl.R;
import com.app.njl.base.BaseActivity;
import com.app.njl.utils.SharedPreferences;

import org.w3c.dom.Text;

import butterknife.Bind;

/**
 * Created by jiaxx on 2016/4/16 0016.
 */
public class ShopStayShowDetailActivity extends BaseActivity {
    @Bind(R.id.stay_time_tv)
    TextView stayTimeTv; //住店时间
    @Bind(R.id.live_time_tv)
    TextView liveTimeTv; //离店时间
    @Bind(R.id.backTv)
    TextView backTv;
    @Bind(R.id.addShoppingCartTv)
    TextView addShoppingCartTv;
    @Bind(R.id.buyNowTv)
    TextView buyNowTv;
    @Override
    protected void initContentView() {
        setContentView(R.layout.shop_stay_detail_layout);
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
            case R.id.backTv:
                finish();
                break;
        }
    }

    public void setStayLiveTime() {
        String stay_in = SharedPreferences.getInstance().getString("live_in", "住店:");
        String live_out = SharedPreferences.getInstance().getString("live_out", "离店:");
        stayTimeTv.setText(stay_in + "住店");
        liveTimeTv.setText(live_out + "离店");
    }
}
