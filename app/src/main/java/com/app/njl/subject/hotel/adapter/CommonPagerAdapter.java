package com.app.njl.subject.hotel.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.app.njl.base.BaseFragment;
import com.app.njl.subject.HomeFragment;
import com.socks.library.KLog;

import java.util.List;

/**
 * pagerAdapter 公共类
 * Created by jiaxx on 2016/4/12 0012.
 */
public class CommonPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles;
    private List<BaseFragment> mFragments;
    public CommonPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public CommonPagerAdapter(FragmentManager fm, List<BaseFragment> fragments, String[] titles) {
        this(fm);
        mFragments = fragments;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        KLog.i("getItem----------" + position);
        if (mFragments != null && mFragments.size() != 0)
            return mFragments.get(position);
        else
            return new HomeFragment();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position % mTitles.length];
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }
}
