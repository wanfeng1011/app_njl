package com.app.njl.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.njl.R;
import com.app.njl.base.BaseActivity;
import com.app.njl.subject.mine.nohttp.CallServer;
import com.app.njl.subject.mine.nohttp.Constants;
import com.app.njl.subject.mine.nohttp.HttpConstants;
import com.app.njl.subject.mine.nohttp.HttpListener;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.Response;

/**
 * 找回密码
 * @author Administrator
 *
 */
public class FindBackPassWordActivity extends BaseActivity implements HttpListener<String> {
	private ImageView mBackIv;
	private EditText mPhoneNumberEt; //手机号码
	private EditText mVerificationCodeEt; //验证码
	private Button mGetVerificationCodeBtn; //获取验证码按钮
	private EditText mFirstPwdEt; //第一遍密码
	private EditText mConfimPwdEt; //密码确认
	private Button mSureBtn; //提交按钮
	
	private String mPhoneNumberStr; //手机号码字符串
	private String mVerificationCodeStr; //验证码字符串
	private String mFirstPwdStr; //第一遍密码字符串
	private String mConfimPwdStr; //确认密码字符串
	private Request<String> mRequest;

	@Override
	protected void initContentView() {
		setContentView(R.layout.find_back_password_layout);
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		initView();
	}

	@Override
	protected void initControl() {

	}
	
	/**
	 * 控件初始化
	 */
	private void initView() {
		mBackIv = (ImageView)findViewById(R.id.back_iv);
		mPhoneNumberEt = (EditText)findViewById(R.id.phone_et);
		mVerificationCodeEt = (EditText)findViewById(R.id.verificationCode_et);
		mGetVerificationCodeBtn = (Button)findViewById(R.id.get_verificationCode_btn);
		mFirstPwdEt = (EditText)findViewById(R.id.first_password_et);
		mConfimPwdEt = (EditText)findViewById(R.id.password_confirm_et);
		mSureBtn = (Button)findViewById(R.id.btnSure);
		mGetVerificationCodeBtn.setOnClickListener(this);
		mSureBtn.setOnClickListener(this);
		mBackIv.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back_iv:
			finish();
			break;
		case R.id.get_verificationCode_btn: //获取验证码
			//验证输入的手机号是否为空
			mPhoneNumberStr = mPhoneNumberEt.getText().toString();
			if(TextUtils.isEmpty(mPhoneNumberStr)) {
				Toast.makeText(FindBackPassWordActivity.this, "请输入手机号码...", Toast.LENGTH_SHORT).show();
			}else {				
				mRequest = NoHttp.createStringRequest(Constants.BASE_PATH + "sendSMS/code", RequestMethod.POST);

		        mRequest.add("memberMobile", mPhoneNumberStr);
		        mRequest.add("msgType", "4");
		        mRequest.add("versionNumber", "0.0.1");
		        mRequest.add("versionType", "1");

		        // 添加到请求队列
		        CallServer.getRequestInstance().add(this, HttpConstants.get_phone_verifictioncode, mRequest, this, true, true);
			}
			break;
		case R.id.btnSure: //找回密码
			//验证输入的手机号、验证码、新密码等是否为空
			mPhoneNumberStr = mPhoneNumberEt.getText().toString();
			mVerificationCodeStr = mVerificationCodeEt.getText().toString();
			mFirstPwdStr = mFirstPwdEt.getText().toString();
			mConfimPwdStr = mConfimPwdEt.getText().toString();
			if(TextUtils.isEmpty(mPhoneNumberStr)) {
				Toast.makeText(FindBackPassWordActivity.this, "请输入手机号码...", Toast.LENGTH_SHORT).show();
			}else if(TextUtils.isEmpty(mVerificationCodeStr)) {
				Toast.makeText(FindBackPassWordActivity.this, "请输入验证码...", Toast.LENGTH_SHORT).show();
			}else if(TextUtils.isEmpty(mFirstPwdStr)) {
				Toast.makeText(FindBackPassWordActivity.this, "请输入密码...", Toast.LENGTH_SHORT).show();
			}else if(TextUtils.isEmpty(mConfimPwdStr)) {
				if(!mConfimPwdStr.equals(mFirstPwdEt)) {
					Toast.makeText(FindBackPassWordActivity.this, "两次密码不一样，请从新输入...", Toast.LENGTH_SHORT).show();
				}
			}else {			
				mRequest = NoHttp.createStringRequest(Constants.BASE_PATH + "searchPasswd", RequestMethod.POST);

		        mRequest.add("memberMobile", mPhoneNumberStr);
		        mRequest.add("code", mVerificationCodeStr);
		        mRequest.add("newPasswd", mFirstPwdStr);
		        mRequest.add("versionNumber", "0.0.1");
		        mRequest.add("versionType", "1");

		        // 添加到请求队列
		        CallServer.getRequestInstance().add(this, HttpConstants.findback_password, mRequest, this, true, true);
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void onSucceed(int what, Response<String> response) {

	}

	@Override
	public void onFailed(int what, String url, Object tag, Exception exception,
			int responseCode, long networkMillis) {

	}

}
