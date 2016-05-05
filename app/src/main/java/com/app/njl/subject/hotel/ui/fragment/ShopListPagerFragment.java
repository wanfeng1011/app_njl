package com.app.njl.subject.hotel.ui.fragment;

import android.content.Intent;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.njl.R;
import com.app.njl.activity.CalendarActivity;
import com.app.njl.activity.MainActivity;
import com.app.njl.base.BaseFragment;
import com.app.njl.dialog.CustomDialog2;
import com.app.njl.subject.hotel.adapter.CommonPagerAdapter;
import com.app.njl.ui.tabstrip.PagerSlidingTabStrip;
import com.app.njl.utils.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class ShopListPagerFragment extends BaseFragment implements View.OnTouchListener, View.OnClickListener {



    @Bind(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @Bind(R.id.pager)
    ViewPager pager;

    @Bind(R.id.iv_back)
    ImageView iv_back; //返回键

    @Bind(R.id.stay_time_select_ll)
    LinearLayout stay_time_select_ll; //选择住店、离店时间
    @Bind(R.id.stay_time_tv)
    TextView stay_time_tv; //住店日期
    @Bind(R.id.live_time_tv)
    TextView live_time_tv; //离店日期

    @Bind(R.id.recommend_first_tv)
    TextView recommend_first_tv; //推荐优先
    @Bind(R.id.good_reputation_first_tv)
    TextView good_reputation_first_tv; //好评优先
    @Bind(R.id.low_price_first_tv)
    TextView low_price_first_tv; //低价优先
    @Bind(R.id.destination_first_tv)
    TextView destination_first_tv;

    FragmentPagerAdapter adapter;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_home_pager;
    }

    @Override
    public void initView() {
        MainActivity.isShowResultPager = true;
        //adapter初始化
        initPagerAdapter();
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initLocalData() {
        setStayTime();
    }

    @Override
    public void initRemoteData() {

    }

    @Override
    public void setListener() {
        recommend_first_tv.setOnTouchListener(this);
        good_reputation_first_tv.setOnTouchListener(this);
        low_price_first_tv.setOnTouchListener(this);
        destination_first_tv.setOnTouchListener(this);

        iv_back.setOnClickListener(this);
        stay_time_select_ll.setOnClickListener(this);
    }

    public void setStayTime() {
        String stay_in = SharedPreferences.getInstance().getString("live_in", "住店:");
        String live_out = SharedPreferences.getInstance().getString("live_out", "离店:");
        stay_time_tv.setText("住店:" + stay_in);
        live_time_tv.setText("离店:" + live_out);
    }

    /**
     * 初始化adapter
     */
    private void initPagerAdapter() {
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new ShopListStayFragment());
        fragments.add(new ShopListStayFragment());
        fragments.add(new ShopListStayFragment());
        fragments.add(new ShopListStayFragment());
        String[] mTitles = getContext().getResources().getStringArray(R.array.shop_item_title);
        adapter = new CommonPagerAdapter(getChildFragmentManager(), fragments, mTitles);
        pager.setAdapter(adapter);
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
        pager.setPageMargin(pageMargin);
        tabs.setViewPager(pager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MainActivity.isShowResultPager = false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.recommend_first_tv:
                resetTextViewBackGround(0);
                break;
            case R.id.good_reputation_first_tv:
                resetTextViewBackGround(1);
                break;
            case R.id.low_price_first_tv:
                resetTextViewBackGround(2);
                break;
            case R.id.destination_first_tv:
                resetTextViewBackGround(3);
                break;
        }
        return false;
    }

    private void resetTextViewBackGround(int index) {
        if (index == 0) {
            recommend_first_tv.setBackgroundResource(R.drawable.rect_green_bg);
            good_reputation_first_tv.setBackgroundResource(R.drawable.background_view_rounded_radius5);
            low_price_first_tv.setBackgroundResource(R.drawable.background_view_rounded_radius5);
            destination_first_tv.setBackgroundResource(R.drawable.background_view_rounded_radius5);
        } else if (index == 1) {
            good_reputation_first_tv.setBackgroundResource(R.drawable.rect_green_bg);
            recommend_first_tv.setBackgroundResource(R.drawable.background_view_rounded_radius5);
            low_price_first_tv.setBackgroundResource(R.drawable.background_view_rounded_radius5);
            destination_first_tv.setBackgroundResource(R.drawable.background_view_rounded_radius5);
        } else if (index == 2) {
            low_price_first_tv.setBackgroundResource(R.drawable.rect_green_bg);
            recommend_first_tv.setBackgroundResource(R.drawable.background_view_rounded_radius5);
            good_reputation_first_tv.setBackgroundResource(R.drawable.background_view_rounded_radius5);
            destination_first_tv.setBackgroundResource(R.drawable.background_view_rounded_radius5);
        } else if (index == 3) {
            destination_first_tv.setBackgroundResource(R.drawable.rect_green_bg);
            recommend_first_tv.setBackgroundResource(R.drawable.background_view_rounded_radius5);
            good_reputation_first_tv.setBackgroundResource(R.drawable.background_view_rounded_radius5);
            low_price_first_tv.setBackgroundResource(R.drawable.background_view_rounded_radius5);
            createDialog();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                getFragmentManager().popBackStack();
                break;
            case R.id.stay_time_select_ll:
                Intent intent_live = new Intent(getContext(), CalendarActivity.class);
                getActivity().startActivityForResult(intent_live, 3);
                break;
        }
    }

    private void createDialog() {
        CustomDialog2.Builder builder = new CustomDialog2.Builder(getActivity());
        builder.create().show();
    }
}
