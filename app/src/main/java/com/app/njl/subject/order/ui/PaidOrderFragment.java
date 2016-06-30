package com.app.njl.subject.order.ui;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.app.njl.R;
import com.app.njl.base.BaseFragment;

import butterknife.Bind;

/**
 * 已支付Fragment
 * Created by jiaxx on 2016/4/13 0013.
 */
public class PaidOrderFragment extends BaseFragment {
    @Bind(R.id.time_rl)
    RelativeLayout time_rl;

    @Override
    public int getLayoutRes() {
        return R.layout.order_paid_layout;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initLocalData() {

    }

    @Override
    public void initRemoteData() {

    }

    @Override
    public void setListener() {
        time_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), OrderDetailActivity.class);
                getContext().startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
