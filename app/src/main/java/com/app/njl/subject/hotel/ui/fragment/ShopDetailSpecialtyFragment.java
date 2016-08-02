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
import com.app.njl.subject.hotel.model.entity.Fruit;
import com.app.njl.subject.hotel.model.entity.shopdetails.ShopSpecialtyDetailData;
import com.app.njl.subject.hotel.presenter.interfaces.IShopDetailStayQueryPresenter;
import com.app.njl.subject.hotel.ui.ShopSpecialtyShowActivity;
import com.app.njl.subject.hotel.view.CommonView;
import com.app.njl.subject.mine.nohttp.CallServer;
import com.app.njl.subject.mine.nohttp.Constants;
import com.app.njl.subject.mine.nohttp.HttpConstants;
import com.app.njl.subject.mine.nohttp.HttpListener;
import com.app.njl.subject.mine.nohttp.StringRequestImpl;
import com.app.njl.ui.quickadapter.QuickAdapter;
import com.app.njl.utils.JsonEasy;
import com.bumptech.glide.Glide;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.Response;

import java.util.List;

import butterknife.Bind;

/**
 * 商家详情特产Fragment
 * Created by jiaxx on 2016/4/16 0016.
 */
public class ShopDetailSpecialtyFragment extends BaseFragment implements CommonView<Fruit>, HttpListener<String> {
    @Bind(R.id.specialty_recyclerView)
    RecyclerView mRecyclerView;
    QuickAdapter<Fruit> adapter;
    private ShopDetailPagerActivity mActivity;
    private IShopDetailStayQueryPresenter shopDetailStayQueryPresenter;
    private int mShopId = 1;
    private ShopDetailSpecialtyRecyclerAdapter mSpecialtyRecyclerAdapter;
    private List<ShopSpecialtyDetailData.MessageBean> mDatas;

    @Override
    public int getLayoutRes() {
        mActivity = (ShopDetailPagerActivity)getActivity();
        return R.layout.shopdetailspecialty_fragment_layout;
    }

    @Override
    public void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        /*adapter = new QuickAdapter<Fruit>(context, R.layout.layout_shopdetail_item) {
            @Override
            protected void convert(BaseAdapterHelper helper, Fruit shop) {
                helper.setText(R.id.name, shop.getName())
                        .setText(R.id.content, "介绍、规格")
                        .setText(R.id.price, "产" + shop.getPrice());
                //.setImageUrl(R.id.img_detail, shop.getUrl()); // 自动异步加载图片
            }
        };
        listView.setDrawingCacheEnabled(true);
        listView.setAdapter(adapter);
        // header custom begin
        final StoreHouseHeader header = new StoreHouseHeader(context);
        header.setPadding(0, DeviceUtil.dp2px(context, 15), 0, 0);
        header.initWithString("loading");
        header.setTextColor(getResources().getColor(R.color.gray));
        mPtrFrame.setHeaderView(header);
        mPtrFrame.addPtrUIHandler(header);*/
    }

    @Override
    public void initPresenter() {
//        shopDetailStayQueryPresenter = new ShopDetailStayQueryPresenterImpl();
    }

    @Override
    public void initLocalData() {

    }

    @Override
    public void initRemoteData() {
        loadData();
    }

    @Override
    public void setListener() {

    }

    @Override
    public void onResume() {
        super.onResume();
        Picasso.with(mActivity).pauseTag(mActivity);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Picasso.with(mActivity).cancelTag(mActivity);
//        shopDetailStayQueryPresenter.detachView();
    }

    /**
     * 加载网络数据
     */
    private void loadData() {
//        shopDetailStayQueryPresenter.attachView(this);
//        shopDetailStayQueryPresenter.queryShopDetailStay();
        mShopId = mActivity.getmShopId();
        queryShopDetailLivesByOptions(mShopId);
    }

    @Override
    public void loadSuccess(List<Fruit> fruits) {
//        listView.updateLoadMoreViewText(fruits);
//        adapter.addAll(fruits);
    }

    @Override
    public void loadFailed() {
//        mPtrFrame.refreshComplete();
//        listView.setLoadMoreViewTextError();
    }

    @Override
    public void loadCompleted() {
//        mPtrFrame.refreshComplete();
    }

    @Override
    public void showToast(String showMessage) {

    }

    @Override
    public void onClick(View v) {

    }

    public void queryShopDetailLivesByOptions(int shopId) {
        Request<String> request = new StringRequestImpl(Constants.BASE_PATH + "shop/specialty", RequestMethod.POST);
        request.add("shopId", shopId);
        CallServer.getRequestInstance().add(getContext(), HttpConstants.QUERY_SHOPSPECIALTY_DETAIL_BYOPTIONS, request, this, true, true);
    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        String result = response.get();
        int code = 0;
        switch (what) {
            case HttpConstants.QUERY_SHOPSPECIALTY_DETAIL_BYOPTIONS:
                Log.i("result", "result get shop specialty:" + result);
                KLog.i("result:" + result);
                if(result == null) return;
                ShopSpecialtyDetailData shopSpecialtyDetailData = JsonEasy.toObject(result, ShopSpecialtyDetailData.class);
                if(shopSpecialtyDetailData == null) return;
                code = shopSpecialtyDetailData.getCode();
                if(code == 1) {
                    mDatas = shopSpecialtyDetailData.getMessage();
                    mSpecialtyRecyclerAdapter = new ShopDetailSpecialtyRecyclerAdapter();
                    mRecyclerView.setAdapter(mSpecialtyRecyclerAdapter);
                }
                break;
        }
    }

    @Override
    public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {

    }

    class ShopDetailSpecialtyRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_shopdetail_item, parent, false);
            return new ShopDetailSpecialtyViewHold(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            ShopDetailSpecialtyViewHold viewHold = (ShopDetailSpecialtyViewHold)holder;
            viewHold.itemName.setText(mDatas.get(position).getSpecialtyComment());
            viewHold.itemContent.setText("重量：" + mDatas.get(position).getSpecialWeight());
            viewHold.itemPrice.setText("￥" + mDatas.get(position).getSpecialtyPrice());
            Glide.with(mActivity).load(mDatas.get(position).getSpecialtyPictureUrl())
                    .placeholder(R.mipmap.default_image)
                    .error(R.mipmap.default_image)
                    .into(viewHold.itemPic);
            /*Picasso.with(mActivity).load(mDatas.get(position).getSpecialtyPictureUrl())
                    .placeholder(R.mipmap.default_image)
                    .error(R.mipmap.default_image)
                    .tag(mActivity)
                    .into(viewHold.itemPic);*/
            viewHold.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), ShopSpecialtyShowActivity.class);
                    intent.putExtra("messageBean", mDatas.get(position));
                    getContext().startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDatas == null ? 0 : mDatas.size();
        }
    }

    class ShopDetailSpecialtyViewHold extends RecyclerView.ViewHolder {
        public View view;
        public TextView itemName;
        public TextView itemContent;
        public TextView itemPrice;
        public ImageView itemPic;
        public ShopDetailSpecialtyViewHold(View itemView) {
            super(itemView);
            this.view = itemView.findViewById(R.id.item_view);
            this.itemName = (TextView)itemView.findViewById(R.id.name);
            this.itemContent = (TextView)itemView.findViewById(R.id.content);
            this.itemPrice = (TextView)itemView.findViewById(R.id.price);
            this.itemPic = (ImageView)itemView.findViewById(R.id.img_detail);
        }
    }
}
