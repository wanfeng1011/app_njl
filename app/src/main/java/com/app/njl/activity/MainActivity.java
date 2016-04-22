
package com.app.njl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioGroup;

import com.app.njl.R;
import com.app.njl.base.BaseActivity;
import com.app.njl.subject.hotel.ui.fragment.HotelMainFragment;
import com.app.njl.subject.hotel.ui.fragment.ShopDetailPagerFragment;
import com.app.njl.subject.hotel.ui.fragment.ShopListPagerFragment;
import com.app.njl.subject.mine.MemberFragment;
import com.app.njl.subject.order.ui.HotelPtrSwipeToLoadLayoutFragment;
import com.app.njl.subject.order.ui.OrderPagerFragment;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{
    private static final String FRAGMENT_TAGS = "fragmentTags";
    private static final String CURR_INDEX = "currIndex";
    public static int currIndex = 0;
    public static boolean isShowResultPager = false;

    public static ArrayList<String> fragmentTags;
    private FragmentManager fragmentManager;

    @Bind(R.id.group)
    RadioGroup group;
    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_main);
    }

    private void initData() {
        currIndex = 0;
        fragmentTags = new ArrayList<>(Arrays.asList("HomeFragment", "ImFragment", "InterestFragment", "MemberFragment"));
    }

    protected void initView(Bundle savedInstanceState) {
        fragmentManager = getBaseFragmentManager();
        if (savedInstanceState == null) {
            initData();
            group.setOnCheckedChangeListener(this);
            showFragment();
        } else {
            initFromSavedInstantsState(savedInstanceState);
        }
    }

    @Override
    protected void initControl() {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURR_INDEX, currIndex);
        outState.putStringArrayList(FRAGMENT_TAGS, fragmentTags);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        initFromSavedInstantsState(savedInstanceState);
    }

    private void initFromSavedInstantsState(Bundle savedInstanceState) {
        currIndex = savedInstanceState.getInt(CURR_INDEX);
        fragmentTags = savedInstanceState.getStringArrayList(FRAGMENT_TAGS);
        showFragment();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        /*if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }*/
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == 1) {
            Fragment fragment = fragmentManager.findFragmentByTag("HomeFragment");
            ((HotelMainFragment)fragment).showLiveData();
        }else if(requestCode == 2 && resultCode == 2) {
            Fragment fragment = fragmentManager.findFragmentByTag("HomeFragment");
            String city = data.getStringExtra("city");
            ((HotelMainFragment)fragment).setDestinationData(city);
        }else if(requestCode == 3 && resultCode == 1) {
            Fragment fragment = fragmentManager.findFragmentByTag("ShopListPagerFragment");
            ((ShopListPagerFragment)fragment).setStayTime();
            Fragment fragment2 = fragmentManager.findFragmentByTag("HomeFragment");
            ((HotelMainFragment)fragment2).showLiveData();
        } else if(requestCode == 4 && resultCode == 1) {
            Fragment fragment = fragmentManager.findFragmentByTag("ShopDetailPagerFragment");
            ((ShopDetailPagerFragment)fragment).setStayLiveTime();
            Fragment fragment2 = fragmentManager.findFragmentByTag("ShopListPagerFragment");
            ((ShopListPagerFragment)fragment2).setStayTime();
            Fragment fragment3 = fragmentManager.findFragmentByTag("HomeFragment");
            ((HotelMainFragment)fragment3).showLiveData();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.foot_bar_home:
                currIndex = (isShowResultPager ? 4 : 0);
                break;
            case R.id.foot_bar_im:
                currIndex = 1;
                break;
            case R.id.foot_bar_interest:
                currIndex = 2;
                break;
            case R.id.main_footbar_user:
                currIndex = 3;
                break;
            default:
                break;
        }
        showFragment();
    }

    /**
     * 隐藏其他的fragment，显示当前选中的fragment
     */
    private void showFragment() {
        FragmentTransaction fragmentTransaction = getBaseFragmentTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag(fragmentTags.get(currIndex));
        if(fragment == null) {
            fragment = instantFragment(currIndex);
        }

        //隐藏所有的fragment
        for (int i = 0; i < fragmentTags.size(); i++) {
            Fragment f = fragmentManager.findFragmentByTag(fragmentTags.get(i));
            if(f != null && f.isAdded()) {
                fragmentTransaction.hide(f);
            }
        }
        //显示当前点击的fragment
        if (fragment != null && fragment.isAdded()) {
            fragmentTransaction.show(fragment);
        } else {
            fragmentTransaction.add(R.id.fragment_container, fragment, fragmentTags.get(currIndex));
        }
        fragmentTransaction.commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();
    }

    /**
     * 根据点击的编号返回相应的fragment
     * @param currIndex 当前编号
     * @return fragment
     */
    private Fragment instantFragment(int currIndex) {
        switch (currIndex) {
            case 0: return new HotelMainFragment();
            case 1: return new OrderPagerFragment();
            case 2: return new HotelPtrSwipeToLoadLayoutFragment();
            case 3: return new MemberFragment();
            default: return null;
        }
    }
}
