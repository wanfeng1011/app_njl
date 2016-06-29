package com.app.njl.activity.scenerypicker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.app.njl.activity.scenerypicker.bean.Destination;
import com.app.njl.activity.scenerypicker.bean.SceneryBeanResponse;
import com.app.njl.subject.mine.nohttp.CallServer;
import com.app.njl.subject.mine.nohttp.Constants;
import com.app.njl.subject.mine.nohttp.HttpConstants;
import com.app.njl.subject.mine.nohttp.HttpListener;
import com.app.njl.subject.mine.nohttp.StringRequestImpl;
import com.app.njl.utils.JsonEasy;
import com.citypicker.adapter.ResultListAdapter;
import com.citypicker.db.DBManager;
import com.citypicker.model.City;
import com.citypicker.model.LocateState;
import com.citypicker.utils.StringUtils;
import com.citypicker.utils.ToastUtils;
import com.citypicker.view.SideLetterBar;
import com.lostad.applib.R;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * author zaaach on 2016/1/26.
 */
public class CityPickerActivity extends Activity implements View.OnClickListener, HttpListener<String> {
    public static final int REQUEST_CODE_PICK_CITY = 2333;
    public static final String KEY_PICKED_CITY = "picked_city";

    private ListView mListView;
    private ListView mResultListView;
    private SideLetterBar mLetterBar;
    private EditText searchBox;
    private ImageView clearBtn;
    private ImageView backBtn;
    private ViewGroup emptyView;

    private com.app.njl.activity.scenerypicker.adapter.CityListAdapter mCityAdapter;
    private com.app.njl.activity.scenerypicker.adapter.ResultListAdapter mResultAdapter;
    private List<City> mAllCities;
    private DBManager dbManager;
    private List<Destination> mHotDestinations;
    private List<Destination> mAllScenerylist;

    private AMapLocationClient mLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);

        initData();
//        initView();
//        initLocation();
    }

    private void initLocation() {
        mLocationClient = new AMapLocationClient(this);
        AMapLocationClientOption option = new AMapLocationClientOption();
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        option.setOnceLocation(true);
        mLocationClient.setLocationOption(option);
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        String city = aMapLocation.getCity();
                        String district = aMapLocation.getDistrict();
                        Log.e("onLocationChanged", "city: " + city);
                        Log.e("onLocationChanged", "district: " + district);
                        String location = StringUtils.extractLocation(city, district);
                        mCityAdapter.updateLocateState(LocateState.SUCCESS, location);
                    } else {
                        //定位失败
                        mCityAdapter.updateLocateState(LocateState.FAILED, null);
                    }
                }
            }
        });
        mLocationClient.startLocation();
    }

    private void initData() {
        dbManager = new DBManager(this);
        dbManager.copyDBFile();
        mAllCities = dbManager.getAllCities();
        //获取热门景点
        getHotScenery();
        //获取所有景区
        getAllScenery();
        /*mCityAdapter = new CityListAdapter(this, mAllCities);
        mCityAdapter.setOnCityClickListener(new CityListAdapter.OnCityClickListener() {
            @Override
            public void onCityClick(String name) {
                back(name);
            }

            @Override
            public void onLocateClick() {
                Log.e("onLocateClick", "重新定位...");
                mCityAdapter.updateLocateState(LocateState.LOCATING, null);
                mLocationClient.startLocation();
            }
        });

        mResultAdapter = new ResultListAdapter(this, null);*/
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.listview_all_city);
        mListView.setAdapter(mCityAdapter);

        TextView overlay = (TextView) findViewById(R.id.tv_letter_overlay);
        mLetterBar = (SideLetterBar) findViewById(R.id.side_letter_bar);
        mLetterBar.setOverlay(overlay);
        mLetterBar.setOnLetterChangedListener(new SideLetterBar.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter) {
                int position = mCityAdapter.getLetterPosition(letter);
                mListView.setSelection(position);
            }
        });

        searchBox = (EditText) findViewById(R.id.et_search);
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String keyword = s.toString();
                if (TextUtils.isEmpty(keyword)) {
                    clearBtn.setVisibility(View.GONE);
                    emptyView.setVisibility(View.GONE);
                    mResultListView.setVisibility(View.GONE);
                } else {
                    clearBtn.setVisibility(View.VISIBLE);
                    mResultListView.setVisibility(View.VISIBLE);
//                    List<City> result = dbManager.searchCity(keyword);
                    //搜索结果
                    if(mAllScenerylist == null) {
                        emptyView.setVisibility(View.VISIBLE);
                        return;
                    }
                    List<Destination> result = new ArrayList<Destination>();
                    for(int i=0; i<mAllScenerylist.size(); i++) {
                        if(mAllScenerylist.get(i).getName().contains(keyword) || mAllScenerylist.get(i).getPinYin().contains(keyword)
                                || mAllScenerylist.get(i).getBriefPinYin().contains(keyword)) {
                            result.add(mAllScenerylist.get(i));
                        }
                    }
                    if (result == null || result.size() == 0) {
                        emptyView.setVisibility(View.VISIBLE);
                    } else {
                        emptyView.setVisibility(View.GONE);
                        mResultAdapter.changeData(result);
                    }
                }
            }
        });

        emptyView = (ViewGroup) findViewById(R.id.empty_view);
        mResultListView = (ListView) findViewById(R.id.listview_search_result);
        mResultListView.setAdapter(mResultAdapter);
        mResultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                back(mResultAdapter.getItem(position).getResortid(), mResultAdapter.getItem(position).getName());
            }
        });

        clearBtn = (ImageView) findViewById(R.id.iv_search_clear);
        backBtn = (ImageView) findViewById(R.id.back);

        clearBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
    }

    private void getHotScenery() {
        Request<String> request = new StringRequestImpl(Constants.BASE_PATH + "resort/getHotResort", RequestMethod.POST);
        CallServer.getRequestInstance().add(this, HttpConstants.QUERY_HOT_SCENERY, request, this, true, true);
    }

    private void getAllScenery() {
        Request<String> request = new StringRequestImpl(Constants.BASE_PATH + "resort/getResortNameAndPinYin", RequestMethod.POST);
        CallServer.getRequestInstance().add(this, HttpConstants.QUERY_ALL_SCENERY, request, this, true, true);
    }

    private void back(int sceneryId, String sceneryName){
        ToastUtils.showToast(this, "点击的城市：" + sceneryName);
        Intent intent = getIntent();
        intent.putExtra("sceneryName", sceneryName);
        intent.putExtra("sceneryId", sceneryId);
        setResult(2, intent);
        finish();
//        Intent data = new Intent();
//        data.putExtra(KEY_PICKED_CITY, city);
//        setResult(RESULT_OK, data);
//        finish();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.iv_search_clear) {
            searchBox.setText("");
            clearBtn.setVisibility(View.GONE);
            emptyView.setVisibility(View.GONE);
            mResultListView.setVisibility(View.GONE);
        }else if(id == R.id.back) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mLocationClient.stopLocation();
    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        int code = 0;
        String result = response.get();
        switch (what) {
            case HttpConstants.QUERY_ALL_SCENERY:
                Log.i("result", "result get all scenery:" + result);
                SceneryBeanResponse bean = JsonEasy.toObject(result, SceneryBeanResponse.class);
                if(bean == null) return;
                code = bean.getCode();
                if(code == 1) {
                    mAllScenerylist = bean.getMessage().getDestinations();
                    Log.e("onLocateClick", "mCities:" + bean.getMessage());
//                    mAllScenerylist.add(0, new Destination("杭州", "hangzhou", ""));
//                    mAllScenerylist.add(1, new Destination("湖州", "huzhou", ""));
                    if(mHotDestinations == null)
                        return;
                    mCityAdapter = new com.app.njl.activity.scenerypicker.adapter.CityListAdapter(this, mAllScenerylist, mHotDestinations);
                    mCityAdapter.setOnCityClickListener(new com.app.njl.activity.scenerypicker.adapter.CityListAdapter.OnCityClickListener() {
                        @Override
                        public void onCityClick(int sceneryId, String name) {
                            back(sceneryId, name);
                        }

                        @Override
                        public void onLocateClick() {
                            Log.e("onLocateClick", "重新定位...");
                            mCityAdapter.updateLocateState(LocateState.LOCATING, null);
                            mLocationClient.startLocation();
                        }
                    });

                    mResultAdapter = new com.app.njl.activity.scenerypicker.adapter.ResultListAdapter(this, null);
                    initView();
                }else if(code == 2 || code == 0) {

                }
                break;
            case HttpConstants.QUERY_HOT_SCENERY:
                Log.i("result", "result get hot scenery:" + result);
                bean = JsonEasy.toObject(result, SceneryBeanResponse.class);
                if(bean == null) return;
                code = bean.getCode();
                if(code == 1) {
                    mHotDestinations = bean.getMessage().getDestinations();
                    if(mHotDestinations == null)
                        return;
                    mCityAdapter = new com.app.njl.activity.scenerypicker.adapter.CityListAdapter(this, mAllScenerylist, mHotDestinations);
                    mCityAdapter.setOnCityClickListener(new com.app.njl.activity.scenerypicker.adapter.CityListAdapter.OnCityClickListener() {
                        @Override
                        public void onCityClick(int sceneryId, String name) {
                            back(sceneryId, name);
                        }

                        @Override
                        public void onLocateClick() {
                            Log.e("onLocateClick", "重新定位...");
                            mCityAdapter.updateLocateState(LocateState.LOCATING, null);
                            mLocationClient.startLocation();
                        }
                    });

                    mResultAdapter = new com.app.njl.activity.scenerypicker.adapter.ResultListAdapter(this, null);
                    initView();
                }else if(code == 2 || code == 0) {

                }
                break;
        }
    }

    @Override
    public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {

    }
}
