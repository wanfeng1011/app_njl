package com.app.njl.subject.order.ui;

import android.view.View;

import com.app.njl.R;
import com.app.njl.base.BaseFragment;

/**
 * Created by root on 2016/4/13.
 */
public class OrderDetailFragment extends BaseFragment {
    @Override
    public int getLayoutRes() {
        getActivity().findViewById(R.id.layoutFooter).setVisibility(View.GONE);
        return R.layout.order_detail_layout;
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

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().findViewById(R.id.layoutFooter).setVisibility(View.VISIBLE);
    }
}
