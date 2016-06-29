package com.app.njl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.njl.R;
import com.app.njl.subject.HomeFragment;
import com.app.njl.ui.tabstrip.PagerSlidingTabStrip;
import com.app.njl.utils.Toast;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity2 extends FragmentActivity implements View.OnClickListener{

	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	private LinearLayout backLl;
	private TextView titleTv;
	private TextView register_tv;
	private TextView findBackPwd_tv;
	List<Fragment> fragments;
	private int type = 0; //0代表普通登录 1代表手机验证码登录
	Button login_sureBtn;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.njl_layout_login);

		backLl = (LinearLayout)findViewById(R.id.back_ll);
		titleTv = (TextView)findViewById(R.id.title);
		tabs = (PagerSlidingTabStrip)findViewById(R.id.tabs);
		pager = (ViewPager)findViewById(R.id.pager);
		register_tv = (TextView)findViewById(R.id.register_tv);
		findBackPwd_tv = (TextView)findViewById(R.id.login_findBackPwd_tv);
		login_sureBtn = (Button)findViewById(R.id.login_sureBtn);

		initView();
    }

	private void initView() {
		titleTv.setText("登录");
		String[] titles = {"普通登录", "手机验证码登录"};
		fragments = new ArrayList<>();
		fragments.add(new Fragment1());
		fragments.add(new Fragment2());
		FragmentPagerAdapter adapter = new TitlePagerAdapter(getSupportFragmentManager(), fragments, titles);
		pager.setAdapter(adapter);

		final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
		pager.setPageMargin(pageMargin);

		tabs.setViewPager(pager);
		pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				Log.i("onPageScrolled", "position:" + position + " positionOffset:" + positionOffset + " positionOffsetPixels" + positionOffsetPixels);

			}

			@Override
			public void onPageSelected(int position) {
				Log.i("onPageSelected", "onPageSelected position:" + position);
				if (position == 0) { //普通登录
					type = 0;
				} else if (position == 1) { //手机验证码登录
					type = 1;
				}
			}

			@Override
			public void onPageScrollStateChanged(int state) {
				Log.i("onPageScrollStateChanged", "state position:" + state);
			}
		});

		backLl.setOnClickListener(this);
		register_tv.setOnClickListener(this);
		findBackPwd_tv.setOnClickListener(this);
		login_sureBtn.setOnClickListener(this);
	}

	/**
	 * 验证手机号/邮箱，密码是否输入或者是否合法
	 */
	private void checkPhonePwd(boolean numUseful, boolean pwdUseful) {
		//检测手机号是否合法
		if(!numUseful) {
			Toast.show("请输入正确的手机号码或者邮箱");
			return;
		}
		//检测密码
		if(!pwdUseful) {
			Toast.show("密码请输入6-30位数字或者字母");
			return;
		}
		Toast.show("验证通过，可以注册");
	}

	/**
	 * 验证手机号，验证码是否输入或者是否合法
	 */
	private void checkPhoneCode(boolean numUseful, boolean codeUseful) {
		//检测手机号是否合法
		if(!numUseful) {
			Toast.show("请输入正确的手机号码");
			return;
		}
		//检测验证码
		if(!codeUseful) {
			Toast.show("请输入正确的验证码");
			return;
		}
		Toast.show("验证通过，可以注册");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.back_ll:
				this.finish();
				break;
			case R.id.register_tv:
				Intent intent_register = new Intent(getApplicationContext(), RegisterActivity.class);
				LoginActivity2.this.startActivity(intent_register);
				overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
				break;
			case R.id.login_sureBtn:
				if(type==0) {
					checkPhonePwd(((Fragment1)fragments.get(type)).mPhoneNumIsUseful, ((Fragment1)fragments.get(type)).mPwdIsUseful);
				}else if(type==1) {
					checkPhoneCode(((Fragment2)fragments.get(type)).mPhoneNumIsUseful, ((Fragment2)fragments.get(type)).mCodeIsUseful);
				}
				break;
			case R.id.login_findBackPwd_tv:
				Intent intent_findPwd = new Intent(getApplicationContext(), FindBackPassWordActivity.class);
				LoginActivity2.this.startActivity(intent_findPwd);
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