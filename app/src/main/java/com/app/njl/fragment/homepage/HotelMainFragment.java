package com.app.njl.fragment.homepage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.app.njl.R;
import com.app.njl.activity.MainActivity;
import com.app.njl.adapter.BrowsingHistoryListAdapter;
import com.app.njl.model.Fruit;
import com.app.njl.nohttp.CallServer;
import com.app.njl.nohttp.HttpListener;
import com.app.njl.utils.Constants;
import com.app.njl.utils.Options;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.Response;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import space.sye.z.library.RefreshRecyclerView;
import space.sye.z.library.adapter.RefreshRecyclerViewAdapter;
import space.sye.z.library.listener.OnBothRefreshListener;
import space.sye.z.library.manager.RecyclerMode;
import space.sye.z.library.manager.RecyclerViewManager;

/**
 * Created by jiaxx on 2016/3/28 0028.
 */
public class HotelMainFragment extends Fragment implements OnBothRefreshListener {
    private RefreshRecyclerView recyclerView;
    private RecyclerView mRecyclerView;
    private BrowsingHistoryListAdapter mAdapter;
    private Button search_btn;

    private static final int PULL_DOWN = 1;
    private static final int LOAD_MORE = 2;
    private int counts = 10;
    private MyAdapter myAdapter;
    private int page = 1;
    private List<Fruit> dataList = new ArrayList<>();
    View header, header2, footer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.library_activity_main, container, false);
//        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//        getContext().setSupportActionBar(toolbar);

        mAdapter = new BrowsingHistoryListAdapter();
        header = View.inflate(getContext(), R.layout.library_recycler_header, null);
//        header2 = View.inflate(getContext(), R.layout.library_recycler_header2, null);
        search_btn = (Button) header.findViewById(R.id.search_btn);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fragment = new ShopResultPagerFragment();
                for (int j = 0; j < MainActivity.fragmentTags.size(); j++) {
                    Fragment f = fragmentManager.findFragmentByTag(MainActivity.fragmentTags.get(j));
                    if(f != null && f.isAdded()) {
                        fragmentTransaction.hide(HotelMainFragment.this);
                    }
                }
                if (fragment.isAdded()) {
                    fragmentTransaction.show(fragment);
                } else {
                    if(!MainActivity.fragmentTags.contains("HotelSearchResultFragment")) {
                        MainActivity.fragmentTags.add("HotelSearchResultFragment");
                    }
                    fragmentTransaction.add(R.id.fragment_container, fragment, "HotelSearchResultFragment");
                }
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commitAllowingStateLoss();
                fragmentManager.executePendingTransactions();
            }
        });

        mRecyclerView = (RecyclerView)header.findViewById(R.id.id_recyclerview_record);
        //图片显示控件
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mRecyclerView.setAdapter(mAdapter);


        footer = View.inflate(getContext(), R.layout.library_recycler_footer, null);

        recyclerView = (RefreshRecyclerView) view.findViewById(R.id.recyclerView);


        myAdapter = new MyAdapter();

        RecyclerViewManager.with(myAdapter, new LinearLayoutManager(getActivity()))
                .setMode(RecyclerMode.BOTH)
                .addHeaderView(header)
//                        .addHeaderView(header2)
                .addFooterView(footer)
                .setOnBothRefreshListener(this)
                .setOnItemClickListener(new RefreshRecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView.ViewHolder holder, int position) {
                        Toast.makeText(getContext(), "item" + position, Toast.LENGTH_SHORT).show();
                    }
                })
                .into(recyclerView, getContext());


        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//                Intent intent = new Intent(getContext(), LoginActivity.class);
//                startActivity(intent);
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Request<JSONArray> request = NoHttp.createJsonArrayRequest(Constants.URL_NOHTTP_JSONARRAY);
        request.add("pageIndex", 1);
        CallServer.getRequestInstance().add(getContext(), 1, request, arrayListener, true, false);
    }

    private HttpListener<JSONArray> arrayListener = new HttpListener<JSONArray>() {
        @Override
        public void onSucceed(int what, Response<JSONArray> response) {
            JSONArray jsonArray = response.get();
            StringBuilder builder = new StringBuilder(jsonArray.toString());

            List<Fruit> dataListJson = JSON.parseArray(jsonArray.toString(), Fruit.class);
            dataList.addAll(dataListJson);
            Log.i("result", "result:" + dataList.toString());

//            if(myAdapter == null) {
//                recyclerView.onRefreshCompleted();
//                myAdapter.notifyDataSetChanged();
//            }else {
                recyclerView.onRefreshCompleted();
//                myAdapter.notifyDataSetChanged();
//            }
                myAdapter.setData();
        }

        @Override
        public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
            Log.i("result", "result failed" + exception);
            recyclerView.onRefreshCompleted();
        }
    };

    @Override
    public void onPullDown() {
        Log.i("onPullDown", "onPullDown1--------");
        //模拟网络请求
        Request<JSONArray> request = NoHttp.createJsonArrayRequest(Constants.URL_NOHTTP_JSONARRAY);
        request.add("pageIndex", 1);
        CallServer.getRequestInstance().add(getContext(), 1, request, arrayListener, true, false);
    }

    @Override
    public void onLoadMore() {
        Log.i("onPullDown", "onPullDown1--------");
        //模拟网络请求
        Request<JSONArray> request = NoHttp.createJsonArrayRequest(Constants.URL_NOHTTP_JSONARRAY);
        request.add("pageIndex", 1);
        CallServer.getRequestInstance().add(getContext(), 1, request, arrayListener, true, false);
    }

    @Override
    public void onScrolling(int newState) {
        Log.i("onScrolling", "onScrolling--------" + newState + " " + RecyclerView.SCROLL_STATE_DRAGGING + " " + RecyclerView.SCROLL_STATE_IDLE
                + " " + RecyclerView.SCROLL_STATE_SETTLING);
        switch(newState) {
            case 0:
                ImageLoader.getInstance().resume();
                break;
            case 1:
                //if(this.pauseOnScroll) {
                ImageLoader.getInstance().pause();
                //}
                break;
            case 2:
                //if(this.pauseOnFling) {
                ImageLoader.getInstance().pause();
                //}
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tv_info;
        public ImageView img;
        public TextView tv_title;
        public TextView tv_views;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_info = (TextView) itemView.findViewById(R.id.beauty_tv_info);
            img = (ImageView) itemView.findViewById(R.id.beauty_img);
            tv_title = (TextView) itemView.findViewById(R.id.beauty_tv_title);
            tv_views = (TextView) itemView.findViewById(R.id.beauty_tv_views);
        }

    }

    private class TitleHolder extends RecyclerView.ViewHolder{

        public TextView tv_item;

        public TitleHolder(View itemView) {
            super(itemView);
            tv_item = (TextView) itemView.findViewById(R.id.tv_item);
        }

    }

    private class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == 1) {
                View v = LayoutInflater.from(getContext()).
                        inflate(R.layout.library_recycler_title_item, parent, false);
                TitleHolder holder = new TitleHolder(v);
                return holder;
            } else {
                View v = LayoutInflater.from(getContext()).
                        inflate(R.layout.item_beauty, parent, false);
                MyViewHolder holder = new MyViewHolder(v);
                return holder;
            }
        }

        public void setData() {
            notifyDataSetChanged();
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof TitleHolder) {
                ((TitleHolder)holder).tv_item.setText(dataList.get(position).getName());
            }else if(holder instanceof MyViewHolder) {
                Log.i("img", "img:---" + dataList.get(position).getUrl());
                if (dataList.get(position).getUrl().equals(((MyViewHolder) holder).img.getTag())) {

                } else {
                    ((MyViewHolder)holder).tv_title.setText(dataList.get(position).getContent());
                    ((MyViewHolder)holder).tv_info.setText(dataList.get(position).getPrice());
                    ((MyViewHolder)holder).tv_views.setText(dataList.get(position).getStar() + "星");
                    // 如果不相同，就加载。现在在这里来改变闪烁的情况
                    ImageLoader.getInstance().displayImage(dataList.get(position).getUrl(), ((MyViewHolder) holder).img,
                            Options.getListOptions());
                    ((MyViewHolder) holder).img.setTag(dataList.get(position).getUrl());
                }
                //ImageLoader.getInstance().displayImage(dataList.get(position).getUrl(), ((MyViewHolder) holder).img, Options.getListOptions());
            }
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        @Override
        public int getItemViewType(int position) {
            if(dataList.get(position).getType().equals("title")) {
                return 1;
            }else {
                return 2;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_linear:
                RecyclerViewManager.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.action_grid:
                RecyclerViewManager.setLayoutManager(new GridLayoutManager(this, 3));
                break;
            case R.id.action_staggered:
                RecyclerViewManager.setLayoutManager(new StaggeredGridLayoutManager(
                        2, StaggeredGridLayoutManager.VERTICAL));
                break;
        }*/

        return super.onOptionsItemSelected(item);
    }
}
