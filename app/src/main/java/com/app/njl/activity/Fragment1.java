package com.app.njl.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.app.njl.R;
import com.app.njl.utils.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Fragment1 extends Fragment {
	@Bind(R.id.custom_login_accountNumberEt)
	EditText mAccountNumberEt; //账号
	@Bind(R.id.custom_login_accountNumber_cancelIv)
	ImageView mAccountNumberCancelIv;

	@Bind(R.id.custom_login_pwdEt)
	EditText mPwdEt; //密码
	@Bind(R.id.custom_login_pwd_cancelIv)
	ImageView mPwdCancelIv;
	public boolean mPhoneNumIsUseful; //账号是否可用的标识
	public boolean mPwdIsUseful; //密码是否可用的标识

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflateAndSetupView(inflater, container, savedInstanceState, R.layout.njl_login_custom_fragment_layout);
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
		mAccountNumberEt.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				Log.i("onTextChanged", s + " " + start + " " + before + " " + count);
				int length = s.length();
				String num = s.toString();
				Log.i("isPhone", "isPhone:" + Utils.isPhone(num));
				Log.i("isEmail", "isEmail:" + Utils.isEmail(num));

				if (length != 0) {
					if (!mAccountNumberCancelIv.isShown()) {
						mAccountNumberCancelIv.setVisibility(View.VISIBLE);
					}
					//验证是否是手机号或者邮箱
					if(Utils.isPhone(num) || Utils.isEmail(num))
						mPhoneNumIsUseful = true;
					else
						mPhoneNumIsUseful = false;
				} else {
					if (mAccountNumberCancelIv.isShown()) {
						mAccountNumberCancelIv.setVisibility(View.GONE);
					}
				}
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

		mAccountNumberCancelIv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mAccountNumberEt.setText("");
				mAccountNumberCancelIv.setVisibility(View.GONE);
				mPhoneNumIsUseful = false;
			}
		});

		mPwdEt.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				Log.i("onTextChanged", s + " " + start + " " + before + " " + count);
				int length = s.length();
				if (length != 0) {
					if (!mPwdCancelIv.isShown()) {
						mPwdCancelIv.setVisibility(View.VISIBLE);
					}
					if(length>=6 && length<=30)
						mPwdIsUseful = true;
					else
						mPwdIsUseful = false;
				} else {
					if (mPwdCancelIv.isShown()) {
						mPwdCancelIv.setVisibility(View.GONE);
					}
				}
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

		mPwdCancelIv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mPwdEt.setText("");
				mPwdCancelIv.setVisibility(View.GONE);
				mPwdIsUseful = false;
			}
		});
	}

}
