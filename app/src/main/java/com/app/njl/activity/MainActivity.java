
package com.app.njl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.RadioGroup;

import com.app.njl.R;
import com.app.njl.fragment.HotelPtrFragment;
import com.app.njl.fragment.HotelPtrSwipeToLoadLayoutFragment;
import com.app.njl.fragment.homepage.HotelMainFragment;
import com.app.njl.fragment.mine.MemberFragment;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends BaseFragmentActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String FRAGMENT_TAGS = "fragmentTags";
    private static final String CURR_INDEX = "currIndex";
    public static int currIndex = 0;
    public static boolean isShowResultPager = false;

    private RadioGroup group;

    public static ArrayList<String> fragmentTags;
    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            initData();
            initView();
        } else {
            initFromSavedInstantsState(savedInstanceState);
        }
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

    private void initData() {
        currIndex = 0;
        fragmentTags = new ArrayList<>(Arrays.asList("HomeFragment", "ImFragment", "InterestFragment", "MemberFragment"));
    }

    private void initView() {
        group = (RadioGroup) findViewById(R.id.group);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.i("currentIndex", "before currentIndex:" + currIndex);
                switch (checkedId) {
                    case R.id.foot_bar_home: currIndex = (isShowResultPager == true ? 4 : 0) ;break;
                    case R.id.foot_bar_im: currIndex = 1; break;
                    case R.id.foot_bar_interest: currIndex = 2; break;
                    case R.id.main_footbar_user: currIndex = 3; break;
                    default: break;
                }
                Log.i("currentIndex", "currentIndex:" + currIndex);
                showFragment();
            }
        });
        showFragment();
    }

    private void showFragment() {
        if (currIndex == 3) {
//            UIHelper.showLogin(MainActivity.this);
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag(fragmentTags.get(currIndex));
        if(fragment == null) {
            fragment = instantFragment(currIndex);
        }
        for (int i = 0; i < fragmentTags.size(); i++) {
            Fragment f = fragmentManager.findFragmentByTag(fragmentTags.get(i));
            if(f != null && f.isAdded()) {
                fragmentTransaction.hide(f);
            }
        }
        if (fragment.isAdded()) {
            fragmentTransaction.show(fragment);
        } else {
            fragmentTransaction.add(R.id.fragment_container, fragment, fragmentTags.get(currIndex));
        }
        fragmentTransaction.commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();
    }

    private Fragment instantFragment(int currIndex) {
        switch (currIndex) {
            case 0: return new HotelMainFragment();
            case 1: return new HotelPtrFragment(); //HotelPtrSwipeToLoadLayoutFragment
            case 2: return new HotelPtrSwipeToLoadLayoutFragment();
            case 3: return new MemberFragment();
            default: return null;
        }
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
            ((HotelMainFragment)fragment).setLiveData();
        }else if(requestCode == 2 && resultCode == 2) {
            Fragment fragment = fragmentManager.findFragmentByTag("HomeFragment");
            String city = data.getStringExtra("city");
            ((HotelMainFragment)fragment).setDestinationData(city);
        }
    }
}
