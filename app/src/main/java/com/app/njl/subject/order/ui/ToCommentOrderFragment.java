package com.app.njl.subject.order.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.app.njl.R;
import com.app.njl.base.BaseFragment;

import butterknife.Bind;

/**
 * 待点评Fragment
 * Created by jiaxx on 2016/4/13 0013.
 */
public class ToCommentOrderFragment extends BaseFragment {
    @Bind(R.id.order_tocomment_bt)
    Button order_tocomment_bt;
    @Override
    public int getLayoutRes() {
        return R.layout.order_tocomment_layout;
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
        order_tocomment_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ListViewActivity.class);
                getContext().startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
            }
        });
    }
}
