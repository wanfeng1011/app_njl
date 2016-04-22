package com.app.njl.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.njl.R;
import com.app.njl.base.BaseActivity;

import butterknife.Bind;

/**
 * Created by jiaxx on 2016/4/21 0021.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.title)
    TextView tv_title;
    @Bind(R.id.back_ll)
    LinearLayout back_ll;
    @Override
    protected void initContentView() {
        setContentView(R.layout.njl_layout_register_next);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        tv_title.setText("注册");
        back_ll.setOnClickListener(this);
    }

    @Override
    protected void initControl() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_ll:
                finish();
                break;
        }
    }
}
