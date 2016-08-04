package com.app.njl.subject.hotel.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.njl.R;
import com.app.njl.subject.hotel.model.entity.shopdetails.ShopPlaySelfServiceBean;
import com.app.njl.subject.hotel.model.entity.shopdetails.ShopPlaySelfServicePicsBean;
import com.app.njl.subject.mine.nohttp.CallServer;
import com.app.njl.subject.mine.nohttp.Constants;
import com.app.njl.subject.mine.nohttp.HttpConstants;
import com.app.njl.subject.mine.nohttp.HttpListener;
import com.app.njl.subject.mine.nohttp.StringRequestImpl;
import com.app.njl.ui.loopviewpager.AutoLoopViewPager;
import com.app.njl.utils.JsonEasy;
import com.app.njl.utils.SharedPreferences;
import com.bumptech.glide.Glide;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.Response;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.app.njl.R.id.live_time_tv;
import static com.app.njl.R.id.stay_time_tv;

/**
 * 自助游介绍
 * Created by jiaxx on 2016/4/16 0016.
 */
public class ShopPlayShowSelfServiceActivity extends AppCompatActivity implements View.OnClickListener, HttpListener<String> {
    @Bind(R.id.collapsingToolBarLayout)
    CollapsingToolbarLayout mCollapsingToolBarLayout;
    @Bind(R.id.view_pager)
    AutoLoopViewPager mViewPager;
    @Bind(R.id.toolBar)
    Toolbar mToolBar;
    @Bind(R.id.picNum)
    TextView mPicNum;
    @Bind(R.id.stay_live_time_ll)
    LinearLayout mStayTimeLayout;
    @Bind(stay_time_tv)
    TextView stayTimeTv; //住店时间
    @Bind(live_time_tv)
    TextView liveTimeTv; //离店时间
    @Bind(R.id.tv_total)
    TextView mTotalDaysTv; //住店天数
    @Bind(R.id.addShoppingCartTv)
    TextView addShoppingCartTv;
    @Bind(R.id.buyNowTv)
    TextView buyNowTv;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.noticeRecyclerView)
    RecyclerView mNoticeRecyclerView;
    @Bind(R.id.roomPrice)
    TextView mRoomPrice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_play_selfservice_detail_layout);
        ButterKnife.bind(this);
        initView();
    }

    protected void initView() {
        setStayLiveTime();
        String selfServiceName = getIntent().getStringExtra("selfServiceName");
        setSupportActionBar(mToolBar);
        setTitle(selfServiceName);
        //mCollapsingToolBarLayout.setExpandedTitleColor(getResources().getColor(R.color.green_light));
        //mCollapsingToolBarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.transparent));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mNoticeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        addShoppingCartTv.setOnClickListener(this);
        buyNowTv.setOnClickListener(this);
        mStayTimeLayout.setOnClickListener(this);
        int mResortId = getIntent().getIntExtra("resortId", 1);
        int mFamilyActivityId = getIntent().getIntExtra("familyActivityId", 1);
        queryDatasPicByOptions(mResortId, mFamilyActivityId);
        queryDatasByOptions(mResortId, mFamilyActivityId);
    }

    protected void loadData() {

    }

    @Override
    public void onClick(View v) {
        /*switch (v.getId()) {
            case R.id.shopPlaySelfServiceDetail_backTv:
                finish();
                break;
        }*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    private void setStayLiveTime() {
        //住店时间
        String stay_in = SharedPreferences.getInstance().getInt("live_in_month", 0) + "月" + SharedPreferences.getInstance().getInt("live_in_day", 0) + "日";
        //离店时间
        String live_out = SharedPreferences.getInstance().getInt("live_out_month", 0) + "月" + SharedPreferences.getInstance().getInt("live_out_day", 0) + "日";
        //住店天数
        int total_days = SharedPreferences.getInstance().getInt("total_day", 1);
        stayTimeTv.setText(stay_in);
        liveTimeTv.setText(live_out);
        mTotalDaysTv.setText("共" + total_days + "晚");
    }

    public void queryDatasPicByOptions(int resortId, int familyActivityId) {
        Request<String> request = new StringRequestImpl(Constants.BASE_PATH + "shop/familyActivityPic", RequestMethod.POST);
        request.add("shopId", resortId);
        request.add("familyActivityId", familyActivityId);
        CallServer.getRequestInstance().add(this, HttpConstants.QUERY_SHOPPLAY_SELFSERVICE_PIC_BYOPTIONS, request, this, true, true);
    }

    public void queryDatasByOptions(int resortId, int familyActivityId) {
        Request<String> request = new StringRequestImpl(Constants.BASE_PATH + "shop/familityActivityNote", RequestMethod.POST);
        request.add("shopId", resortId);
        request.add("familyActivityId", familyActivityId);
        CallServer.getRequestInstance().add(this, HttpConstants.QUERY_SHOPPLAY_SELFSERVICE_BYOPTIONS, request, this, true, true);
    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        int code = 0;
        String result = response.get();
        switch (what) {
            case HttpConstants.QUERY_SHOPPLAY_SELFSERVICE_BYOPTIONS:
                Log.i("result", "result get shop selfService:" + result);
                if(result == null) return;
                ShopPlaySelfServiceBean shopSelfServiceBean = JsonEasy.toObject(result, ShopPlaySelfServiceBean.class);
                if(shopSelfServiceBean == null) return;
                code = shopSelfServiceBean.getCode();
                if(code == 1) {
                    DefRecyclerViewAdapter mAdapter = new DefRecyclerViewAdapter(shopSelfServiceBean.getMessage());
                    mRecyclerView.setAdapter(mAdapter);
                }
                break;
            case HttpConstants.QUERY_SHOPPLAY_SELFSERVICE_PIC_BYOPTIONS:
                Log.i("result", "result get shop selfService pic:" + result);
                if(result == null) return;
                ShopPlaySelfServicePicsBean picsBean = JsonEasy.toObject(result, ShopPlaySelfServicePicsBean.class);
                if(picsBean == null) return;
                code = picsBean.getCode();
                if(code == 1) {
                    final List<ShopPlaySelfServicePicsBean.MessageBean> messageBeans = picsBean.getMessage();
                    AutoPagerAdapter pagerAdapter = new AutoPagerAdapter(messageBeans);
                    mViewPager.setAdapter(pagerAdapter);
                    mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                            Log.i("position", "viewPager onPageScrolled position:" + position + " " + positionOffset + " " + positionOffsetPixels);
                            if(positionOffset == 0 && positionOffsetPixels == 0) {
                                mPicNum.setText((position + 1) + "/" + messageBeans.size());
                            }
                        }

                        @Override
                        public void onPageSelected(int position) {
//                            Log.i("position", "viewPager onPageSelected position:" + position);
                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {
//                            Log.i("position", "viewPager onPageScrollStateChanged position:" + state);
                        }
                    });
                }
                break;
        }
    }

    @Override
    public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {

    }

    //轮播图适配器
    public class AutoPagerAdapter extends PagerAdapter {
        private List<ShopPlaySelfServicePicsBean.MessageBean> mMessageBeens;

        public AutoPagerAdapter(List<ShopPlaySelfServicePicsBean.MessageBean> messageBeens) {
            mMessageBeens = messageBeens;
        }

        @Override
        public int getCount() {
            return mMessageBeens.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView item = new ImageView(getApplicationContext());
//            item.setImageResource(mMessageBeens.get(position).getPicUrl());
            Glide.with(getApplicationContext()).load(mMessageBeens.get(position).getPicUrl())
                    .placeholder(R.mipmap.default_image)
                    .error(R.mipmap.default_image)
                    .centerCrop()
                    .into(item);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(-1, -1);
            item.setLayoutParams(params);
            item.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(item);

            /*final int pos = position;
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), ImageGalleryActivity.class);
                    intent.putStringArrayListExtra("images", (ArrayList<String>) imageList);
                    intent.putExtra("position", pos);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                }
            });*/

            return item;
        }

        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }
    }

    class DefRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        ShopPlaySelfServiceBean.MessageBean mMessageBean;
        public DefRecyclerViewAdapter(ShopPlaySelfServiceBean.MessageBean messageBean) {
            mMessageBean = messageBean;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.room_item, parent, false);
            return new ShopPlayShowSelfServiceActivity.DefViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ShopPlayShowSelfServiceActivity.DefViewHolder viewHolder = (ShopPlayShowSelfServiceActivity.DefViewHolder)holder;
            viewHolder.itemName.setText(mMessageBean.getNote().get(position));
            viewHolder.itemName.setPadding(13, 0, 0, 0);
        }

        @Override
        public int getItemCount() {
            return mMessageBean.getNote() == null ? 0 : mMessageBean.getNote().size();
        }
    }

    class DefViewHolder extends RecyclerView.ViewHolder {
        public TextView itemName;
        public DefViewHolder(View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.name);
            itemView.findViewById(R.id.img_logo).setVisibility(View.GONE);
        }
    }
}
