package com.app.njl.subject.hotel.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.app.njl.R;
import com.app.njl.activity.MainActivity;
import com.app.njl.base.BaseFragment;
import com.app.njl.subject.hotel.model.entity.Fruit;
import com.app.njl.subject.hotel.presenter.impl.ShopDetailStayQueryPresenterImpl;
import com.app.njl.subject.hotel.presenter.interfaces.IShopDetailStayQueryPresenter;
import com.app.njl.subject.hotel.ui.ShopStayShowDetailActivity;
import com.app.njl.subject.hotel.view.CommonView;
import com.app.njl.ui.loadmore.LoadMoreListView;
import com.app.njl.ui.quickadapter.BaseAdapterHelper;
import com.app.njl.ui.quickadapter.QuickAdapter;
import com.app.njl.utils.DeviceUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * 商家详情特产Fragment
 * Created by jiaxx on 2016/4/16 0016.
 */
public class ShopDetailSpecialtyFragment extends BaseFragment implements CommonView<Fruit> {
    @Bind(R.id.rotate_header_list_view_frame)
    PtrClassicFrameLayout mPtrFrame;
    @Bind(R.id.listView)
    LoadMoreListView listView;
    QuickAdapter<Fruit> adapter;
    private MainActivity context;
    private IShopDetailStayQueryPresenter shopDetailStayQueryPresenter;
    @Override
    public int getLayoutRes() {
        context = (MainActivity) getActivity();
        return R.layout.shopdetailspecialty_fragment_layout;
    }

    @Override
    public void initView() {
        adapter = new QuickAdapter<Fruit>(context, R.layout.layout_shopdetail_item) {
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
        mPtrFrame.addPtrUIHandler(header);
    }

    @Override
    public void initPresenter() {
        shopDetailStayQueryPresenter = new ShopDetailStayQueryPresenterImpl();
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
        // 下拉刷新
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                loadData();
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
                loadData();
            }
        });

        // 点击事件
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), ShopStayShowDetailActivity.class);
                getContext().startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
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
    }

    @Override
    public void onResume() {
        super.onResume();
        Picasso.with(context).pauseTag(context);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Picasso.with(context).cancelTag(context);
        shopDetailStayQueryPresenter.detachView();
    }

    /**
     * 加载网络数据
     */
    private void loadData() {
        shopDetailStayQueryPresenter.attachView(this);
        shopDetailStayQueryPresenter.queryShopDetailStay();
    }

    @Override
    public void loadSuccess(List<Fruit> fruits) {
        listView.updateLoadMoreViewText(fruits);
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

    }
}
