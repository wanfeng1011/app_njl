package com.app.njl.subject.order.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.njl.R;
import com.app.njl.base.BaseActivity;
import com.app.njl.subject.hotel.ui.ShopOrderMessageActivity;

import butterknife.Bind;

/**
 * Created by jiaxx on 2016/5/5 0005.
 */
public class OrderMakeSureActivity extends BaseActivity {
    @Bind(R.id.title)
    TextView mTitleTv;
    @Bind(R.id.order_makeSure_detail_ll)
    LinearLayout order_makeSure_detail_ll;
    @Override
    protected void initContentView() {
        setContentView(R.layout.order_make_sure_layout);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mTitleTv.setText("消费确认");
        order_makeSure_detail_ll.setOnClickListener(this);
    }

    @Override
    protected void initControl() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_makeSure_detail_ll:
                Intent intent = new Intent(getApplicationContext(), ShopOrderMessageActivity.class);
                OrderMakeSureActivity.this.startActivity(intent);
                overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                break;
        }
    }
}
