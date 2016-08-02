package com.app.njl.subject.hotel.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
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
import com.app.njl.activity.CalendarActivity;
import com.app.njl.subject.hotel.model.entity.shopdetails.ShopRoomInfos;
import com.app.njl.subject.hotel.model.entity.shopdetails.ShopRoomPicsBean;
import com.app.njl.subject.hotel.model.entity.shopstaydetail.ShopStaysDetailData;
import com.app.njl.subject.mine.nohttp.CallServer;
import com.app.njl.subject.mine.nohttp.Constants;
import com.app.njl.subject.mine.nohttp.HttpConstants;
import com.app.njl.subject.mine.nohttp.HttpListener;
import com.app.njl.subject.mine.nohttp.StringRequestImpl;
import com.app.njl.ui.loopviewpager.AutoLoopViewPager;
import com.app.njl.utils.JsonEasy;
import com.app.njl.utils.SharedPreferences;
import com.app.njl.utils.Toast;
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
 * Created by jiaxx on 2016/4/16 0016.
 */
public class ShopStayShowDetailActivity extends AppCompatActivity implements View.OnClickListener, HttpListener<String> {
    @Bind(R.id.collapsingToolBarLayout)
    CollapsingToolbarLayout mCollapsingToolBarLayout;
    @Bind(R.id.view_pager)
    AutoLoopViewPager mViewPager;
    @Bind(R.id.toolBar)
    Toolbar mToolBar;
    @Bind(R.id.picNum)
    TextView mPicNum;
    @Bind(R.id.roomName)
    TextView mRoomName;
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
    @Bind(R.id.roomRecyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.noticeRecyclerView)
    RecyclerView mNoticeRecyclerView;
    @Bind(R.id.roomPrice)
    TextView mRoomPrice;

    private ShopStaysDetailData.MessageBean mMessageBean;
    private int mShopId = 1;
    private int mRoomId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_stay_detail_layout);
        //初始化view注入
        ButterKnife.bind(this);
        initView();
        loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setStayLiveTime();
    }

    protected void initView() {
        setSupportActionBar(mToolBar);
        //mCollapsingToolBarLayout.setExpandedTitleColor(getResources().getColor(R.color.green_light));
        //mCollapsingToolBarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.transparent));
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mNoticeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        addShoppingCartTv.setOnClickListener(this);
        buyNowTv.setOnClickListener(this);
        mStayTimeLayout.setOnClickListener(this);
    }

    protected void loadData() {
        Intent intent = getIntent();
        mMessageBean = (ShopStaysDetailData.MessageBean)intent.getSerializableExtra("messageBean");
        setTitle(mMessageBean.getRoomName());
        mRoomName.setText("房间设施");
        mRoomPrice.setText("￥" + mMessageBean.getPrice() + "起/晚");
        queryShopDetailLivesPicByOptions(mMessageBean.getShopId(), mMessageBean.getRoomId());
        queryShopDetailLivesByOptions(mMessageBean.getShopId(), mMessageBean.getRoomId());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_edit) {
            Toast.show("select clock item");
            return true;
        }
        //if(item.getItemId() == R.id.home) {
        finish();
        //onBackPressed();
        return true;
        //}
        //return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.stay_live_time_ll:
                Intent intent_live = new Intent(this, CalendarActivity.class);
                startActivityForResult(intent_live, 4);
                overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setStayLiveTime();
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

    public void queryShopDetailLivesPicByOptions(int shopId, int roomId) {
        Request<String> request = new StringRequestImpl(Constants.BASE_PATH + "shop/roomPic", RequestMethod.POST);
        request.add("shopId", shopId);
        request.add("roomId", roomId);
        CallServer.getRequestInstance().add(this, HttpConstants.QUERY_SHOPROOMPICS_BYOPTIONS, request, this, true, true);
    }

    public void queryShopDetailLivesByOptions(int shopId, int roomId) {
        Request<String> request = new StringRequestImpl(Constants.BASE_PATH + "shop/roomInfo", RequestMethod.POST);
        request.add("shopId", shopId);
        request.add("roomId", roomId);
        CallServer.getRequestInstance().add(this, HttpConstants.QUERY_SHOPROOM_BYOPTIONS, request, this, true, true);
    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        int code = 0;
        String result = response.get();
        switch (what) {
            case HttpConstants.QUERY_SHOPROOM_BYOPTIONS:
                Log.i("result", "result get shop detail room:" + result);
                ShopRoomInfos roomInfos = JsonEasy.toObject(result, ShopRoomInfos.class);
                if(roomInfos == null) return;
                code = roomInfos.getCode();
                if(code == 1) {
                    if(roomInfos.getMessage() == null) return;
                    List<ShopRoomInfos.MessageBean.ShopRoomFacilityVosBean> shopRoomFacilityVoBeans = null;
                    if((shopRoomFacilityVoBeans = roomInfos.getMessage().getShopRoomFacilityVos()) != null) {
                        RoomRecyclerViewAdapter roomRecyclerViewAdapter = new RoomRecyclerViewAdapter(shopRoomFacilityVoBeans);
                        mRecyclerView.setAdapter(roomRecyclerViewAdapter);
                    }
                    List<ShopRoomInfos.MessageBean.ShopRoomNoticeVosBean> shopRoomNoticeVosBeans = null;
                    if((shopRoomNoticeVosBeans = roomInfos.getMessage().getShopRoomNoticeVos()) != null) {
                        RoomNoticeRecyclerViewAdapter roomRecyclerViewAdapter = new RoomNoticeRecyclerViewAdapter(shopRoomNoticeVosBeans);
                        mNoticeRecyclerView.setAdapter(roomRecyclerViewAdapter);
                    }
                }
                break;
            case HttpConstants.QUERY_SHOPROOMPICS_BYOPTIONS:
                Log.i("result", "result get shop detail room pic:" + result);
                ShopRoomPicsBean picsBean = JsonEasy.toObject(result, ShopRoomPicsBean.class);
                if(picsBean == null) return;
                code = picsBean.getCode();
                if(code == 1) {
                    final List<ShopRoomPicsBean.MessageBean> messageBeens = picsBean.getMessage();
                    if(messageBeens == null) return;
                    AutoPagerAdapter pagerAdapter = new AutoPagerAdapter(messageBeens);
                    mViewPager.setAdapter(pagerAdapter);
                    mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                            Log.i("position", "viewPager onPageScrolled position:" + position + " " + positionOffset + " " + positionOffsetPixels);
                            if(positionOffset == 0 && positionOffsetPixels == 0) {
                                mPicNum.setText((position + 1) + "/" + messageBeens.size());
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
        private List<ShopRoomPicsBean.MessageBean> mMessageBeens;

        public AutoPagerAdapter(List<ShopRoomPicsBean.MessageBean> messageBeens) {
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

    class RoomRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        List<ShopRoomInfos.MessageBean.ShopRoomFacilityVosBean> mShopRoomFacilityVos;
        public RoomRecyclerViewAdapter(List<ShopRoomInfos.MessageBean.ShopRoomFacilityVosBean> shopRoomFacilityVos) {
            mShopRoomFacilityVos = shopRoomFacilityVos;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.room_item, parent, false);
            return new RoomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            RoomViewHolder viewHolder = (RoomViewHolder)holder;
            viewHolder.itemName.setText(mShopRoomFacilityVos.get(position).getFacilityName());
        }

        @Override
        public int getItemCount() {
            return mShopRoomFacilityVos == null ? 0 : mShopRoomFacilityVos.size();
        }
    }

    class RoomNoticeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        List<ShopRoomInfos.MessageBean.ShopRoomNoticeVosBean> mShopRoomNoticeVosBeans;
        public RoomNoticeRecyclerViewAdapter(List<ShopRoomInfos.MessageBean.ShopRoomNoticeVosBean> shopRoomNoticeVosBeans) {
            mShopRoomNoticeVosBeans = shopRoomNoticeVosBeans;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.room_notice_item, parent, false);
            return new RoomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            RoomViewHolder viewHolder = (RoomViewHolder)holder;
            viewHolder.itemName.setText(mShopRoomNoticeVosBeans.get(position).getNotice());
        }

        @Override
        public int getItemCount() {
            return mShopRoomNoticeVosBeans == null ? 0 : mShopRoomNoticeVosBeans.size();
        }
    }

    class RoomViewHolder extends RecyclerView.ViewHolder {
        public TextView itemName;
        public RoomViewHolder(View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
