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
import com.app.njl.subject.hotel.model.entity.shopdetails.ShopPlaysDetailData;
import com.app.njl.subject.hotel.ui.ShopPlayShowSceneryActivity;
import com.app.njl.subject.hotel.ui.ShopPlayShowSelfServiceActivity;
import com.app.njl.subject.hotel.ui.ShopPlayShowTourGuideActivity;
import com.app.njl.subject.mine.nohttp.CallServer;
import com.app.njl.subject.mine.nohttp.Constants;
import com.app.njl.subject.mine.nohttp.HttpConstants;
import com.app.njl.subject.mine.nohttp.HttpListener;
import com.app.njl.subject.mine.nohttp.StringRequestImpl;
import com.app.njl.utils.JsonEasy;
import com.app.njl.utils.SharedPreferences;
import com.bumptech.glide.Glide;
import com.socks.library.KLog;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by tiansj on 15/9/4.
 */
public class ShopDetailPlayFragment extends BaseFragment implements View.OnClickListener, HttpListener<String> {
    ShopDetailPagerActivity mActivity;

    @Bind(R.id.shopDetailPlay_SceneryTicket_recyclerView)
    RecyclerView mSceneryTicketRecyclerView;

    ShopDetailPlayTicketRecyclerAdapter mTicketAdapter;
    List<ShopPlaysDetailData.MessageBean.TicketVoListBean> ticketVoList;
    List<ShopPlaysDetailData.MessageBean.FamilyActivityVoListBean> familyActivityVoList;
    List<ShopPlaysDetailData.MessageBean.GuideVoListBean> guideVoList;
    private List<PlayData> mPlayDatas;

    private int mShopId = 1;

    @Override
    public int getLayoutRes() {
        return R.layout.layout_shopdetail_play;
    }

    @Override
    public void initView() {
        mSceneryTicketRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        mTourGuideRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        mSelfServiceRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void setListener() {
        /*scenery_ticket_ll.setOnClickListener(this);
        tour_guide_ll.setOnClickListener(this);
        self_service_ll.setOnClickListener(this);*/
    }

    @Override
    public void initLocalData() {
        mActivity = (ShopDetailPagerActivity)getActivity();
    }

    @Override
    public void initRemoteData() {
        mShopId = mActivity.getmShopId();
        queryShopDetailLivesByOptions(mShopId);
    }

    public void queryShopDetailLivesByOptions(int shopId) {
        String startDate = SharedPreferences.getInstance().getInt("live_in_year", 0) + "-" + SharedPreferences.getInstance().getInt("live_in_month", 0) + "-" + SharedPreferences.getInstance().getInt("live_in_day", 0);
        String endDate = SharedPreferences.getInstance().getInt("live_out_year", 0) + "-" + SharedPreferences.getInstance().getInt("live_out_month", 0) + "-" + SharedPreferences.getInstance().getInt("live_out_day", 0);
        Request<String> request = new StringRequestImpl(Constants.BASE_PATH + "shop/travel", RequestMethod.POST);
        request.add("shopId", shopId);
//        request.add("page", 1);
//        request.add("pageSize", mPageSize);
        request.add("dateStart", startDate);
        request.add("dateEnd", endDate);
        Log.i("sceneryId", "shopId:" + shopId + " startData:" + startDate + " endDate:" + endDate);
        CallServer.getRequestInstance().add(getContext(), HttpConstants.QUERY_SHOPPLAY_DETAIL_BYOPTIONS, request, this, true, true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*case R.id.scenery_ticket_ll:
                Intent intent_ticket = new Intent(getContext(), ShopPlayShowSceneryActivity.class);
                getContext().startActivity(intent_ticket);
                getActivity().overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                break;
            case R.id.tour_guide_ll:
                Intent intent_tour_guide = new Intent(getContext(), ShopPlayShowTourGuideActivity.class);
                getContext().startActivity(intent_tour_guide);
                getActivity().overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                break;
            case R.id.self_service_ll:
                Intent intent_self_service = new Intent(getContext(), ShopPlayShowSelfServiceActivity.class);
                getContext().startActivity(intent_self_service);
                getActivity().overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                break;*/
        }
    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        int code = 0;
        String result = response.get();
        switch (what) {
            case HttpConstants.QUERY_SHOPPLAY_DETAIL_BYOPTIONS:
                Log.i("result", "result get shop travels:" + result);
                if(result == null) return;
                ShopPlaysDetailData shopPlaysDetailData = JsonEasy.toObject(result, ShopPlaysDetailData.class);
                if(shopPlaysDetailData == null) return;
                code = shopPlaysDetailData.getCode();
                if(code == 1) {
                    ticketVoList = shopPlaysDetailData.getMessage().getTicketVoList();
                    familyActivityVoList = shopPlaysDetailData.getMessage().getFamilyActivityVoList();
                    guideVoList = shopPlaysDetailData.getMessage().getGuideVoList();
                    mPlayDatas = new ArrayList<>();
                    PlayData playData = null;
                    if(ticketVoList != null) {
                        for (int i=0; i<ticketVoList.size(); i++) {
                            if(i == 0) {
                                playData = new PlayData();
                                playData.setType("门票代购");
                                mPlayDatas.add(playData);
                            }
                            playData = new PlayData();
                            playData.setId(ticketVoList.get(i).getShopId());
                            playData.setName(ticketVoList.get(i).getTicketType());
                            playData.setContent(ticketVoList.get(i).getTicketVo() + "");
                            playData.setPrice(ticketVoList.get(i).getTicketVo());
                            playData.setPic(ticketVoList.get(i).getPicUrl());
                            playData.setType("门票代购");
                            mPlayDatas.add(playData);
                        }
                    }
                    if(familyActivityVoList != null) {
                        for (int i=0; i<familyActivityVoList.size(); i++) {
                            if(i == 0) {
                                playData = new PlayData();
                                playData.setType("自助游");
                                mPlayDatas.add(playData);
                            }
                            playData = new PlayData();
                            playData.setId(familyActivityVoList.get(i).getShopId());
                            playData.setName(familyActivityVoList.get(i).getFamilyActivityName());
                            playData.setContent(familyActivityVoList.get(i).getFamilyActivityComment());
                            playData.setPrice(familyActivityVoList.get(i).getFamilyActivityPrice());
                            playData.setPic(familyActivityVoList.get(i).getPicUrl());
                            playData.setFamilyActivityId(familyActivityVoList.get(i).getFamilyActiveId());
                            playData.setType("自助游");
                            mPlayDatas.add(playData);
                        }
                    }
                    if(guideVoList != null) {
                        for (int i=0; i<guideVoList.size(); i++) {
                            if(i == 0) {
                                playData = new PlayData();
                                playData.setType("导游服务");
                                mPlayDatas.add(playData);
                            }
                            playData = new PlayData();
                            playData.setId(guideVoList.get(i).getShopId());
                            playData.setGuideId(guideVoList.get(i).getGuideId());
                            playData.setName(guideVoList.get(i).getDescribe());
                            playData.setContent(guideVoList.get(i).getComments());
                            playData.setPrice(guideVoList.get(i).getTourGuardPrice());
                            playData.setPic(guideVoList.get(i).getPicUrl());
                            playData.setType("导游服务");
                            mPlayDatas.add(playData);
                        }
                    }
                    /*mTicketAdapter = new ShopDetailPlayTicketRecyclerAdapter();
                    mSceneryTicketRecyclerView.setAdapter(mTicketAdapter);*/
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTicketAdapter = new ShopDetailPlayTicketRecyclerAdapter();
                            mSceneryTicketRecyclerView.setAdapter(mTicketAdapter);
                        }
                    });
                    /*getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTourGuideAdapter = new ShopDetailPlayTourGuideRecyclerAdapter();
                            mTourGuideRecyclerView.setAdapter(mTourGuideAdapter);
                        }
                    });
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mSelfServiceAdapter = new ShopDetailPlaySelfServiceRecyclerAdapter();
                            mSelfServiceRecyclerView.setAdapter(mSelfServiceAdapter);
                        }
                    });*/
                }
                break;
        }
    }

    @Override
    public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {

    }

    class ShopDetailPlayTicketRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            KLog.i("viewType:" + viewType);
            if(viewType == 2) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_shopdetail_play_item, parent, false);
                return new ShopDetailPlayViewHold(view);
            }else {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_shopdetail_play_item_title, parent, false);
                return new ShopDetailPlayTitleViewHold(view);
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            if(holder instanceof ShopDetailPlayViewHold) {
                ShopDetailPlayViewHold viewHold = (ShopDetailPlayViewHold)holder;
                viewHold.itemName.setText(mPlayDatas.get(position).getName());
                viewHold.itemContent.setText(mPlayDatas.get(position).getContent());
                viewHold.itemPrice.setText("￥" + mPlayDatas.get(position).getPrice());
                Glide.with(mActivity).load(mPlayDatas.get(position).getPic())
                        .placeholder(R.mipmap.default_image)
                        .error(R.mipmap.default_image)
                        .into(viewHold.itemPic);
                /*Picasso.with(getContext()).load(mPlayDatas.get(position).getPic())
                        .placeholder(R.mipmap.default_image)
                        .error(R.mipmap.default_image)
                        .tag(getContext())
                        .into(viewHold.itemPic);*/
                viewHold.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    Intent intent = null;
                    if(mPlayDatas.get(position).getType().equals("门票代购")) {
                        intent = new Intent(getContext(), ShopPlayShowSceneryActivity.class);
                        intent.putExtra("resortId", mPlayDatas.get(position).getId());
                    }else if(mPlayDatas.get(position).getType().equals("自助游")) {
                        intent = new Intent(getContext(), ShopPlayShowSelfServiceActivity.class);
                        intent.putExtra("resortId", mPlayDatas.get(position).getId());
                        intent.putExtra("selfServiceName", mPlayDatas.get(position).getName());
                        intent.putExtra("familyActivityId", mPlayDatas.get(position).getFamilyActivityId());
                    }else if(mPlayDatas.get(position).getType().equals("导游服务")) {
                        intent = new Intent(getContext(), ShopPlayShowTourGuideActivity.class);
                        intent.putExtra("resortId", mPlayDatas.get(position).getId());
                        intent.putExtra("guideId", mPlayDatas.get(position).getGuideId());
                    }
                    getContext().startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                    }
                });
            }else if(holder instanceof ShopDetailPlayTitleViewHold){
                ShopDetailPlayTitleViewHold viewHold = (ShopDetailPlayTitleViewHold)holder;
                viewHold.itemTitle.setText(mPlayDatas.get(position).getType());
            }
        }

        @Override
        public int getItemCount() {
            return mPlayDatas == null ? 0 : mPlayDatas.size();
        }

        /**
         * 决定元素的布局使用哪种类型
         * @param position 数据源List的下标
         * @return 一个int型标志，传递给onCreateViewHolder的第二个参数
         */
        @Override
        public int getItemViewType(int position) {
            if(position == 0)
                return 1;
            //第一个要显示时间
            String type = mPlayDatas.get(position).getType();
            int prevIndex = position - 1;
            boolean isDifferent = !mPlayDatas.get(prevIndex).getType().equals(type);
            return isDifferent ? 1 : 2;
        }

        @Override
        public long getItemId(int position) {
            return mPlayDatas.get(position).getId();
        }
    }

    class ShopDetailPlayViewHold extends RecyclerView.ViewHolder {
        public TextView itemName;
        public TextView itemContent;
        public TextView itemPrice;
        public ImageView itemPic;
        public TextView itemBuy;
        public ShopDetailPlayViewHold(View itemView) {
            super(itemView);
            this.itemName = (TextView)itemView.findViewById(R.id.play_item_name);
            this.itemContent = (TextView)itemView.findViewById(R.id.play_item_content);
            this.itemPrice = (TextView)itemView.findViewById(R.id.play_item_price);
            this.itemPic = (ImageView)itemView.findViewById(R.id.play_item_img);
            this.itemBuy = (TextView)itemView.findViewById(R.id.play_item_buy);
        }
    }

    class ShopDetailPlayTitleViewHold extends RecyclerView.ViewHolder {
        public TextView itemTitle;
        public ShopDetailPlayTitleViewHold(View itemView) {
            super(itemView);
            this.itemTitle = (TextView)itemView.findViewById(R.id.play_item_title);
        }
    }

    class PlayData {
        int id;
        int guideId;
        int familyActivityId;
        String name;
        String content;
        double price;
        String pic;
        String expand;
        String type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGuideId() {
            return guideId;
        }

        public void setGuideId(int guideId) {
            this.guideId = guideId;
        }

        public int getFamilyActivityId() {
            return familyActivityId;
        }

        public void setFamilyActivityId(int familyActivityId) {
            this.familyActivityId = familyActivityId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getExpand() {
            return expand;
        }

        public void setExpand(String expand) {
            this.expand = expand;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
