package com.app.njl.subject.hotel.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
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
import com.app.njl.subject.hotel.model.entity.shoplist.ShopCommonMessage;
import com.app.njl.subject.hotel.model.entity.shoplist.ShopPicsBean;
import com.app.njl.subject.hotel.ui.ShoppingCarActivity;
import com.app.njl.subject.mine.nohttp.CallServer;
import com.app.njl.subject.mine.nohttp.Constants;
import com.app.njl.subject.mine.nohttp.HttpConstants;
import com.app.njl.subject.mine.nohttp.HttpListener;
import com.app.njl.subject.mine.nohttp.StringRequestImpl;
import com.app.njl.ui.loopviewpager.AutoLoopViewPager;
import com.app.njl.ui.tabstrip.PagerSlidingTabStrip;
import com.app.njl.ui.viewpagerindicator.CirclePageIndicator;
import com.app.njl.utils.JsonEasy;
import com.app.njl.utils.SharedPreferences;
import com.bumptech.glide.Glide;
import com.socks.library.KLog;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.app.njl.R.id.shopName;

public class ShopDetailPagerActivity extends AppCompatActivity implements View.OnClickListener, HttpListener<String> {

    @Bind(R.id.position_tvName)
    TextView mPositionName;
    @Bind(R.id.position_tvContent)
    TextView mPositionContent;
    @Bind(R.id.comment_num)
    TextView mCommentNum;
    @Bind(R.id.reComment_num)
    TextView mReCommentNum;
    @Bind(R.id.comment_button)
    TextView mCommentButton;
    @Bind(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @Bind(R.id.pager)
    ViewPager pager;

    //@Bind(R.id.back_ll)
    //LinearLayout backLl; //返回
    //@Bind(R.id.title)
    //TextView titleTv; //标题
    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(shopName)
    TextView mShopNameTv;
    @Bind(R.id.picNum)
    TextView mPicNumTv;

    @Bind(R.id.stay_live_time_ll)
    LinearLayout stay_live_time_ll; //住店、离店
    @Bind(R.id.stay_time_tv)
    TextView stay_time_tv; //住店
    @Bind(R.id.live_time_tv)
    TextView live_time_tv; //离店
    @Bind(R.id.tv_total)
    TextView mTotalDaysTv;
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
    private List<String> imageViewIds;
    private List<String> imageList = new ArrayList<String>(Arrays.asList(
            "http://pic.nipic.com/2008-07-11/20087119630716_2.jpg",
            "http://pic.nipic.com/2008-07-11/20087119630716_2.jpg",
            "http://pic.nipic.com/2008-07-11/20087119630716_2.jpg"));

    private int mShopId = 1;

    private String mShopName = "";

    private ShopCommonMessage mCommonMessage;

    private int type = 0; //0、住宿 1、餐饮 2、游玩 3、特产

    public int getmShopId() {
        return mShopId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.njl_layout_shopshow);
        //初始化view注入
        ButterKnife.bind(this);
        initView();
        initLocalData();
        setListener();
    }

    public void initView() {
        Intent intent = getIntent();
        type = intent.getIntExtra("type", 1);
        mCommonMessage = (ShopCommonMessage)intent.getSerializableExtra("shopCommonMessage");
        if(mCommonMessage == null) return;
        mShopName = mCommonMessage.getShopName();
        mShopId = mCommonMessage.getShopId();
        setSupportActionBar(toolbar);

//        toolbar.setTitle(mShopName);
//        toolbarLayout.setExpandedTitleColor(R.color.transparent_half);
        mShopNameTv.setText(mShopName);
        toolbarLayout.setTitle(mShopName);
        //通过CollapsingToolbarLayout修改字体颜色
        toolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);//设置还没收缩时状态下字体颜色
        toolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的颜色


        //设置店家信息
        setShopInfos();

//        setTitle("toolbar");
        // 设置返回主页的按钮
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String[] titles = getResources().getStringArray(R.array.shop_item_title);
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new ShopDetailStayFragment());
        fragments.add(new ShopDetailOrderFragment());
        fragments.add(new ShopDetailPlayFragment());
        fragments.add(new ShopDetailSpecialtyFragment());
        FragmentPagerAdapter adapter = new ShopDetailPagerAdapter(getSupportFragmentManager(), fragments, titles);
        pager.setAdapter(adapter);

        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
        pager.setPageMargin(pageMargin);

        tabs.setViewPager(pager);
        tabs.setDividerColor(R.color.gray_bg);
        tabs.setSelectedTextColor(R.color.green_light);
        tabs.setUnderlineColor(0X00000000);
        pager.setCurrentItem(type, true);
    }

    private void setShopInfos() {
        //设置地址
        mPositionName.setText(mCommonMessage.getAddressDetail());
        mPositionContent.setText("距离地铁站0.1公里");
        //设置评论信息
        mCommentNum.setText(mCommonMessage.getGoodCommentNum() + "/" + mCommonMessage.getTotalCommentNum() + "评论");
        mReCommentNum.setText(mCommonMessage.getRecommendNum() + "推荐");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_edit) {
//            Toast.show("select clock item");
            Intent intent_live = new Intent(this, CalendarActivity.class);
            startActivityForResult(intent_live, 4);
            overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
            return true;
        }
        //if(item.getItemId() == R.id.home) {
            finish();
            //onBackPressed();
            return true;
        //}
        //return super.onOptionsItemSelected(item);
    }

    public void initLocalData() {
        /*String title = getArguments().getString("title");
        mShopId = getArguments().getInt("shopId", 0);*/
        //titleTv.setText(title);
        setStayLiveTime();
        //获取顶部图片
        queryStorePicsByOptions(mShopId, 1);
    }

    public void setListener() {
        stay_live_time_ll.setOnClickListener(this);
        shopping_car_tv.setOnClickListener(this);
        connect_shopTv.setOnClickListener(this);
    }

    public void setStayLiveTime() {
        String stay_in = SharedPreferences.getInstance().getInt("live_in_month", 0) + "月" + SharedPreferences.getInstance().getInt("live_in_day", 0) + "日";
        String live_out = SharedPreferences.getInstance().getInt("live_out_month", 0) + "月" + SharedPreferences.getInstance().getInt("live_out_day", 0) + "日";
        int total_days = SharedPreferences.getInstance().getInt("total_day", 1);
        stay_time_tv.setText(stay_in);
        live_time_tv.setText(live_out);
        mTotalDaysTv.setText("共" + total_days + "晚");
    }

    @Override
    public void onResume() {
        super.onResume();
        top_pager.startAutoScroll();
        setStayLiveTime();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        top_pager.stopAutoScroll();
        super.onDestroy();
        //getActivity().findViewById(R.id.layoutFooter).setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.stay_live_time_ll:
                Intent intent_live = new Intent(this, CalendarActivity.class);
                startActivityForResult(intent_live, 4);
                overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                break;
            case R.id.shopping_car_tv:
                Intent intent_shopping_car = new Intent(this, ShoppingCarActivity.class);
                startActivity(intent_shopping_car);
                overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                break;
            case R.id.back_ll:
                getFragmentManager().popBackStack();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setStayLiveTime();
    }

    public void queryStorePicsByOptions(int shopId, int productType) {
        Request<String> request = new StringRequestImpl(Constants.BASE_PATH + "shop/logo", RequestMethod.POST);
        request.add("shopId", shopId);
        request.add("productType", productType);
        CallServer.getRequestInstance().add(this, HttpConstants.QUERY_STORE_PICS_BYOPTIONS, request, this, true, true);
    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        int code = 0;
        String result = response.get();
        switch (what) {
            case HttpConstants.QUERY_STORE_PICS_BYOPTIONS:
                Log.i("result", "result get store pics:" + result);
                ShopPicsBean bean = JsonEasy.toObject(result, ShopPicsBean.class);
                if(bean == null) return;
                code = bean.getCode();
                if(code == 1) {
                    List<ShopPicsBean.MessageBean> messageBeens = bean.getMessage();
                    if((messageBeens = bean.getMessage()) == null) return;
                    imageViewIds = new ArrayList<>();
                    for(int i=0; i< messageBeens.size(); i++) {
                        imageViewIds.add(messageBeens.get(i).getPicUrl());
                    }
                    mPicNumTv.setText(imageViewIds.size() + "");
                    galleryAdapter = new DetailPagerGalleryPagerAdapter();
                    top_pager.setAdapter(galleryAdapter);
                    indicator.setViewPager(top_pager);
    //        indicator.setPadding(5, 5, 10, 5);
                }
                /*imageViewIds = new int[] { R.mipmap.house_background, R.mipmap.house_background_1, R.mipmap.house_background_2};

                galleryAdapter = new DetailPagerGalleryPagerAdapter();
                top_pager.setAdapter(galleryAdapter);
                indicator.setViewPager(top_pager);
//        indicator.setPadding(5, 5, 10, 5);*/
                break;
        }
    }

    @Override
    public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {

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
            if(imageViewIds == null) return 0;
            if(imageViewIds.size() >= 3) return 3;
            return imageViewIds.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView item = new ImageView(getApplicationContext());
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(-1, -1);
            item.setLayoutParams(params);
            item.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(getApplicationContext()).load(imageViewIds.get(position))
                    .placeholder(R.mipmap.default_image)
                    .error(R.mipmap.default_image)
                    .into(item);
            container.addView(item);

            final int pos = position;
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), ImageGalleryActivity.class);
                    intent.putStringArrayListExtra("images", new ArrayList<String>(imageViewIds));
                    intent.putExtra("position", pos);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
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
