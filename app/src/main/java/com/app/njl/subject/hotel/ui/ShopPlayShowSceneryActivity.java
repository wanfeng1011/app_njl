package com.app.njl.subject.hotel.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.njl.R;
import com.app.njl.subject.mine.nohttp.CallServer;
import com.app.njl.subject.mine.nohttp.Constants;
import com.app.njl.subject.mine.nohttp.HttpConstants;
import com.app.njl.subject.mine.nohttp.HttpListener;
import com.app.njl.subject.mine.nohttp.StringRequestImpl;
import com.app.njl.ui.loopviewpager.AutoLoopViewPager;
import com.app.njl.utils.SharedPreferences;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.Response;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.app.njl.R.id.live_time_tv;
import static com.app.njl.R.id.stay_time_tv;

/**
 * 景点介绍
 * Created by jiaxx on 2016/4/16 0016.
 */
public class ShopPlayShowSceneryActivity extends AppCompatActivity implements View.OnClickListener, HttpListener<String> {
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
        setContentView(R.layout.shop_play_scenery_detail_layout);
        ButterKnife.bind(this);
        initView();
    }

    protected void initView() {
        setStayLiveTime();
        setSupportActionBar(mToolBar);
        addShoppingCartTv.setOnClickListener(this);
        buyNowTv.setOnClickListener(this);
        int mResortId = getIntent().getIntExtra("resortId", 1);
        Log.i("mResortId", "resortId:" + mResortId);
        queryShopPlayTicketPicsByOptions(mResortId);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    @Override
    public void onClick(View v) {

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

    public void queryShopPlayTicketPicsByOptions(int resortId) {
        Request<String> request = new StringRequestImpl(Constants.BASE_PATH + "shop/ticketResortPic", RequestMethod.POST);
        request.add("resortId", resortId);
        CallServer.getRequestInstance().add(this, HttpConstants.QUERY_SHOPPLAY_TICKETMESSAGE_PIC_BYOPTIONS, request, this, true, true);
    }

    public void queryShopPlayTicketByOptions(int resortId) {
        Request<String> request = new StringRequestImpl(Constants.BASE_PATH + "shop/ticketResortPic", RequestMethod.POST);
        request.add("resortId", resortId);
        CallServer.getRequestInstance().add(this, HttpConstants.QUERY_SHOPPLAY_TICKETMESSAGE_PIC_BYOPTIONS, request, this, true, true);
    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        int code = 0;
        String result = response.get();
        switch (what) {
            case HttpConstants.QUERY_SHOPPLAY_TICKETMESSAGE_PIC_BYOPTIONS:
                Log.i("result", "result get shop ticket pics detail:" + result);
                /*if(result == null) return;
                ShopPlaysDetailData shopPlaysDetailData = JsonEasy.toObject(result, ShopPlaysDetailData.class);
                if(shopPlaysDetailData == null) return;
                code = shopPlaysDetailData.getCode();
                if(code == 1) {

                }*/
                break;
        }
    }

    @Override
    public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {

    }
}
