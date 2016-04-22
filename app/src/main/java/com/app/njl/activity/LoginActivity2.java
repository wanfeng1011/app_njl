package com.app.njl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.njl.R;
import com.app.njl.subject.HomeFragment;
import com.app.njl.ui.tabstrip.PagerSlidingTabStrip;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity2 extends FragmentActivity implements View.OnClickListener{

	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	private LinearLayout backLl;
	private TextView titleTv;
	private LinearLayout register_ll;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.njl_layout_login);

		backLl = (LinearLayout)findViewById(R.id.back_ll);
		titleTv = (TextView)findViewById(R.id.title);
		tabs = (PagerSlidingTabStrip)findViewById(R.id.tabs);
		pager = (ViewPager)findViewById(R.id.pager);
		register_ll = (LinearLayout)findViewById(R.id.register_ll);

		initView();
    }

	private void initView() {
		titleTv.setText("登录");
		String[] titles = {"普通登录", "手机验证码登录"};
		List<Fragment> fragments = new ArrayList<>();
		fragments.add(new Fragment1());
		fragments.add(new Fragment2());
		FragmentPagerAdapter adapter = new TitlePagerAdapter(getSupportFragmentManager(), fragments, titles);
		pager.setAdapter(adapter);

		final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
		pager.setPageMargin(pageMargin);

		tabs.setViewPager(pager);

		backLl.setOnClickListener(this);
		register_ll.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.back_ll:
				this.finish();
				break;
			case R.id.register_ll:
				Intent intent_register = new Intent(getApplicationContext(), RegisterActivity.class);
				LoginActivity2.this.startActivity(intent_register);
				break;
		}
	}

	public class TitlePagerAdapter extends FragmentPagerAdapter {
		private String[] mTitles;
		private List<Fragment> mFragments;
		public TitlePagerAdapter(FragmentManager fm) {
			super(fm);
		}
		public TitlePagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] titles) {
			this(fm);
			mFragments = fragments;
			mTitles = titles;
		}

		@Override
		public Fragment getItem(int position) {
			KLog.i("getItem----------");
			if (mFragments != null && mFragments.size() != 0)
				return mFragments.get(position);
			else
				return new HomeFragment();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return mTitles[position % mTitles.length];
		}

		@Override
		public int getCount() {
			return mTitles.length;
		}
	}
}