package com.app.njl.subject.hotel.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.njl.R;
import com.app.njl.base.BaseFragment;
import com.app.njl.subject.hotel.model.entity.shopstaydetail.ShopStaysDetailData;
import com.app.njl.subject.hotel.presenter.interfaces.IShopDetailStayQueryPresenter;
import com.app.njl.subject.hotel.ui.ShopStayShowDetailActivity;
import com.app.njl.subject.hotel.view.CommonView;
import com.app.njl.subject.mine.nohttp.CallServer;
import com.app.njl.subject.mine.nohttp.Constants;
import com.app.njl.subject.mine.nohttp.HttpConstants;
import com.app.njl.subject.mine.nohttp.HttpListener;
import com.app.njl.subject.mine.nohttp.StringRequestImpl;
import com.app.njl.ui.quickadapter.QuickAdapter;
import com.app.njl.utils.JsonEasy;
import com.app.njl.utils.SharedPreferences;
import com.squareup.picasso.Picasso;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.Response;

import java.util.List;

import butterknife.Bind;

public class ShopDetailStayFragment extends BaseFragment implements CommonView<ShopStaysDetailData.MessageBean>, HttpListener<String> {
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private ShopDetailPagerActivity context;
    private QuickAdapter<ShopStaysDetailData.MessageBean> adapter;
    private IShopDetailStayQueryPresenter shopDetailStayQueryPresenter;
    private int mShopId;
    private ShopDetailStayRecyclerAdapter mAdapter;
    private List<ShopStaysDetailData.MessageBean> beans;

    @Override
    public int getLayoutRes() {
        context = (ShopDetailPagerActivity) getActivity();
        return R.layout.shop_detail_stay_fragment;
    }

    @Override
    public void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void initPresenter() {
//        shopDetailStayQueryPresenter = new ShopDetailStayQueryPresenterImpl();
    }

    @Override
    public void initLocalData() {
//        loadData();
    }

    @Override
    public void initRemoteData() {
//        loadData();
        mShopId = context.getmShopId();
        queryShopDetailLivesByOptions(mShopId);
    }

    @Override
    public void setListener() {

    }

    /**
     * 加载网络数据
     */
    private void loadData() {
//        shopDetailStayQueryPresenter.attachView(this);
//        shopDetailStayQueryPresenter.queryShopDetailStay();
    }

    public void queryShopDetailLivesByOptions(int shopId) {
        String startDate = SharedPreferences.getInstance().getInt("live_in_year", 0) + "-" + SharedPreferences.getInstance().getInt("live_in_month", 0) + "-" + SharedPreferences.getInstance().getInt("live_in_day", 0);
        String endDate = SharedPreferences.getInstance().getInt("live_out_year", 0) + "-" + SharedPreferences.getInstance().getInt("live_out_month", 0) + "-" + SharedPreferences.getInstance().getInt("live_out_day", 0);
        Request<String> request = new StringRequestImpl(Constants.BASE_PATH + "shop/live", RequestMethod.POST);
        request.add("shopId", shopId);
        request.add("dateStart", startDate);
        request.add("dateEnd", endDate);
        Log.i("sceneryId", "shopId:" + shopId + " startData:" + startDate + " endDate:" + endDate);
        CallServer.getRequestInstance().add(getContext(), HttpConstants.QUERY_SHOPDETAIL_BYOPTIONS, request, this, true, true);
    }

    @Override
    public void onResume() {
        super.onResume();
        Picasso.with(context).resumeTag(context);
    }

    @Override
    public void onPause() {
        super.onPause();
        Picasso.with(context).pauseTag(context);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Picasso.with(context).cancelTag(context);
//        shopDetailStayQueryPresenter.detachView();
    }

    @Override
    public void loadSuccess(List<ShopStaysDetailData.MessageBean> fruits) {
        adapter.addAll(fruits);
    }

    @Override
    public void loadFailed() {

    }

    @Override
    public void loadCompleted() {

    }

    @Override
    public void showToast(String showMessage) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        int code = 0;
        String result = response.get();
        switch (what) {
            case HttpConstants.QUERY_SHOPDETAIL_BYOPTIONS:
                Log.i("result", "result get shop lives:" + result);
                ShopStaysDetailData detailData = JsonEasy.toObject(result, ShopStaysDetailData.class);
                if(detailData == null) return;
                code = detailData.getCode();
                if(code == 1) {
                    beans = detailData.getMessage();
                    if(mAdapter == null)
                        mAdapter = new ShopDetailStayRecyclerAdapter();
                    mRecyclerView.setAdapter(mAdapter);
                }
                break;
        }
    }

    @Override
    public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {

    }

    class ShopDetailStayRecyclerAdapter extends RecyclerView.Adapter<ShopDetailStayViewHold> {

        @Override
        public ShopDetailStayViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_shopdetail_item, parent, false);
            return new ShopDetailStayViewHold(view);
        }

        @Override
        public void onBindViewHolder(ShopDetailStayViewHold holder, final int position) {
            holder.roomName.setText(beans.get(position).getRoomName());
            String roomSpecialty = "";
            if(beans.get(position).getRoomSquare() != null) {
                roomSpecialty += beans.get(position).getRoomSquare() + "m2 ";
            }
            if(beans.get(position).getIsWindow() == 0) {
                roomSpecialty += "无窗 ";
            }else {
                roomSpecialty += "有窗 ";
            }
            holder.roomContent.setText(roomSpecialty);
            holder.roomPrice.setText("￥" + beans.get(position).getPrice());
            Picasso.with(context).load(beans.get(position).getPicUrl())
                    .placeholder(R.mipmap.default_image)
                    .error(R.mipmap.default_image)
                    .tag(context)
                    .into(holder.roomPic);
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), ShopStayShowDetailActivity.class);
                    intent.putExtra("messageBean", beans.get(position));
                    getContext().startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                }
            });
        }

        @Override
        public int getItemCount() {
            return beans == null ? 0 : beans.size();
        }
    }

    class ShopDetailStayViewHold extends RecyclerView.ViewHolder {
        public View view;
        public TextView roomName;
        public TextView roomContent;
        public TextView roomPrice;
        public ImageView roomPic;
        public ShopDetailStayViewHold(View itemView) {
            super(itemView);
            this.view = itemView.findViewById(R.id.item_view);
            this.roomName = (TextView)itemView.findViewById(R.id.name);
            this.roomContent = (TextView)itemView.findViewById(R.id.content);
            this.roomPrice = (TextView)itemView.findViewById(R.id.price);
            this.roomPic = (ImageView)itemView.findViewById(R.id.img_detail);
        }
    }

}
