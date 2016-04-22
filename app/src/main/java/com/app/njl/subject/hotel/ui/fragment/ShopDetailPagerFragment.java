package com.app.njl.subject.hotel.ui.fragment;

import android.content.Intent;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.njl.R;
import com.app.njl.activity.CalendarActivity;
import com.app.njl.base.BaseFragment;
import com.app.njl.subject.hotel.adapter.CommonPagerAdapter;
import com.app.njl.ui.tabstrip.PagerSlidingTabStrip;
import com.app.njl.utils.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class ShopDetailPagerFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @Bind(R.id.pager)
    ViewPager pager;

    @Bind(R.id.stay_live_time_ll)
    LinearLayout stay_live_time_ll; //住店、离店
    @Bind(R.id.stay_time_tv)
    TextView stay_time_tv; //住店
    @Bind(R.id.live_time_tv)
    TextView live_time_tv; //离店

    @Override
    public int getLayoutRes() {
        getActivity().findViewById(R.id.layoutFooter).setVisibility(View.GONE);
        return R.layout.njl_layout_shopshow;
    }

    @Override
    public void initView() {
        String[] titles = getResources().getStringArray(R.array.shop_item_title);
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new ShopDetailStayFragment());
        fragments.add(new ShopDetailOrderFragment());
        fragments.add(new ShopDetailPlayFragment());
        fragments.add(new ShopDetailStayFragment());
        FragmentPagerAdapter adapter = new CommonPagerAdapter(getChildFragmentManager(), fragments, titles);
        pager.setAdapter(adapter);

        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
        pager.setPageMargin(pageMargin);

        tabs.setViewPager(pager);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initLocalData() {
        setStayLiveTime();
    }

    @Override
    public void initRemoteData() {

    }

    @Override
    public void setListener() {
        stay_live_time_ll.setOnClickListener(this);
    }

    public void setStayLiveTime() {
        String stay_in = SharedPreferences.getInstance().getString("live_in", "住店:");
        String live_out = SharedPreferences.getInstance().getString("live_out", "离店:");
        stay_time_tv.setText("住店:" + stay_in);
        live_time_tv.setText("离店:" + live_out);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().findViewById(R.id.layoutFooter).setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.stay_live_time_ll:
                Intent intent_live = new Intent(getContext(), CalendarActivity.class);
                getActivity().startActivityForResult(intent_live, 4);
                break;
        }
    }
}
