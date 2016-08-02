package com.app.njl.subject.hotel.ui.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.util.Log;
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
import com.app.njl.base.BaseFragment;
import com.app.njl.subject.hotel.adapter.BrowsingHistoryListAdapter3;
import com.app.njl.subject.hotel.adapter.MainFragmentShopListAdapter;
import com.app.njl.subject.hotel.model.entity.Fruit;
import com.app.njl.subject.hotel.model.entity.homescenicspot.ScenicSpot;
import com.app.njl.subject.hotel.model.entity.homescenicspot.ScenicSpotBean;
import com.app.njl.subject.hotel.presenter.impl.ShopListQueryPresenterImpl;
import com.app.njl.subject.hotel.presenter.interfaces.IShopListQueryPresenter;
import com.app.njl.subject.hotel.view.CommonView;
import com.app.njl.subject.mine.nohttp.CallServer;
import com.app.njl.subject.mine.nohttp.Constants;
import com.app.njl.subject.mine.nohttp.HttpConstants;
import com.app.njl.subject.mine.nohttp.HttpListener;
import com.app.njl.subject.mine.nohttp.StringRequestImpl;
import com.app.njl.ui.loopviewpager.AutoLoopViewPager;
import com.app.njl.ui.viewpagerindicator.CirclePageIndicator;
import com.app.njl.utils.JsonEasy;
import com.app.njl.utils.SharedPreferences;
import com.app.njl.widget.calendar.SimpleMonthAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.socks.library.KLog;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.Response;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import space.sye.z.library.RefreshRecyclerView;
import space.sye.z.library.adapter.RefreshRecyclerViewAdapter;
import space.sye.z.library.listener.OnBothRefreshListener;
import space.sye.z.library.manager.RecyclerMode;
import space.sye.z.library.manager.RecyclerViewManager;

/**
 * Created by jiaxx on 2016/3/28 0028.
 */
public class HotelMainFragment2 extends BaseFragment implements View.OnClickListener, CommonView<Fruit>, HttpListener<String> {
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
    String urls[] = {"http://www.whateverblake.com/slogan_country.jpg?imageView2/1/w/550/h/330"
            ,"http://www.whateverblake.com/slogan_fishing.jpg?imageView2/1/w/550/h/330"
            ,"http://www.whateverblake.com/slogan_yard.jpg?imageView2/1/w/550/h/330"
            ,"http://www.whateverblake.com/slogan_moutain.jpg?imageView2/1/w/550/h/330"
            ,"http://www.whateverblake.com/1465285064271.jpg?imageView2/1/w/550/h/330"};


    private String live_inStr;
    private String live_outStr;
    private int live_totalDay;
    private BrowsingHistoryListAdapter3 mAdapter; //头部内容部分RecyclerView的adapter
    private MainFragmentShopListAdapter myAdapter; //内容部分RecyclerView的adapter
    private List<Fruit> dataList = new ArrayList<>();
    private View header, footer;
    private Request<JSONArray> request;

    IShopListQueryPresenter loadAllShopPresenter;
    public static SimpleMonthAdapter.SelectedDays<SimpleMonthAdapter.CalendarDay> selectedDays;

    private int mStoreId = 1; //景点id

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
        //初始化RecyclerView
//        initRecyclerView();
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
//        live_rl.setOnClickListener(this);
        live_in_tv.setOnClickListener(this);
        live_out_tv.setOnClickListener(this);
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

    private void loadPopularList() {
        Request<String> request = new StringRequestImpl(Constants.BASE_PATH + "popular/getPopularList", RequestMethod.POST);
        // 添加到请求队列
        CallServer.getRequestInstance().add(getActivity(), HttpConstants.GET_POPULARSHOPLIST, request, this, true, true);
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
        SharedPreferences.getInstance().putInt("live_in_year", today.year);
        SharedPreferences.getInstance().putInt("live_in_month", today.month + 1);
        SharedPreferences.getInstance().putInt("live_in_day", today.monthDay);
        SharedPreferences.getInstance().putInt("live_out_year", today.year);
        SharedPreferences.getInstance().putInt("live_out_month", today.month + 1);
        SharedPreferences.getInstance().putInt("live_out_day", (today.monthDay + 1));
        SharedPreferences.getInstance().putInt("total_day", live_totalDay);
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView(List<ScenicSpot> list) {
        mAdapter = new BrowsingHistoryListAdapter3(getContext(), list);
        //图片显示控件
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        //mRecyclerView.setAdapter(mAdapter);
        //myAdapter = new MainFragmentShopListAdapter(getContext(), dataList);

        RecyclerViewManager recyclerViewManager = new RecyclerViewManager();

//        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.space_20);
//        recyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        recyclerViewManager.with(mAdapter, new LinearLayoutManager(getContext()))
                .setMode(RecyclerMode.BOTH)
                .addHeaderView(header)
                .addFooterView(footer)
                .setOnBothRefreshListener(new OnBothRefreshListener() {
                    @Override
                    public void onPullDown() {
                        KLog.i("result onPullDown-------------");
                        recyclerView.onRefreshCompleted();
                    }

                    @Override
                    public void onLoadMore() {
                        KLog.i("result onLoadMore-------------");
                        recyclerView.onRefreshCompleted();
                    }

                    @Override
                    public void onScrolling(int newState) {

                    }
                })
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
//        loadAllShopPresenter.attachView(this);
//        loadAllShopPresenter.searchAllShop();
        loadPopularList();
    }

    /**
     * 设置住店、离店日期
     */
    public void showLiveData() {
        String live_in = SharedPreferences.getInstance().getInt("live_in_month", 0) + "月" + SharedPreferences.getInstance().getInt("live_in_day", 0) + "日";
        String live_out = SharedPreferences.getInstance().getInt("live_out_month", 0) + "月" + SharedPreferences.getInstance().getInt("live_out_day", 0) + "日";
        int totalDay = SharedPreferences.getInstance().getInt("total_day", live_totalDay);
        live_in_tv.setText(live_in);
        live_out_tv.setText(live_out);
        tv_total.setText("共" + totalDay + "晚");
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
                fragmentTransaction.hide(HotelMainFragment2.this);
            }
        }
        if (fragment.isAdded()) {
            fragmentTransaction.show(fragment);
        } else {
            if (!MainActivity.fragmentTags.contains("ShopListPagerFragment")) {
                MainActivity.fragmentTags.add("ShopListPagerFragment");
            }
            Bundle bundle = new Bundle();
            bundle.putInt("sceneryId", mStoreId);
            fragment.setArguments(bundle);
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
            case R.id.live_in_tv:
                Intent intent_live = new Intent(getContext(), CalendarActivity.class);
                intent_live.putExtra("selectedDays", selectedDays);
                startActivityForResult(intent_live, 1);
                getActivity().overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                break;
            case R.id.live_out_tv:
                intent_live = new Intent(getContext(), CalendarActivity.class);
                intent_live.putExtra("selectedDays", selectedDays);
                startActivityForResult(intent_live, 1);
                getActivity().overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                break;
            case R.id.tv_destination:
                Intent intent_destination = new Intent(getContext(), com.app.njl.activity.scenerypicker.CityPickerActivity.class);
                startActivityForResult(intent_destination, 2);
                getActivity().overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                break;
            case R.id.search_btn:
                showFragment();
//                Intent intent = new Intent(getActivity(), ShopListPagerActivity.class);
//                getActivity().startActivity(intent);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("onActivityResult", "onActivityResult data------------");
        if(requestCode == 1 && resultCode == 1) {
            showLiveData();
            selectedDays = (SimpleMonthAdapter.SelectedDays<SimpleMonthAdapter.CalendarDay>)data.getSerializableExtra("selectedDays");
            Log.i("selectDays", "onActivityResult selectedDays:" + selectedDays.toString());
        }else if(requestCode == 2 && resultCode == 2) {
            String city = data.getStringExtra("sceneryName");
            mStoreId = data.getIntExtra("sceneryId", 1);
            setDestinationData(city);
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
        //recyclerView.onRefreshCompleted();
    }

    @Override
    public void loadCompleted() {
        //recyclerView.onRefreshCompleted();
    }

    @Override
    public void showToast(String showMessage) {

    }
    //--------------------- /** LoadAllRecyclerView回调方法 **/ -------------//


    @Override
    public void onResume() {
        super.onResume();
        pager.startAutoScroll();
        //显示默认住店日期
        showLiveData();
    }

    @Override
    public void onDestroy() {
        pager.stopAutoScroll();
        loadAllShopPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        int code = 0;
        String result = response.get();
        switch (what) {
            case HttpConstants.GET_POPULARSHOPLIST:
                Log.i("result", "result get popular shop list:" + result);
                ScenicSpotBean  scenicSpotBean = JsonEasy.toObject(result, ScenicSpotBean.class);
                code = scenicSpotBean.getCode();
                if(code == 1) {
                    if(scenicSpotBean.getMessage() != null)
                        initRecyclerView(scenicSpotBean.getMessage().getResortShops());
                    KLog.i("result scenicspot bean:" + scenicSpotBean);
                }
                break;
        }
    }

    @Override
    public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {

    }

    //轮播图适配器
    public class GalleryPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return urls.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView item = new ImageView(getContext());
            //item.setImageResource(imageViewIds[position]);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(-1, -1);
            item.setLayoutParams(params);
            item.setScaleType(ImageView.ScaleType.FIT_XY);

            Glide.with(getActivity())
                    .load(urls[position])
            .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .placeholder(R.mipmap.hotel)
                    .crossFade()
                    .into(item);

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

    public class SpaceItemDecoration extends RecyclerView.ItemDecoration{

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            if(parent.getChildLayoutPosition(view) != 0)
                outRect.top = space;
        }
    }
}
