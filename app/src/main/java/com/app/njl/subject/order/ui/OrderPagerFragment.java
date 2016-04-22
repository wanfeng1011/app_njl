package com.app.njl.subject.order.ui;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;

import com.app.njl.R;
import com.app.njl.base.BaseFragment;
import com.app.njl.subject.hotel.adapter.CommonPagerAdapter;
import com.app.njl.ui.tabstrip.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by jiaxx on 2016/4/13 0013.
 */

public class OrderPagerFragment extends BaseFragment {

    @Bind(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @Bind(R.id.pager)
    ViewPager pager;

    FragmentPagerAdapter adapter;
    List<BaseFragment> fragments;

    @Override
    public int getLayoutRes() {
        return R.layout.orderpager_layout;
    }

    @Override
    public void initView() {
        //adapter初始化
        initPagerAdapter();
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

    /**
     * 初始化adapter
     */
    private void initPagerAdapter() {
        fragments = new ArrayList<>();
        fragments.add(new PaidOrderFragment());
        fragments.add(new ObligationOrderFragment());
        fragments.add(new ToCommentOrderFragment());
        fragments.add(new PaidOrderFragment());
        String[] mTitles = getContext().getResources().getStringArray(R.array.order_item_title);
        adapter = new CommonPagerAdapter(getChildFragmentManager(), fragments, mTitles);
        pager.setAdapter(adapter);
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
        pager.setPageMargin(pageMargin);
        tabs.setViewPager(pager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

