package com.app.njl.subject.hotel.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.app.njl.R;
import com.app.njl.base.BaseActivity;

import butterknife.Bind;

/**
 * Created by jiaxx on 2016/4/16 0016.
 */
public class ShopOrderShopDetailActivity extends BaseActivity {
    @Bind(R.id.cancelTv)
    TextView cancelTv; //取消
    @Bind(R.id.sureTv)
    TextView sureTv; //确认
    @Override
    protected void initContentView() {
        setContentView(R.layout.shop_order_detail_layout);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        cancelTv.setOnClickListener(this);
        sureTv.setOnClickListener(this);
    }

    @Override
    protected void initControl() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancelTv:
                finish();
                break;
        }
    }
}
