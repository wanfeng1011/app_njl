package com.app.njl.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.app.njl.R;
import com.app.njl.activity.MainActivity;
import com.app.njl.model.Fruit;
import com.app.njl.model.SearchParam;
import com.app.njl.nohttp.CallServer;
import com.app.njl.nohttp.HttpListener;
import com.app.njl.ui.UIHelper;
import com.app.njl.ui.quickadapter.BaseAdapterHelper;
import com.app.njl.ui.quickadapter.QuickAdapter;
import com.app.njl.utils.Constants;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.Response;

import org.json.JSONArray;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tiansj on 15/9/4.
 */
public class HotelPtrSwipeToLoadLayoutFragment extends Fragment implements OnRefreshListener, OnLoadMoreListener{
    private MainActivity context;

    private SearchParam param;
    private int pno = 1;
    private boolean isLoadAll;

    @Bind(R.id.swipeToLoadLayout)
    SwipeToLoadLayout mSwipeToLoadLayout;
//    PtrClassicFrameLayout mPtrFrame;
    @Bind(R.id.swipe_target)
    ListView listView;
    QuickAdapter<Fruit> adapter;

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
        param = new SearchParam();
        pno = 1;
        isLoadAll = false;
    }

    private void loadData() {
        if (isLoadAll) {
            return;
        }
        param.setPno(pno);
        //加载数据
        Request<JSONArray> request = NoHttp.createJsonArrayRequest(Constants.URL_NOHTTP_JSONARRAY);
        request.add("pageIndex", 1);
        CallServer.getRequestInstance().add(getContext(), 1, request, arrayListener, true, false);

        /*HttpClient.getRecommendShops(param, new HttpResponseHandler() {
            @Override
            public void onSuccess(String body) {
                mPtrFrame.refreshComplete();
                JSONObject object = JSON.parseObject(body);
                List<SearchShop> list = JSONArray.parseArray(object.getString("body"), SearchShop.class);
                listView.updateLoadMoreViewText(list);
                isLoadAll = list.size() < HttpClient.PAGE_SIZE;
                if(pno == 1) {
                    adapter.clear();
                }
                adapter.addAll(list);
                pno++;
            }

            @Override
            public void onFailure(Request request, IOException e) {
                mPtrFrame.refreshComplete();
                listView.setLoadMoreViewTextError();
            }
        });*/
    }

    private HttpListener<JSONArray> arrayListener = new HttpListener<JSONArray>() {
        @Override
        public void onSucceed(int what, Response<JSONArray> response) {
            JSONArray jsonArray = response.get();
            StringBuilder builder = new StringBuilder(jsonArray.toString());

            List<Fruit> dataListJson = JSON.parseArray(jsonArray.toString(), Fruit.class);
//            listView.updateLoadMoreViewText(dataListJson);
            adapter.addAll(dataListJson);
            Log.i("result", "result:" + dataListJson.toString());
//            mPtrFrame.refreshComplete();
            overRefresh();
        }

        @Override
        public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
            Log.i("result", "result failed" + exception);
//            mPtrFrame.refreshComplete();
//            mSwipeToLoadLayout.isRefreshing();
            overRefresh();
//            listView.setLoadMoreViewTextError();
        }
    };

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
    }

    @Override
    public void onLoadMore() {
        KLog.i("onLoadMore---------------");
        //加载数据
        Request<JSONArray> request = NoHttp.createJsonArrayRequest(Constants.URL_NOHTTP_JSONARRAY);
        request.add("pageIndex", 1);
        CallServer.getRequestInstance().add(getContext(), 1, request, arrayListener, true, false);
    }

    @Override
    public void onRefresh() {
        KLog.i("onRefresh---------------");
        //加载数据
        Request<JSONArray> request = NoHttp.createJsonArrayRequest(Constants.URL_NOHTTP_JSONARRAY);
        request.add("pageIndex", 1);
        CallServer.getRequestInstance().add(getContext(), 1, request, arrayListener, true, false);
    }

    private void overRefresh() {
        mSwipeToLoadLayout.setRefreshing(false);
        mSwipeToLoadLayout.setLoadingMore(false);
    }
}
