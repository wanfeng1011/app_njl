package com.app.njl.subject.hotel.ui.fragment;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.app.njl.R;
import com.app.njl.activity.MainActivity;
import com.app.njl.subject.hotel.model.entity.landmark.LandMark;
import com.app.njl.subject.hotel.model.entity.landmark.SceneryLandMarkBean;
import com.app.njl.subject.hotel.model.entity.shoplist.ShopCommonMessage;
import com.app.njl.subject.hotel.model.entity.shoplist.StoreListStayBean;
import com.app.njl.subject.hotel.presenter.interfaces.IShopListStayQueryPresenter;
import com.app.njl.subject.hotel.ui.fragment.base.StoreListBaseFragment;
import com.app.njl.subject.hotel.view.CommonView;
import com.app.njl.subject.mine.nohttp.CallServer;
import com.app.njl.subject.mine.nohttp.Constants;
import com.app.njl.subject.mine.nohttp.HttpConstants;
import com.app.njl.subject.mine.nohttp.HttpListener;
import com.app.njl.subject.mine.nohttp.StringRequestImpl;
import com.app.njl.ui.loadmore.LoadMoreListView;
import com.app.njl.ui.quickadapter.BaseAdapterHelper;
import com.app.njl.ui.quickadapter.QuickAdapter;
import com.app.njl.utils.JsonEasy;
import com.app.njl.utils.SharedPreferences;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class ShopListStayFragment extends StoreListBaseFragment implements CommonView<StoreListStayBean.MessageBean.ResultBean>, HttpListener<String> {
    private Activity context;

    @Bind(R.id.rotate_header_list_view_frame)
    PtrClassicFrameLayout mPtrFrame;
    @Bind(R.id.listView)
    LoadMoreListView listView;
    QuickAdapter<StoreListStayBean.MessageBean.ResultBean> adapter;

    @Bind(R.id.ll_content_list_view)
    LinearLayout ll_content_list_view;
    //推荐
    @Bind(R.id.ll_recommend)
    LinearLayout mRecommendLayout;
    @Bind(R.id.tv_recommend)
    TextView mRecommendTv;
    @Bind(R.id.iv_recommend_arrow)
    ImageView mRecommendImgArrow;
    String mRecommendLists[] = new String[] {"低到高", "高到低"};
    QuickAdapter<String> reCommendAdapter;

    //好评率
    @Bind(R.id.ll_good_first)
    LinearLayout mGoodCommLayout;
    @Bind(R.id.tv_good_first)
    TextView mGoodCommTv;
    @Bind(R.id.iv_good_first_arrow)
    ImageView mGoodCommImgArrow;
    String mGoodCommLists[] = new String[] {"低到高", "高到低"};
    QuickAdapter<String> goodCommAdapter;

    //价格
    @Bind(R.id.ll_price)
    LinearLayout mPriceLayout;
    @Bind(R.id.tv_price)
    TextView mPriceTv;
    @Bind(R.id.iv_price_arrow)
    ImageView mPriceImgArrow;
    String mPriceLists[] = new String[] {"低到高", "高到低"};
    QuickAdapter<String> priceAdapter;

    //位置
    @Bind(R.id.ll_filter)
    LinearLayout mFilterLayout;
    @Bind(R.id.tv_filter)
    TextView mFilterTv;
    @Bind(R.id.iv_filter_arrow)
    ImageView mFilterArrow;
    QuickAdapter<LandMark> filteAdapter;
    List<LandMark> mFilterLists;

    @Bind(R.id.view_mask_bg)
    View view_mask_bg;

    int panelHeight;


    //下拉列表
    @Bind(R.id.filter_list_view)
    ListView filterListView;

//    private String contents[] = new String[] {"离我最近（当玩家不在景区时显示）", "景区东大门", "景区南大门", "景区西大门"};

    //Presenter
    IShopListStayQueryPresenter mPresenter;
    private List<StoreListStayBean.MessageBean.ResultBean> lists = new ArrayList<>();

    private int mSceneryId;
    private int mPageNo = 1;
    private int mPageSize = 10;
    private int mTotalPage;
    private int flag = 0; //条件类型

    private int mSort = 0; //0：表示按照默认排序1： 按照推荐排序2：按照好评数排序3：价格低价排序
    private int mSortOrder = 0; //0 表示升序 1表示降序
    private int mLandMarkId = -1; //地标id

    public ShopListStayFragment() {

    }

    public ShopListStayFragment(int sceneryId) {
        this.mSceneryId = sceneryId;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.shopliststay_fragment_layout;
    }

    @Override
    public void initView() {
        //设置界面主数据
        context = getActivity();
        adapter = new QuickAdapter<StoreListStayBean.MessageBean.ResultBean>(context, R.layout.shopliststay_fragment_item) {
            @Override
            protected void convert(BaseAdapterHelper helper, StoreListStayBean.MessageBean.ResultBean shop) {
                String typeStr = "";
                String positionStr = "";
                if(mSort == 1) {
                    typeStr = shop.getRecommendNum() + "推荐";
                }else {
                    typeStr = shop.getGoodCommentNum() + "好评/" + shop.getTotalCommentNum() + "评论";
                }
                if(!mFilterTv.getText().equals("位置筛选") && !mFilterTv.getText().equals("不限")) {
                    positionStr = shop.getAddressDistinct() + "-" + mFilterTv.getText();
                }else {
                    positionStr = shop.getAddressDistinct();
                }
                helper.setText(R.id.name, shop.getShopName())
                        .setText(R.id.comment, typeStr)
                        .setText(R.id.position, positionStr)
                        .setText(R.id.state, "预定动态")
                        .setText(R.id.price, "￥" + shop.getCheapestPrice() + "起")
                        .setImageUrl(R.id.logo, shop.getShopPic()); // 自动异步加载图片
            }
        };
        listView.setDrawingCacheEnabled(true);
        listView.setAdapter(adapter);

        /** header custom begin **/
        View view = new View(context);
        mPtrFrame.setHeaderView(view);
        /** header custom end **/
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initLocalData() {
        //mPresenter = new ShopListStayQueryPresenterImpl();
        //设置推荐数据到listview
        reCommendAdapter = new QuickAdapter<String>(context, R.layout.filter_listview_item) {
            @Override
            protected void convert(BaseAdapterHelper helper, String item) {
                helper.setText(R.id.filter_item_content, item);
            }
        };

        //设置好评率数据到listview
        goodCommAdapter = new QuickAdapter<String>(context, R.layout.filter_listview_item) {
            @Override
            protected void convert(BaseAdapterHelper helper, String item) {
                helper.setText(R.id.filter_item_content, item);
            }
        };

        //设置价格数据到listview
        priceAdapter = new QuickAdapter<String>(context, R.layout.filter_listview_item) {
            @Override
            protected void convert(BaseAdapterHelper helper, String item) {
                helper.setText(R.id.filter_item_content, item);
            }
        };

        //设置位置数据到listview
        filteAdapter = new QuickAdapter<LandMark>(context, R.layout.filter_listview_item) {
            @Override
            protected void convert(BaseAdapterHelper helper, LandMark item) {
                helper.setText(R.id.filter_item_content, item.getLandmarkName());
            }
        };
    }

    @Override
    public void initRemoteData() {
//        mPresenter.attachView(this);
//        mPresenter.queryShopListStay();
//        queryStoresByOptions();
    }

    @Override
    public void setListener() {
        // 下拉刷新
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
//                initRemoteData();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });

        // 加载更多
        listView.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
//                mPageNo++;
//                initRemoteData();
                mPageNo++;
                queryStoresByOptions(null);
            }
        });

        // 点击事件
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (lists.size() > 1) {
                    Intent intent = new Intent(getActivity(), ShopDetailPagerActivity.class);
                    ShopCommonMessage commonMessage = new ShopCommonMessage();
                    commonMessage.setShopId(lists.get(i).getShopId());
                    commonMessage.setShopName(lists.get(i).getShopName());
                    commonMessage.setLocation(new ShopCommonMessage.Location(lists.get(i).getLocation().getLon(), lists.get(i).getLocation().getLat()));
                    commonMessage.setLandmarkId(lists.get(i).getLandmarkId());
                    commonMessage.setTotalCommentNum(lists.get(i).getTotalCommentNum());
                    commonMessage.setGoodCommentNum(lists.get(i).getGoodCommentNum());
                    commonMessage.setRecommendNum(lists.get(i).getRecommendNum());
                    commonMessage.setDefaultSort(lists.get(i).getShopId());
                    commonMessage.setAddressDetail(lists.get(i).getAddressDetail());
                    commonMessage.setAddressCity(lists.get(i).getAddressCity());
                    commonMessage.setAddressProvince(lists.get(i).getAddressProvince());
                    commonMessage.setAddressDistinct(lists.get(i).getAddressDistinct());
                    intent.putExtra("shopCommonMessage", commonMessage);
                    intent.putExtra("type", 0);
                    getActivity().startActivity(intent);
                }
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                    Picasso.with(context).pauseTag(context);
                } else {
                    Picasso.with(context).resumeTag(context);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
            }
        });

        mRecommendLayout.setOnClickListener(this);
        mGoodCommLayout.setOnClickListener(this);
        mPriceLayout.setOnClickListener(this);
        mFilterLayout.setOnClickListener(this);

        filterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                KLog.i("filterListView click----------");
                mPageNo = 1;
                if(flag == 1) {
                    mSort = 1;
                    if(position == 0) {
                        mSortOrder = 0;
                        mRecommendTv.setText("推荐升序");
                    }else if(position == 1) {
                        mSortOrder = 1;
                        mRecommendTv.setText("推荐降序");
                    }
                    //还原除位置的其他设置
                    mGoodCommTv.setText("好评排序");
                    mPriceTv.setText("价格排序");
                }else if(flag == 2) {
                    mSort = 2;
                    if(position == 0) {
                        mSortOrder = 0;
                        mGoodCommTv.setText("好评升序");
                    }else if(position == 1) {
                        mSortOrder = 1;
                        mGoodCommTv.setText("好评降序");
                    }
                    //还原除位置的其他设置
                    mRecommendTv.setText("推荐排序");
                    mPriceTv.setText("价格排序");
                }else if(flag == 3) {
                    mSort = 3;
                    if(position == 0) {
                        mSortOrder = 0;
                        mPriceTv.setText("价格升序");
                    }else if(position == 1) {
                        mSortOrder = 1;
                        mPriceTv.setText("价格降序");
                    }
                    //还原除位置的其他设置
                    mRecommendTv.setText("推荐排序");
                    mGoodCommTv.setText("好评排序");
                }else if(flag == 4) {
                    mLandMarkId = mFilterLists.get(position).getLandmarkId();
                    mFilterTv.setText(mFilterLists.get(position).getLandmarkName());
                    mFilterArrow.setImageResource(R.mipmap.home_down_arrow);
                }
                //搜索店家
                queryStoresByOptions(null);
                hide();
            }
        });

        view_mask_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KLog.i("view_mask_bg click----------");
                resetFilterStatus();
                mFilterArrow.setImageResource(R.mipmap.home_down_arrow);
                view_mask_bg.setVisibility(View.GONE);
                ObjectAnimator.ofFloat(ll_content_list_view, "translationY", 0, -panelHeight).setDuration(200).start();
            }
        });
    }

    private void show() {
        view_mask_bg.setVisibility(View.VISIBLE);
        ll_content_list_view.setVisibility(View.VISIBLE);
        ll_content_list_view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ll_content_list_view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                panelHeight = ll_content_list_view.getHeight();
                ObjectAnimator.ofFloat(ll_content_list_view, "translationY", -panelHeight, 0).setDuration(200).start();
            }
        });
    }

    private void hide() {
        resetFilterStatus();
        flag = 0;
        panelHeight = ll_content_list_view.getHeight();
        view_mask_bg.setVisibility(View.GONE);
        ObjectAnimator.ofFloat(ll_content_list_view, "translationY", 0, -panelHeight).setDuration(200).start();
    }

    // 设置推荐筛选数据
    private void setRecommendSelect() {
        mRecommendTv.setTextColor(getContext().getResources().getColor(R.color.orange));
        mRecommendImgArrow.setImageResource(R.mipmap.home_up_arrow);
    }

    // 设置好评率筛选数据
    private void setGoodCommSelect() {
        mGoodCommTv.setTextColor(getContext().getResources().getColor(R.color.orange));
        mGoodCommImgArrow.setImageResource(R.mipmap.home_up_arrow);
    }

    // 设置价格筛选数据
    private void setPriceSelect() {
        mPriceTv.setTextColor(getContext().getResources().getColor(R.color.orange));
        mPriceImgArrow.setImageResource(R.mipmap.home_up_arrow);
    }

    // 设置位置筛选数据
    private void setFilterSelect() {
        mFilterTv.setTextColor(getContext().getResources().getColor(R.color.orange));
        mFilterArrow.setImageResource(R.mipmap.home_up_arrow);
    }

    // 复位筛选的显示状态
    public void resetFilterStatus() {
        mRecommendTv.setTextColor(getContext().getResources().getColor(R.color.green_light));
        mRecommendImgArrow.setImageResource(R.mipmap.home_down_arrow);
        mGoodCommTv.setTextColor(getContext().getResources().getColor(R.color.green_light));
        mGoodCommImgArrow.setImageResource(R.mipmap.home_down_arrow);
        mPriceTv.setTextColor(getContext().getResources().getColor(R.color.green_light));
        mPriceImgArrow.setImageResource(R.mipmap.home_down_arrow);
        mFilterTv.setTextColor(getContext().getResources().getColor(R.color.green_light));
        mFilterArrow.setImageResource(R.mipmap.home_down_arrow);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_recommend:
                flag = 1;
                reCommendAdapter.clearAll();
                reCommendAdapter.addAll(Arrays.asList(mRecommendLists));
                filterListView.setAdapter(reCommendAdapter);
                resetFilterStatus();
                setRecommendSelect();
                if(view_mask_bg.isShown()) return;
                show();
                break;
            case R.id.ll_good_first:
                flag = 2;
                goodCommAdapter.clearAll();
                goodCommAdapter.addAll(Arrays.asList(mGoodCommLists));
                filterListView.setAdapter(goodCommAdapter);
                resetFilterStatus();
                setGoodCommSelect();
                if(view_mask_bg.isShown()) return;
                show();
                break;
            case R.id.ll_price:
                flag = 3;
                priceAdapter.clearAll();
                priceAdapter.addAll(Arrays.asList(mPriceLists));
                filterListView.setAdapter(priceAdapter);
                resetFilterStatus();
                setPriceSelect();
                if(view_mask_bg.isShown()) return;
                show();
                break;
            case R.id.ll_filter:
                flag = 4;
                if(mFilterLists != null) {
                    filteAdapter.clearAll();
                    filteAdapter.addAll(mFilterLists);
                    filterListView.setAdapter(filteAdapter);
                    resetFilterStatus();
                    setFilterSelect();
                    if(view_mask_bg.isShown()) return;
                    show();
                }else {
                    //到远程服务器加载位置筛选的数据
                    initLandMarkFilter(mSceneryId);
                }
                break;
        }
    }

    /**
     * 跳转到fragment界面
     */
    private void replaceFragment(String name, int ShopId) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new ShopDetailPagerFragment();
        for (int j = 0; j < MainActivity.fragmentTags.size(); j++) {
            Fragment f = fragmentManager.findFragmentByTag(MainActivity.fragmentTags.get(j));
            if(f != null && f.isAdded()) {
                fragmentTransaction.hide(ShopListStayFragment.this);
            }
        }
        if (fragment.isAdded()) {
            fragmentTransaction.show(fragment);
        } else {
            if (!MainActivity.fragmentTags.contains("ShopDetailPagerFragment")) {
                MainActivity.fragmentTags.add("ShopDetailPagerFragment");
            }
            Bundle bundle = new Bundle();
            bundle.putString("title", name);
            bundle.putInt("shopId", ShopId);
            fragment.setArguments(bundle);
            fragmentTransaction.add(R.id.fragment_container, fragment, "ShopDetailPagerFragment");
        }
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("result", "result lists4:");
        queryStoresByOptions(null);
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
//        mPresenter.detachView();
    }

    @Override
    public void loadSuccess(List<StoreListStayBean.MessageBean.ResultBean> stores) {
        lists.addAll(stores);
        listView.updateLoadMoreViewText(stores);
        listView.onLoadMoreComplete();
        adapter.addAll(stores);
    }

    @Override
    public void loadFailed() {
        mPtrFrame.refreshComplete();
        listView.setLoadMoreViewTextError();
    }

    @Override
    public void loadCompleted() {
        mPtrFrame.refreshComplete();
    }

    @Override
    public void showToast(String showMessage) {

    }

    private void initLandMarkFilter(int sceneryId) {
        Log.i("sceneryId", "initLandMarkFilter mSceneryId:" + mSceneryId);
        Request<String> request = new StringRequestImpl(Constants.BASE_PATH + "resort/getResortLandmark", RequestMethod.POST);
        request.add("resortId", mSceneryId);
        request.add("page", 1);
        request.add("pageSize", 10);
        CallServer.getRequestInstance().add(getContext(), HttpConstants.QUERY_STORE_LANDMARKS, request, this, true, true);
    }

    @Override
    public void queryStoresByOptions(String shopName) {
        String startDate = SharedPreferences.getInstance().getInt("live_in_year", 0) + "-" + SharedPreferences.getInstance().getInt("live_in_month", 0) + "-" + SharedPreferences.getInstance().getInt("live_in_day", 0);
        String endDate = SharedPreferences.getInstance().getInt("live_out_year", 0) + "-" + SharedPreferences.getInstance().getInt("live_out_month", 0) + "-" + SharedPreferences.getInstance().getInt("live_out_day", 0);
        Request<String> request = new StringRequestImpl(Constants.BASE_PATH + "shopList/listHotel", RequestMethod.POST);
        request.add("resortId", mSceneryId);
        request.add("sort", mSort);
        request.add("sortOrder", mSortOrder);
        request.add("page", 1);
        request.add("pageSize", mPageSize);
        request.add("startDate", startDate);
        request.add("endDate", endDate);
        if(mLandMarkId != -1)
            request.add("landmarkId", mLandMarkId);
        if(!TextUtils.isEmpty(shopName))
            request.add("shopName", shopName);
        Log.i("sceneryId", "mSceneryId:" + mSceneryId + " " + mSort + " " + mSortOrder + " " + mPageNo + " " + mPageSize + " " + startDate + " " + endDate + " " + mLandMarkId + " " + shopName);
        CallServer.getRequestInstance().add(getContext(), HttpConstants.QUERY_STORES_BYOPTIONS, request, this, true, true);
    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        int code = 0;
        String result = response.get();
        switch (what) {
            case HttpConstants.QUERY_STORES_BYOPTIONS:
                Log.i("result", "result get store list:" + result);
                StoreListStayBean bean = JsonEasy.toObject(result, StoreListStayBean.class);
                if(bean == null) return;
                Log.i("result", "result get store list bean2:" + bean.getMessage().getTotalNumber() + " " + bean.getMessage().getPageSize() + " " + bean.getMessage().getPageNum());
                code = bean.getCode();
                if(code == 1) {
                    if(bean.getMessage() == null) return;
                    mTotalPage = bean.getMessage().getTotalNumber();
                    List<StoreListStayBean.MessageBean.ResultBean> shops = bean.getMessage().getResult();
                    Log.i("result", "result lists:" + lists.toString());
                    if(mPageNo == 1) {
                        lists.clear();
                        adapter.clearAll();
                    }
                    if(listView != null) {
                        if(lists == null)
                            lists = new ArrayList<>();
                        lists.addAll(shops);
                        listView.updateLoadMoreViewText(shops);
                        listView.onLoadMoreComplete();
                        adapter.addAll(shops);
                    }
                }
                break;
            case HttpConstants.QUERY_STORE_LANDMARKS:
                Log.i("result", "result get store landmarks:" + result);
                final SceneryLandMarkBean markBean = JsonEasy.toObject(result, SceneryLandMarkBean.class);
                if(markBean == null) return;
                code = markBean.getCode();
                if(code == 1) {
                    mFilterLists = markBean.getMessage().getResult();
                    //设置不限
                    LandMark landMark = new LandMark();
                    landMark.setLandmarkId(-1);
                    landMark.setLandmarkName("不限");
                    mFilterLists.add(landMark);
                    filteAdapter.addAll(mFilterLists);
                    filterListView.setAdapter(filteAdapter);
                    KLog.i("ll_category click----------");
                    resetFilterStatus();
                    setFilterSelect();
                    show();
                }
                break;
        }
    }

    @Override
    public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {

    }

}
