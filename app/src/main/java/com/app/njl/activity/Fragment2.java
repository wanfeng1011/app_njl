package com.app.njl.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.app.njl.R;
import com.app.njl.utils.Toast;
import com.app.njl.utils.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Fragment2 extends Fragment {
	@Bind(R.id.quickLogin_phoneEt)
	EditText mPhoneNumEt;
	@Bind(R.id.quickLogin_cancelIv)
	ImageView mPhoneNumCancelIv;
	@Bind(R.id.quickLogin_codeEt)
	EditText mCodeEt; //验证码
	@Bind(R.id.quickLogin_codeBtn)
	Button mCodeBtn; //获取验证码按钮
	boolean mPhoneNumIsUseful; //手机号是否可用的标识
	boolean mCodeIsUseful; //验证码是否可用的标识
	TimeCount time; //倒计时
	boolean isInTime = false; //是否在倒计时中

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflateAndSetupView(inflater, container, savedInstanceState, R.layout.njl_login_phone_fragment_layout);
	}
	
	private View inflateAndSetupView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState, int layoutResourceId) {
		View layout = inflater.inflate(layoutResourceId, container, false);
		ButterKnife.bind(this, layout);
		return layout;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mPhoneNumEt.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				Log.i("onTextChanged", s + " " + start + " " + before + " " + count);
				int length = s.length();
				if (length != 0) {
					if (!mPhoneNumCancelIv.isShown()) {
						mPhoneNumCancelIv.setVisibility(View.VISIBLE);
					}
					//1、判断手机号是否已输入11位 2、判断手机号是否是1开头
					if (Utils.isPhone(s.toString())) {
						Log.i("onTextChanged", "onTextChanged:" + "手机号可用");
						mPhoneNumIsUseful = true;
						if(!isInTime) {
							mCodeBtn.setBackgroundResource(R.drawable.verificationcode_normal_bg);
							mCodeBtn.setEnabled(true);
						}
					} else {
						if (mPhoneNumIsUseful && !isInTime) {
							mCodeBtn.setBackgroundResource(R.drawable.verificationcode_bg);
							mCodeBtn.setEnabled(false);
						}
						mPhoneNumIsUseful = false;
					}

				} else {
					if (mPhoneNumCancelIv.isShown()) {
						mPhoneNumCancelIv.setVisibility(View.INVISIBLE);
					}
				}
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

		mPhoneNumCancelIv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mPhoneNumEt.setText("");
				mPhoneNumCancelIv.setVisibility(View.INVISIBLE);
				if (mPhoneNumIsUseful && !isInTime) {
					mCodeBtn.setBackgroundResource(R.drawable.verificationcode_bg);
					mCodeBtn.setEnabled(false);
				}
				mPhoneNumIsUseful = false;
			}
		});

		//验证码
		mCodeEt.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				Log.i("onTextChanged", s + " " + start + " " + before + " " + count + " " + s.length());
				int length = s.length();
				if (length == 6)
					mCodeIsUseful = true;
				else
					mCodeIsUseful = false;
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

		mCodeBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				time = new TimeCount(10000, 1000);
				time.start();
			}
		});
	}

	class TimeCount extends CountDownTimer {
		public TimeCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
		}
		@Override
		public void onFinish() {//计时完毕时触发
			mCodeBtn.setText("获取验证码");
			if(isInTime && mPhoneNumIsUseful) {
				mCodeBtn.setBackgroundResource(R.drawable.verificationcode_normal_bg);
				mCodeBtn.setEnabled(true);
			}
			isInTime = false;
		}
		@Override
		public void onTick(long millisUntilFinished){//计时过程显示
			mCodeBtn.setEnabled(false);
			if(!isInTime)
				mCodeBtn.setBackgroundResource(R.drawable.verificationcode_bg);
			mCodeBtn.setText(millisUntilFinished /1000+"秒");
			isInTime = true;
		}
	}

	/**
	 * 验证手机号，密码，验证码是否输入或者是否合法
	 */
	public void checkPhonePwdCode() {
		//检测手机号是否合法
		if(!mPhoneNumIsUseful) {
			Toast.show("请输入正确的手机号码");
			return;
		}
		//检测验证码
		if(!mCodeIsUseful) {
			Toast.show("请输入正确的验证码");
			return;
		}
		Toast.show("验证通过，可以注册");
	}
}
