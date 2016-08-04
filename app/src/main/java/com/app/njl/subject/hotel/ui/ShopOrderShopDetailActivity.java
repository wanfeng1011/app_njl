package com.app.njl.subject.hotel.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.njl.R;
import com.app.njl.subject.hotel.model.entity.shopdetails.ShopCourseBean;
import com.app.njl.subject.hotel.model.entity.shopdetails.ShopCoursePicsBean;
import com.app.njl.subject.hotel.model.entity.shopdetails.ShopOrdersDetailData;
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
 * Created by jiaxx on 2016/4/16 0016.
 */
public class ShopOrderShopDetailActivity extends AppCompatActivity implements View.OnClickListener, HttpListener<String> {
    /*@Bind(R.id.cancelTv)
    TextView cancelTv; //取消
    @Bind(R.id.sureTv)
    TextView sureTv; //确认*/
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
    private ShopOrdersDetailData.MessageBean mMessageBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_order_detail_layout);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setStayLiveTime();
        setSupportActionBar(mToolBar);
        Intent intent = getIntent();
        mMessageBean = (ShopOrdersDetailData.MessageBean)intent.getSerializableExtra("messageBean");
        setTitle(mMessageBean.getCourseName());
        /*mRoomName.setText("房间设施");
        mRoomPrice.setText("￥" + mMessageBean.getPrice() + "起/晚");*/
        queryShopDetailLivesPicByOptions(mMessageBean.getShopId(), mMessageBean.getCourseId());
        queryShopDetailLivesByOptions(mMessageBean.getShopId(), mMessageBean.getCourseId());
    }

    private void initCourseViewData(ShopCourseBean.MessageBean messageBean) {
        if(messageBean == null) return;
        ((TextView)findViewById(R.id.courseTast)).setText(messageBean.getTaste() + "");
        if(messageBean.getConsist() != null)
            ((TextView)findViewById(R.id.courseKind)).setText(messageBean.getConsist());
        if(messageBean.getNote() != null)
            ((TextView)findViewById(R.id.courseNote)).setText(messageBean.getNote());
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    @Override
    public void onClick(View v) {

    }

    public void queryShopDetailLivesPicByOptions(int shopId, int courseId) {
        Request<String> request = new StringRequestImpl(Constants.BASE_PATH + "shop/coursePic", RequestMethod.POST);
        request.add("shopId", shopId);
        request.add("courseId", courseId);
        CallServer.getRequestInstance().add(this, HttpConstants.QUERY_SHOPCOURSEPICS_BYOPTIONS, request, this, true, true);
    }

    public void queryShopDetailLivesByOptions(int shopId, int courseId) {
        Request<String> request = new StringRequestImpl(Constants.BASE_PATH + "shop/courseInfo", RequestMethod.POST);
        request.add("shopId", shopId);
        request.add("courseId", courseId);
        CallServer.getRequestInstance().add(this, HttpConstants.QUERY_SHOPCOURSE_BYOPTIONS, request, this, true, true);
    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        int code = 0;
        String result = response.get();
        switch (what) {
            case HttpConstants.QUERY_SHOPCOURSE_BYOPTIONS:
                Log.i("result", "result get shop detail course:" + result);
                ShopCourseBean courseBean = JsonEasy.toObject(result, ShopCourseBean.class);
                if(courseBean == null) return;
                code = courseBean.getCode();
                if(code == 1) {
                    if(courseBean.getMessage() == null) return;
                    List<ShopCourseBean.MessageBean> messageBeens = null;
                    if((messageBeens = courseBean.getMessage()) != null) {
                        initCourseViewData(messageBeens.get(0));
                    }
                }
                break;
            case HttpConstants.QUERY_SHOPCOURSEPICS_BYOPTIONS:
                Log.i("result", "result get shop detail course pic:" + result);
                ShopCoursePicsBean picsBean = JsonEasy.toObject(result, ShopCoursePicsBean.class);
                if(picsBean == null) return;
                code = picsBean.getCode();
                if(code == 1) {
                    final List<ShopCoursePicsBean.MessageBean> messageBeens = picsBean.getMessage();
                    if(messageBeens == null) return;
                    ShopOrderShopDetailActivity.AutoPagerAdapter pagerAdapter = new ShopOrderShopDetailActivity.AutoPagerAdapter(messageBeens);
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
        private List<ShopCoursePicsBean.MessageBean> mMessageBeens;

        public AutoPagerAdapter(List<ShopCoursePicsBean.MessageBean> messageBeens) {
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
}
