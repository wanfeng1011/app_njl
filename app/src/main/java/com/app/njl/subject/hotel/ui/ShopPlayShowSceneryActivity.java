package com.app.njl.subject.hotel.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.app.njl.R;
import com.app.njl.base.BaseActivity;
import com.app.njl.subject.hotel.model.entity.shopdetails.ShopPlaysDetailData;
import com.app.njl.subject.hotel.ui.fragment.ShopDetailPlayFragment;
import com.app.njl.subject.mine.nohttp.CallServer;
import com.app.njl.subject.mine.nohttp.Constants;
import com.app.njl.subject.mine.nohttp.HttpConstants;
import com.app.njl.subject.mine.nohttp.HttpListener;
import com.app.njl.subject.mine.nohttp.StringRequestImpl;
import com.app.njl.utils.JsonEasy;
import com.app.njl.utils.SharedPreferences;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.Response;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 景点介绍
 * Created by jiaxx on 2016/4/16 0016.
 */
public class ShopPlayShowSceneryActivity extends AppCompatActivity implements View.OnClickListener, HttpListener<String> {
    @Bind(R.id.playTimeTv)
    TextView playTimeTv; //游玩时间
    @Bind(R.id.shopPlaySceneryDetail_backTv)
    TextView backTv; //返回
    @Bind(R.id.shopPlaySceneryDetail_addShoppingCartTv)
    TextView addShoppingCartTv; //加入购物车
    @Bind(R.id.shopPlaySceneryDetail_buyNowTv)
    TextView buyNowTv; //立即购买

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_play_scenery_detail_layout);
        ButterKnife.bind(this);
        initView();
    }

    protected void initView() {
        setStayLiveTime();
        backTv.setOnClickListener(this);
        addShoppingCartTv.setOnClickListener(this);
        buyNowTv.setOnClickListener(this);
        int mResortId = getIntent().getIntExtra("resortId", 1);
        Log.i("mResortId", "resortId:" + mResortId);
        queryShopDetailLivesByOptions(mResortId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shopPlaySceneryDetail_backTv:
                finish();
                break;
        }
    }

    public void setStayLiveTime() {
//        String stay_in = SharedPreferences.getInstance().getString("live_in", "住店:");
        String live_out = SharedPreferences.getInstance().getString("live_out", "离店:");
        playTimeTv.setText("游玩时间：" + live_out);
    }

    public void queryShopDetailLivesByOptions(int resortId) {
        Request<String> request = new StringRequestImpl(Constants.BASE_PATH + "shop/ticketResortPic", RequestMethod.POST);
        request.add("resortId", resortId);
        CallServer.getRequestInstance().add(this, HttpConstants.QUERY_SHOPPLAY_TICKETMESSAGE_BYOPTIONS, request, this, true, true);
    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        Log.i("result", "result get shop ticket detail:" + response.get());
        int code = 0;
        String result = response.get();
        switch (what) {
            case HttpConstants.QUERY_SHOPPLAY_TICKETMESSAGE_BYOPTIONS:
                Log.i("result", "result get shop ticket detail:" + result);
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
