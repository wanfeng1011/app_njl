package com.app.njl.subject.hotel.ui.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.njl.R;
import com.app.njl.activity.CalendarActivity;
import com.app.njl.activity.ImageGalleryActivity;
import com.app.njl.activity.MainActivity;
import com.app.njl.adapter.BrowsingHistoryListAdapter;
import com.app.njl.base.BaseFragment;
import com.app.njl.subject.hotel.adapter.MainFragmentShopListAdapter;
import com.app.njl.subject.hotel.listener.MyOnBothRefreshListener;
import com.app.njl.subject.hotel.model.entity.Fruit;
import com.app.njl.subject.hotel.presenter.impl.ShopListQueryPresenterImpl;
import com.app.njl.subject.hotel.presenter.interfaces.IShopListQueryPresenter;
import com.app.njl.subject.hotel.view.CommonView;
import com.app.njl.ui.loopviewpager.AutoLoopViewPager;
import com.app.njl.ui.viewpagerindicator.CirclePageIndicator;
import com.app.njl.utils.SharedPreferences;
import com.citypicker.CityPickerActivity;
import com.yolanda.nohttp.Request;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import space.sye.z.library.RefreshRecyclerView;
import space.sye.z.library.adapter.RefreshRecyclerViewAdapter;
import space.sye.z.library.manager.RecyclerMode;
import space.sye.z.library.manager.RecyclerViewManager;

/**
 * Created by jiaxx on 2016/3/28 0028.
 */
public class HotelMainFragment extends BaseFragment implements View.OnClickListener, CommonView<Fruit> {
    @Bind(R.id.recyclerView)
    RefreshRecyclerView recyclerView; //主RecyclerView
    private RecyclerView mRecyclerView; //头部RecyclerView
    private TextView tv_destination; //搜索目的地
    private Button search_btn; //搜索按钮
    private RelativeLayout live_rl;
    private TextView live_in_tv; //住店
    private TextView live_out_tv; //离店
    private TextView tv_total;

    AutoLoopViewPager pager;
    CirclePageIndicator indicator;
    private GalleryPagerAdapter galleryAdapter;
    private int[] imageViewIds;
    private List<String> imageList = new ArrayList<String>(Arrays.asList(
            "http://pic.nipic.com/2008-07-11/20087119630716_2.jpg",
            "http://pic.nipic.com/2008-07-11/20087119630716_2.jpg",
            "http://pic.nipic.com/2008-07-11/20087119630716_2.jpg"));


    private String live_inStr;
    private String live_outStr;
    private int live_totalDay;
    private BrowsingHistoryListAdapter mAdapter; //头部内容部分RecyclerView的adapter
    private MainFragmentShopListAdapter myAdapter; //内容部分RecyclerView的adapter
    private List<Fruit> dataList = new ArrayList<>();
    private View header, footer;
    private Request<JSONArray> request;

    IShopListQueryPresenter loadAllShopPresenter;

    @Override
    public int getLayoutRes() {
        return R.layout.library_activity_main;
    }

    @Override
    public void initView() {
        header = View.inflate(getContext(), R.layout.library_recycler_header, null);
        pager = (AutoLoopViewPager) header.findViewById(R.id.pager);
        indicator = (CirclePageIndicator) header.findViewById(R.id.indicator);
        tv_destination = (TextView) header.findViewById(R.id.tv_destination);
        search_btn = (Button) header.findViewById(R.id.search_btn);
        live_rl = (RelativeLayout) header.findViewById(R.id.live_relativelayout);
        live_in_tv = (TextView) header.findViewById(R.id.live_in_tv);
        live_out_tv = (TextView) header.findViewById(R.id.live_out_tv);
        tv_total = (TextView) header.findViewById(R.id.tv_total);
        mRecyclerView = (RecyclerView)header.findViewById(R.id.id_recyclerview_record);
        footer = View.inflate(getContext(), R.layout.library_recycler_footer, null);
    }

    @Override
    public void initLocalData() {
        imageViewIds = new int[] { R.mipmap.house_background, R.mipmap.house_background_1, R.mipmap.house_background_2};

        galleryAdapter = new GalleryPagerAdapter();
        pager.setAdapter(galleryAdapter);
        indicator.setViewPager(pager);
        indicator.setPadding(5, 5, 10, 5);

        setSharePrefData();
        //显示默认住店日期
        showLiveData();
        //初始化RecyclerView
        initRecyclerView();
    }

    @Override
    public void initRemoteData() {
        //请求网络数据
        requestNetData();
    }

    @Override
    public void initPresenter() {
        loadAllShopPresenter = new ShopListQueryPresenterImpl();
    }

    @Override
    public void setListener() {
        tv_destination.setOnClickListener(this);
        live_rl.setOnClickListener(this);
        search_btn.setOnClickListener(this);

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//                Intent intent = new Intent(getContext(), LoginActivity.class);
//                startActivity(intent);
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    /**
     * 设置shareprefrence
     */
    private void setSharePrefData() {
        //获取当前日期
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        live_inStr = (today.month+1) + "月" + today.monthDay + "日";
        live_outStr = (today.month+1) + "月" + (today.monthDay + 1) + "日";
        live_totalDay = 1;
        //设置默认住店离店日期到SharePreference
        SharedPreferences.getInstance().putString("live_in", live_inStr);
        SharedPreferences.getInstance().putString("live_out", live_outStr);
        SharedPreferences.getInstance().putInt("total_day", live_totalDay);
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        mAdapter = new BrowsingHistoryListAdapter();
        //图片显示控件
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mRecyclerView.setAdapter(mAdapter);
        myAdapter = new MainFragmentShopListAdapter(getContext(), dataList);

        RecyclerViewManager recyclerViewManager = new RecyclerViewManager();

        recyclerViewManager.with(myAdapter, new LinearLayoutManager(getContext()))
                .setMode(RecyclerMode.BOTH)
                .addHeaderView(header)
                .addFooterView(footer)
                .setOnBothRefreshListener(new MyOnBothRefreshListener(HotelMainFragment.this))
                .setOnItemClickListener(new RefreshRecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView.ViewHolder holder, int position) {
                        Toast.makeText(getContext(), "item" + position, Toast.LENGTH_SHORT).show();
                    }
                })
                .into(recyclerView, getContext());
    }

    /**
     * 请求网络数据
     */
    private void requestNetData() {
        loadAllShopPresenter.attachView(this);
        loadAllShopPresenter.searchAllShop();
    }

    /**
     * 设置住店、离店日期
     */
    public void showLiveData() {
        String live_in = SharedPreferences.getInstance().getString("live_in", live_inStr);
        String live_out = SharedPreferences.getInstance().getString("live_out", live_outStr);
        int totalDay = SharedPreferences.getInstance().getInt("total_day", live_totalDay);
        live_in_tv.setText(live_in);
        live_out_tv.setText(live_out);
        tv_total.setText("共计" + totalDay + "日");
    }

    /**
     * 显示fragment
     */
    private void showFragment() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new ShopListPagerFragment();
        for (int j = 0; j < MainActivity.fragmentTags.size(); j++) {
            Fragment f = fragmentManager.findFragmentByTag(MainActivity.fragmentTags.get(j));
            if (f != null && f.isAdded()) {
                fragmentTransaction.hide(HotelMainFragment.this);
            }
        }
        if (fragment.isAdded()) {
            fragmentTransaction.show(fragment);
        } else {
            if (!MainActivity.fragmentTags.contains("ShopListPagerFragment")) {
                MainActivity.fragmentTags.add("ShopListPagerFragment");
            }
            fragmentTransaction.add(R.id.fragment_container, fragment, "ShopListPagerFragment");
        }
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();
    }

    /**
     * 显示城市
     * @param city 城市名称
     */
    public void setDestinationData(String city) {
        tv_destination.setText(city);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.live_relativelayout:
                Intent intent_live = new Intent(getContext(), CalendarActivity.class);
                getActivity().startActivityForResult(intent_live, 1);
                getActivity().overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                break;
            case R.id.tv_destination:
                Intent intent_destination = new Intent(getContext(), CityPickerActivity.class);
                getActivity().startActivityForResult(intent_destination, 2);
                getActivity().overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                break;
            case R.id.search_btn:
                showFragment();
                break;
        }
    }

    //--------------------- /** LoadAllRecyclerView回调方法 **/ -------------//
    @Override
    public void loadSuccess(List<Fruit> fruits) {
        dataList.addAll(fruits);
        myAdapter.setData(dataList);
    }

    @Override
    public void loadFailed() {
        recyclerView.onRefreshCompleted();
    }

    @Override
    public void loadCompleted() {
        recyclerView.onRefreshCompleted();
    }

    @Override
    public void showToast(String showMessage) {

    }
    //--------------------- /** LoadAllRecyclerView回调方法 **/ -------------//


    @Override
    public void onResume() {
        super.onResume();
        pager.startAutoScroll();
    }

    @Override
    public void onDestroy() {
        pager.stopAutoScroll();
        loadAllShopPresenter.detachView();
        super.onDestroy();
    }

    //轮播图适配器
    public class GalleryPagerAdapter extends PagerAdapter {

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
