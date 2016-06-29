package com.app.njl.subject.order.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.njl.R;
import com.app.njl.base.BaseActivity;

import butterknife.Bind;

/**
 * Created by root on 2016/4/13.
 */
public class OrderDetailActivity extends BaseActivity {
    @Bind(R.id.order_makeSure_btn)
    Button tv_order_makeSure;
    @Bind(R.id.order_back_request)
    TextView order_back_request;


    @Override
    protected void initContentView() {
        setContentView(R.layout.order_detail_layout);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initControl() {
        tv_order_makeSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OrderMakeSureActivity.class);
                OrderDetailActivity.this.startActivity(intent);
                overridePendingTransition(R.anim.slide_right_in,R.anim.slide_left_out);
            }
        });
        order_back_request.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_back_request:
                break;
        }
    }
}
