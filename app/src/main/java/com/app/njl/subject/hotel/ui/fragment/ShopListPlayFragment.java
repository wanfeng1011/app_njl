package com.app.njl.subject.hotel.ui.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import com.app.njl.base.BaseFragment;
import com.app.njl.subject.hotel.model.entity.landmark.LandMark;
import com.app.njl.subject.hotel.model.entity.landmark.SceneryLandMarkBean;
import com.app.njl.subject.hotel.model.entity.shoplist.Shop;
import com.app.njl.subject.hotel.model.entity.shoplist.ShopListBean;
import com.app.njl.subject.hotel.presenter.impl.ShopListStayQueryPresenterImpl;
import com.app.njl.subject.hotel.presenter.interfaces.IShopListStayQueryPresenter;
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

/**
 * Created by jiaxx on 2016/5/5 0005.
 */
public class ShopListPlayFragment extends BaseFragment implements CommonView<Shop>, View.OnClickListener, HttpListener<String> {
    private MainActivity context;

    @Bind(R.id.rotate_header_list_view_frame)
    PtrClassicFrameLayout mPtrFrame;
    @Bind(R.id.listView)
    LoadMoreListView listView;
    QuickAdapter<Shop> adapter;

    @Bind(R.id.ll_content_list_view)
    LinearLayout ll_content_list_view;

    @Bind(R.id.ll_price)
    LinearLayout ll_price;
    @Bind(R.id.tv_price)
    TextView tv_price;
    @Bind(R.id.iv_price_arrow)
    ImageView iv_price_arrow;
    @Bind(R.id.ll_filter)
    LinearLayout ll_filter;
    @Bind(R.id.tv_filter)
    TextView tv_filter;
    @Bind(R.id.iv_filter_arrow)
    ImageView iv_filter_arrow;

    @Bind(R.id.view_mask_bg)
    View view_mask_bg;

    int panelHeight;
//    boolean isShow = false;

    int flag = 0; //0 没有选择 1推荐 2好评 3低价 4位置

    QuickAdapter<String> priceAdapter;
    //位置筛选下拉列表
    @Bind(R.id.filter_list_view)
    ListView filterListView;
    QuickAdapter<LandMark> filteAdapter;
    List<LandMark> filterLists;
//    private String contents[] = new String[] {"离我最近（当玩家不在景区时显示）", "景区东大门", "景区南大门", "景区西大门"};

    //价格筛选
    private String playItems[] = new String[] {"门票低价优先", "导游低价优先", "自助游低价优先", "自助游低价优先"};

    //Presenter
    IShopListStayQueryPresenter mPresenter;
    private List<Shop> lists = new ArrayList<>();

    private int mSceneryId;
    private int mPageNo = 1;
    private int mPageSize = 10;
    private int mTotalPage;

    public ShopListPlayFragment() {

    }

    public ShopListPlayFragment(int sceneryId) {
        this.mSceneryId = sceneryId;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = (MainActivity) getActivity();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_play_list;
    }

    @Override
    public void initView() {
        context = (MainActivity) getActivity();
        adapter = new QuickAdapter<Shop>(context, R.layout.shoplistplay_list_item) {
            @Override
            protected void convert(BaseAdapterHelper helper, Shop shop) {
                helper.setText(R.id.name, shop.getShopName())
                        //.setText(R.id.favorable, shop.getContent())
                        //.setText(R.id.star, shop.getStar() + "分")
                        .setText(R.id.state, "预定动态")
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
        mPresenter = new ShopListStayQueryPresenterImpl();
    }

    @Override
    public void initRemoteData() {
//        mPresenter.attachView(this);
//        mPresenter.queryShopListStay();
        queryStoresByOptions();
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
                mPageNo++;
                initRemoteData();
            }
        });

        // 点击事件
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(lists.size()>1) {
                    replaceFragment(lists.get(i).getShopName());
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

        ll_filter.setOnClickListener(this);

        filterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                KLog.i("filterListView click----------");
                if (flag == 3)
                    tv_price.setText(playItems[position]);
                else if (flag == 4)
                    tv_filter.setText(filterLists.get(position).getLandmarkName());
                hide();
            }
        });

        view_mask_bg.setOnClickListener(this);

        ll_price.setOnClickListener(this);
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

    // 设置低价优先数据
    private void setPriceFilter() {
        tv_price.setTextColor(getContext().getResources().getColor(R.color.orange));
        iv_price_arrow.setImageResource(R.mipmap.home_up_arrow);
    }

    // 设置位置筛选数据
    private void setFilterFilter() {
        tv_filter.setTextColor(getContext().getResources().getColor(R.color.orange));
        iv_filter_arrow.setImageResource(R.mipmap.home_up_arrow);
    }

    // 复位筛选的显示状态
    public void resetFilterStatus() {
        tv_price.setTextColor(getContext().getResources().getColor(R.color.green_light));
        iv_price_arrow.setImageResource(R.mipmap.home_down_arrow);

        tv_filter.setTextColor(getContext().getResources().getColor(R.color.green_light));
        iv_filter_arrow.setImageResource(R.mipmap.home_down_arrow);
    }

    /**
     * 跳转到fragment界面
     */
    private void replaceFragment(String name) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new ShopDetailPagerFragment();
        for (int j = 0; j < MainActivity.fragmentTags.size(); j++) {
            Fragment f = fragmentManager.findFragmentByTag(MainActivity.fragmentTags.get(j));
            if(f != null && f.isAdded()) {
                fragmentTransaction.hide(ShopListPlayFragment.this);
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
        mPresenter.detachView();
    }

    @Override
    public void loadSuccess(List<Shop> fruits) {
        lists.addAll(fruits);
        listView.updateLoadMoreViewText(fruits);
        listView.onLoadMoreComplete();
        adapter.addAll(fruits);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_price:
                flag = 3;
                //设置数据到listview
                priceAdapter = new QuickAdapter<String>(context, R.layout.filter_listview_item) {
                    @Override
                    protected void convert(BaseAdapterHelper helper, String item) {
                        helper.setText(R.id.filter_item_content, item);
                    }
                };
                priceAdapter.clearAll();
                priceAdapter.addAll(Arrays.asList(playItems));
                filterListView.setAdapter(priceAdapter);
                resetFilterStatus();
                setPriceFilter();
                if(view_mask_bg.isShown()) return;
                    show();
                break;
            case R.id.ll_filter:
                flag = 4;
                if(filteAdapter != null) {
                    filteAdapter.clearAll();
                    filteAdapter.addAll(filterLists);
                    filterListView.setAdapter(filteAdapter);
                    resetFilterStatus();
                    setFilterFilter();
                    if(view_mask_bg.isShown()) return;
                    show();
                }else {
                    initLandMarkFilter(mSceneryId);
                }
                break;
            case R.id.view_mask_bg:
//                resetFilterStatus();
                hide();
                break;
        }
    }

    private void initLandMarkFilter(int sceneryId) {
        Log.i("sceneryId", "initLandMarkFilter mSceneryId:" + mSceneryId);
        Request<String> request = new StringRequestImpl(Constants.BASE_PATH + "resort/getResortLandmark", RequestMethod.POST);
        request.add("resortId", mSceneryId);
        request.add("page", 1);
        request.add("pageSize", 10);
        CallServer.getRequestInstance().add(getContext(), HttpConstants.QUERY_STORE_LANDMARKS, request, this, true, false);
    }

    private void queryStoresByOptions() {
        Request<String> request = new StringRequestImpl(Constants.BASE_PATH + "shopList/list", RequestMethod.POST);
        request.add("resortId", mSceneryId);
        request.add("productType", 3);
        request.add("sort", 1);
        request.add("sortOrder", 1);
        request.add("page", mPageNo);
        request.add("pageSize", mPageSize);
        CallServer.getRequestInstance().add(getContext(), HttpConstants.QUERY_STORES_BYOPTIONS, request, this, true, true);
    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        int code = 0;
        String result = response.get();
        switch (what) {
            case HttpConstants.QUERY_STORES_BYOPTIONS:
                Log.i("result", "result get store list:" + result);
                ShopListBean bean = JsonEasy.toObject(result, ShopListBean.class);
                if(bean == null) return;
                Log.i("result", "result get store list bean2:" + bean.getMessage().getTotalNumber() + " " + bean.getMessage().getPageSize() + " " + bean.getMessage().getPageNum());
                code = bean.getCode();
                if(code == 1) {
                    if(bean.getMessage() == null) return;
                    mTotalPage = bean.getMessage().getTotalNumber();
                    List<Shop> shops = bean.getMessage().getResult();
                    lists.addAll(shops);
                    listView.updateLoadMoreViewText(shops);
                    listView.onLoadMoreComplete();
                    adapter.addAll(shops);
                }
                break;
            case HttpConstants.QUERY_STORE_LANDMARKS:
                Log.i("result", "result get store landmarks:" + result);
                final SceneryLandMarkBean markBean = JsonEasy.toObject(result, SceneryLandMarkBean.class);
                if(markBean == null) return;
                code = markBean.getCode();
                if(code == 1) {
                    //设置数据到listview
                    filteAdapter = new QuickAdapter<LandMark>(context, R.layout.filter_listview_item) {
                        @Override
                        protected void convert(BaseAdapterHelper helper, LandMark item) {
                            helper.setText(R.id.filter_item_content, item.getLandmarkName());
                        }
                    };
                    filterLists = markBean.getMessage().getResult();
                    filteAdapter.clearAll();
                    filteAdapter.addAll(filterLists);
                    filterListView.setAdapter(filteAdapter);
                    resetFilterStatus();
                    setFilterFilter();
                    show();
                }
                break;
        }
    }

    @Override
    public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {

    }
}
