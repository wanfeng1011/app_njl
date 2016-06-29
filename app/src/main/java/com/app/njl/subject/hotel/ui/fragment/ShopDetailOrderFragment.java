package com.app.njl.subject.hotel.ui.fragment;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.njl.R;
import com.app.njl.activity.MainActivity;
import com.app.njl.base.BaseFragment;
import com.app.njl.subject.hotel.model.entity.Fruit;
import com.app.njl.subject.hotel.model.entity.PickerListEntity;
import com.app.njl.subject.hotel.presenter.impl.ShopDetailOrderQueryPresenterImpl;
import com.app.njl.subject.hotel.presenter.interfaces.IShopDetailOrderPresenter;
import com.app.njl.subject.hotel.ui.ShopOrderShopDetailActivity;
import com.app.njl.subject.hotel.view.CommonView;
import com.app.njl.subject.hotel.view.ShopDetailOrderView;
import com.app.njl.ui.loadmore.LoadMoreListView;
import com.app.njl.ui.quickadapter.BaseAdapterHelper;
import com.app.njl.ui.quickadapter.QuickAdapter;
import com.app.njl.utils.Utils;
import com.app.njl.widget.wheel.view.PickerView;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by tiansj on 15/9/4.
 */
public class ShopDetailOrderFragment extends BaseFragment implements CommonView<Fruit>, ShopDetailOrderView<PickerListEntity>, View.OnClickListener {

    private MainActivity context;

    @Bind(R.id.order_rotate_header_list_view_frame)
    PtrClassicFrameLayout mPtrFrame;
    @Bind(R.id.order_listView)
    LoadMoreListView listView;
    @Bind(R.id.order_pickersure_btn)
    Button pickerSureBtn;
    @Bind(R.id.order_pickercancel_btn)
    Button pickerCancelBtn;
    QuickAdapter<Fruit> adapter;

    @Bind(R.id.month_pv)
    PickerView minute_pv;
    @Bind(R.id.day_pv)
    PickerView second_pv;
    @Bind(R.id.order_pv)
    PickerView order_pv;

    @Bind(R.id.order_select_ll)
    LinearLayout order_select_ll;
    @Bind(R.id.pickerView_ll)
    LinearLayout pickerViewLl;
    @Bind(R.id.order_select_tv)
    TextView order_select_tv;
    @Bind(R.id.order_select_arrow)
    ImageView order_select_arrow;

    @Bind(R.id.view_mask_bg)
    View view_mask_bg;
    int panelHeight;
    boolean isShow;

    boolean isShowPicker;

    List<String> monthList;
    List<String> dayList;
    List<String> orderList;
    int day;

    private IShopDetailOrderPresenter shopDetailOrderPresenter;
    int currentMonth;
    int currentDay;
    String currentOrder = "中餐";

    @Override
    public int getLayoutRes() {
        context = (MainActivity) getActivity();
        return R.layout.layout_order_dialog;
    }

    @Override
    public void initView() {
        adapter = new QuickAdapter<Fruit>(context, R.layout.layout_shopdetailorder_item) {
            @Override
            protected void convert(BaseAdapterHelper helper, Fruit shop) {
                helper.setText(R.id.name, shop.getName())
                        .setText(R.id.content, shop.getContent())
                        .setText(R.id.price, "菜" + shop.getPrice());
                        //.setImageUrl(R.id.img_detail, shop.getUrl()); // 自动异步加载图片
            }
        };
        listView.setDrawingCacheEnabled(true);
        listView.setAdapter(adapter);
    }

    @Override
    public void initPresenter() {
        shopDetailOrderPresenter = new ShopDetailOrderQueryPresenterImpl();
    }

    @Override
    public void setListener() {
        minute_pv.setOnSelectListener(new PickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {
                shopDetailOrderPresenter.loadDaysList(Integer.parseInt(text));
                currentMonth = Integer.parseInt(text);
                Toast.makeText(getContext(), "选择了 " + text + " 月",
                        Toast.LENGTH_SHORT).show();
            }
        });
        second_pv.setOnSelectListener(new PickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {
                order_pv.setData(orderList);
                order_pv.setSelected(0);
                currentDay = Integer.parseInt(text);
                Toast.makeText(getContext(), "选择了 " + text + " 日",
                        Toast.LENGTH_SHORT).show();
            }
        });
        order_pv.setOnSelectListener(new PickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {
                currentOrder = text + "餐";
                Toast.makeText(getContext(), "选择了 " + text + " 餐",
                        Toast.LENGTH_SHORT).show();
            }
        });

        order_select_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickerViewLl.setVisibility(View.VISIBLE);
                KLog.i("order_select_ll click-----------");
            }
        });

        pickerSureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order_select_tv.setText(currentMonth + "月" + currentDay + "日" + "    " + currentOrder);
                hide();
            }
        });

        pickerCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide();
            }
        });

        // 下拉刷新
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
//                initData();
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
//                UIHelper.showHouseDetailActivity(context);
//                UIHelper.showShopDetailActivity(context);
                Intent intent = new Intent(getContext(), ShopOrderShopDetailActivity.class);
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

        order_select_ll.setOnClickListener(this);
        view_mask_bg.setOnClickListener(this);
    }

    @Override
    public void initLocalData() {
        currentMonth = Utils.getMonth();
        currentDay = Utils.getDay();
        monthList = new ArrayList<>();
        dayList = new ArrayList<>();
        orderList = new ArrayList<>();

    }

    @Override
    public void initRemoteData() {
        loadData();
    }

    /**
     * 加载网络数据
     */
    private void loadData() {
        shopDetailOrderPresenter.attachView(this);
        shopDetailOrderPresenter.getPickListEntity();
        shopDetailOrderPresenter.queryShopDetailOrder();
    }



    @Override
    public void loadSuccess(List<Fruit> fruits) {
        KLog.i("loadSuccess-------");
        listView.updateLoadMoreViewText(fruits);
        adapter.addAll(fruits);
//        mPtrFrame.refreshComplete();
        KLog.i("loadSuccess2-------");
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
    public void onDestroy() {
        super.onDestroy();
        shopDetailOrderPresenter.detachView();
    }

    @Override
    public void loadOtherData(PickerListEntity pickerListEntity) {
        KLog.i("loadOtherData-------");
        monthList.clear();
        monthList.addAll(pickerListEntity.getMonthsList());
        dayList.clear();
        dayList.addAll(pickerListEntity.getDaysList());
        orderList.clear();
        orderList.addAll(pickerListEntity.getOrdersList());
        minute_pv.setData(monthList);

        second_pv.setData(dayList);

        order_pv.setData(orderList);

        minute_pv.setSelected(currentMonth - 1);

        second_pv.setSelected(currentDay - 1);

        order_pv.setSelected(0);
        KLog.i("loadOtherData2-------");
    }

    @Override
    public void loadDaysList(List<String> lists) {
        dayList.clear();
        dayList.addAll(lists);
        second_pv.setData(dayList);
        second_pv.setSelected(0);
        order_pv.setSelected(0);
    }

    private void show() {
        isShow = true;
        setSelectState();
        view_mask_bg.setVisibility(View.VISIBLE);
        pickerViewLl.setVisibility(View.VISIBLE);
        pickerViewLl.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                pickerViewLl.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                panelHeight = pickerViewLl.getHeight();
                ObjectAnimator.ofFloat(pickerViewLl, "translationY", -panelHeight, 0).setDuration(200).start();
            }
        });
    }

    private void hide() {
        isShow = false;
        view_mask_bg.setVisibility(View.GONE);
        ObjectAnimator.ofFloat(pickerViewLl, "translationY", 0, -panelHeight).setDuration(200).start();
        setSelectState();
    }

    // 设置低价优先数据
    private void setSelectState() {
        if(isShow) {
            order_select_tv.setTextColor(getContext().getResources().getColor(R.color.orange));
            order_select_arrow.setImageResource(R.mipmap.home_up_arrow);
        }else {
            order_select_tv.setTextColor(getContext().getResources().getColor(R.color.gray_light));
            order_select_arrow.setImageResource(R.mipmap.home_down_arrow);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_select_ll:
                if(isShow) return;
                show();
                break;
            case R.id.view_mask_bg:
                if(!isShow) return;
                hide();
                break;
        }
    }
}
