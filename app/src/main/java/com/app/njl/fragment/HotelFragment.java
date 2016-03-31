package com.app.njl.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by jiaxx on 2016/3/28 0028.
 */
public class HotelFragment extends Fragment {
    /*private RefreshRecyclerView recyclerView;

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

        header = View.inflate(getContext(), R.layout.library_recycler_header, null);
        header2 = View.inflate(getContext(), R.layout.library_recycler_header2, null);
        footer = View.inflate(getContext(), R.layout.library_recycler_footer, null);

        recyclerView = (RefreshRecyclerView) view.findViewById(R.id.recyclerView);

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

            if(myAdapter == null) {
                myAdapter = new MyAdapter();

                RecyclerViewManager.with(myAdapter, new LinearLayoutManager(getActivity()))
                        .setMode(RecyclerMode.BOTH)
                        .addHeaderView(header)
                        .addHeaderView(header2)
                        .addFooterView(footer)
                        .setOnBothRefreshListener(new OnBothRefreshListener() {
                            @Override
                            public void onPullDown() {
                                //模拟网络请求
                                Request<JSONArray> request = NoHttp.createJsonArrayRequest(Constants.URL_NOHTTP_JSONARRAY);
                                request.add("pageIndex", 1);
                                CallServer.getRequestInstance().add(getContext(), 1, request, arrayListener, true, false);
                            }

                            @Override
                            public void onLoadMore() {
                                //模拟网络请求
                                Request<JSONArray> request = NoHttp.createJsonArrayRequest(Constants.URL_NOHTTP_JSONARRAY);
                                request.add("pageIndex", 1);
                                CallServer.getRequestInstance().add(getContext(), 1, request, arrayListener, true, false);
                            }

                            @Override
                            public void onScrolling(int newState) {
                                Log.i("scrolling", "scrolling-----" + newState);
                            }
                        })
                        .setOnItemClickListener(new RefreshRecyclerViewAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(RecyclerView.ViewHolder holder, int position) {
                                Toast.makeText(getContext(), "item" + position, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .into(recyclerView, getContext());
            }else {
                recyclerView.onRefreshCompleted();
                myAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
            Log.i("result", "result failed" + exception);
        }
    };

    private class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tv_item;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_item = (TextView) itemView.findViewById(R.id.tv_item);
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
                        inflate(R.layout.library_recycler_item, parent, false);
                MyViewHolder holder = new MyViewHolder(v);
                return holder;
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof TitleHolder) {
                ((TitleHolder)holder).tv_item.setText(dataList.get(position).getName());
            }else if(holder instanceof MyViewHolder) {
                ((MyViewHolder)holder).tv_item.setText(dataList.get(position).getContent());
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        *//*switch (id){
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
        }*//*

        return super.onOptionsItemSelected(item);
    }*/
}
