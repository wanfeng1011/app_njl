package com.app.njl.subject.order.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.app.njl.R;
import com.app.njl.activity.MainActivity;
import com.app.njl.subject.hotel.model.entity.Fruit;
import com.app.njl.subject.hotel.presenter.impl.ShopDetailOrderQueryPresenterImpl;
import com.app.njl.subject.hotel.presenter.interfaces.IShopDetailOrderPresenter;
import com.app.njl.subject.hotel.view.CommonView;
import com.app.njl.ui.UIHelper;
import com.app.njl.ui.quickadapter.BaseAdapterHelper;
import com.app.njl.ui.quickadapter.QuickAdapter;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tiansj on 15/9/4.
 */
public class HotelPtrSwipeToLoadLayoutFragment extends Fragment implements OnRefreshListener, OnLoadMoreListener, CommonView<Fruit> {
    private MainActivity context;

    private int pno = 1;
    private boolean isLoadAll;

    @Bind(R.id.swipeToLoadLayout)
    SwipeToLoadLayout mSwipeToLoadLayout;
//    PtrClassicFrameLayout mPtrFrame;
    @Bind(R.id.swipe_target)
    ListView listView;
    QuickAdapter<Fruit> adapter;

    IShopDetailOrderPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.swipetoloadlayout_hotel, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = (MainActivity) getActivity();
        initData();
        initView();
        loadData();
    }

    void initView() {
        mSwipeToLoadLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeToLoadLayout.setRefreshing(true);
            }
        });
        adapter = new QuickAdapter<Fruit>(context, R.layout.item_beauty) {
            @Override
            protected void convert(BaseAdapterHelper helper, Fruit shop) {
                helper.setText(R.id.beauty_tv_title, shop.getName())
                        .setText(R.id.beauty_tv_info, shop.getContent())
                        .setText(R.id.beauty_tv_views, shop.getStar())
                        .setImageUrl(R.id.beauty_img, shop.getUrl()); // 自动异步加载图片
            }
        };
        listView.setDrawingCacheEnabled(true);
        listView.setAdapter(adapter);

        // 点击事件
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                UIHelper.showHouseDetailActivity(context);
            }
        });

        mSwipeToLoadLayout.setOnRefreshListener(this);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);

    }

    private void initData() {
        pno = 1;
        isLoadAll = false;
    }

    private void loadData() {
        presenter = new ShopDetailOrderQueryPresenterImpl();
        presenter.attachView(this);
        presenter.queryShopDetailOrder();
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
        presenter.detachView();
    }

    @Override
    public void onLoadMore() {
        loadData();
    }

    private void overRefresh() {
        mSwipeToLoadLayout.setRefreshing(false);
        mSwipeToLoadLayout.setLoadingMore(false);
    }

    @Override
    public void loadSuccess(List<Fruit> fruits) {
        adapter.addAll(fruits);
    }

    @Override
    public void loadFailed() {
        overRefresh();
    }

    @Override
    public void loadCompleted() {
        overRefresh();
    }

    @Override
    public void showToast(String showMessage) {

    }

    @Override
    public void onRefresh() {
        overRefresh();
    }
}
