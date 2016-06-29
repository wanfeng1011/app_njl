package com.app.njl.subject.hotel.ui.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.njl.R;
import com.app.njl.activity.CalendarActivity;
import com.app.njl.activity.ImageGalleryActivity;
import com.app.njl.base.BaseFragment;
import com.app.njl.subject.HomeFragment;
import com.app.njl.subject.hotel.ui.ShoppingCarActivity;
import com.app.njl.ui.loopviewpager.AutoLoopViewPager;
import com.app.njl.ui.tabstrip.PagerSlidingTabStrip;
import com.app.njl.ui.viewpagerindicator.CirclePageIndicator;
import com.app.njl.utils.SharedPreferences;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

public class ShopDetailPagerFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @Bind(R.id.pager)
    ViewPager pager;

    @Bind(R.id.back_ll)
    LinearLayout backLl; //返回
    @Bind(R.id.title)
    TextView titleTv; //标题

    @Bind(R.id.stay_live_time_ll)
    LinearLayout stay_live_time_ll; //住店、离店
    @Bind(R.id.stay_time_tv)
    TextView stay_time_tv; //住店
    @Bind(R.id.live_time_tv)
    TextView live_time_tv; //离店
    @Bind(R.id.order_completed)
    TextView order_completed;
    @Bind(R.id.shopping_car_tv) //购物车
    TextView shopping_car_tv;
    @Bind(R.id.connect_shop)
    TextView connect_shopTv; //联系店家

    @Bind(R.id.view_pager)
    AutoLoopViewPager top_pager;
    @Bind(R.id.indicator)
    CirclePageIndicator indicator;
    private DetailPagerGalleryPagerAdapter galleryAdapter;
    private int[] imageViewIds;
    private List<String> imageList = new ArrayList<String>(Arrays.asList(
            "http://pic.nipic.com/2008-07-11/20087119630716_2.jpg",
            "http://pic.nipic.com/2008-07-11/20087119630716_2.jpg",
            "http://pic.nipic.com/2008-07-11/20087119630716_2.jpg"));

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
        fragments.add(new ShopDetailSpecialtyFragment());
        FragmentPagerAdapter adapter = new ShopDetailPagerAdapter(getChildFragmentManager(), fragments, titles);
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
        String title = getArguments().getString("title");
        titleTv.setText(title);
        imageViewIds = new int[] { R.mipmap.house_background, R.mipmap.house_background_1, R.mipmap.house_background_2};

        galleryAdapter = new DetailPagerGalleryPagerAdapter();
        top_pager.setAdapter(galleryAdapter);
        indicator.setViewPager(top_pager);
        indicator.setPadding(5, 5, 10, 5);
        setStayLiveTime();
    }

    @Override
    public void initRemoteData() {

    }

    @Override
    public void setListener() {
        stay_live_time_ll.setOnClickListener(this);
        shopping_car_tv.setOnClickListener(this);
        connect_shopTv.setOnClickListener(this);
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
        top_pager.startAutoScroll();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        top_pager.stopAutoScroll();
        super.onDestroy();
        getActivity().findViewById(R.id.layoutFooter).setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.stay_live_time_ll:
                Intent intent_live = new Intent(getContext(), CalendarActivity.class);
                getActivity().startActivityForResult(intent_live, 4);
                getActivity().overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                break;
            case R.id.shopping_car_tv:
                Intent intent_shopping_car = new Intent(getContext(), ShoppingCarActivity.class);
                getActivity().startActivity(intent_shopping_car);
                getActivity().overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                break;
            case R.id.back_ll:
                getFragmentManager().popBackStack();
                break;
        }
    }

    public class ShopDetailPagerAdapter extends FragmentPagerAdapter {
        private String[] mTitles;
        private List<BaseFragment> mFragments;
        public ShopDetailPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        public ShopDetailPagerAdapter(FragmentManager fm, List<BaseFragment> fragments, String[] titles) {
            this(fm);
            mFragments = fragments;
            mTitles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            KLog.i("getItem----------" + position);
            if (mFragments != null && mFragments.size() != 0) {
                //if (position )
                return mFragments.get(position);
            } else {
                return new HomeFragment();
            }
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

    //轮播图适配器
    public class DetailPagerGalleryPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageViewIds.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView item = new ImageView(getContext());
            item.setImageResource(imageViewIds[position]);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(-1, -1);
            item.setLayoutParams(params);
            item.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(item);

            final int pos = position;
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), ImageGalleryActivity.class);
                    intent.putStringArrayListExtra("images", (ArrayList<String>) imageList);
                    intent.putExtra("position", pos);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                }
            });

            return item;
        }

        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }
    }
}
