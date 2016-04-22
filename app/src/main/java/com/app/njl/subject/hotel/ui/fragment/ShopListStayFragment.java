package com.app.njl.subject.hotel.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.app.njl.R;
import com.app.njl.activity.MainActivity;
import com.app.njl.base.BaseFragment;
import com.app.njl.subject.hotel.model.entity.Fruit;
import com.app.njl.subject.hotel.presenter.impl.ShopListStayQueryPresenterImpl;
import com.app.njl.subject.hotel.presenter.interfaces.IShopListStayQueryPresenter;
import com.app.njl.subject.hotel.view.CommonView;
import com.app.njl.ui.loadmore.LoadMoreListView;
import com.app.njl.ui.quickadapter.BaseAdapterHelper;
import com.app.njl.ui.quickadapter.QuickAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by tiansj on 15/9/4.
 */
public class ShopListStayFragment extends BaseFragment implements CommonView<Fruit> {
    private MainActivity context;

    @Bind(R.id.rotate_header_list_view_frame)
    PtrClassicFrameLayout mPtrFrame;
    @Bind(R.id.listView)
    LoadMoreListView listView;
    QuickAdapter<Fruit> adapter;

    //Presenter
    IShopListStayQueryPresenter mPresenter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = (MainActivity) getActivity();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_demo_ptr;
    }

    @Override
    public void initView() {
        context = (MainActivity) getActivity();
        adapter = new QuickAdapter<Fruit>(context, R.layout.recommend_shop_list_item) {
            @Override
            protected void convert(BaseAdapterHelper helper, Fruit shop) {
                helper.setText(R.id.name, shop.getName())
                        .setText(R.id.favorable, shop.getContent())
                        .setText(R.id.star, shop.getStar() + "分")
                        .setText(R.id.state, "预定动态")
                        .setText(R.id.price, "住" + shop.getPrice() + "起")
                        .setImageUrl(R.id.logo, shop.getUrl()); // 自动异步加载图片
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
        mPresenter.attachView(this);
        mPresenter.queryShopListStay();
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
                initRemoteData();
            }
        });

        // 点击事件
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                replaceFragment();
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

    /**
     * 跳转到fragment界面
     */
    private void replaceFragment() {
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
    public void loadSuccess(List<Fruit> fruits) {
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
}
