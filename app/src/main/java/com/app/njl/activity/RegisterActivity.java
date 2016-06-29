package com.app.njl.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.njl.R;
import com.app.njl.base.BaseActivity;
import com.app.njl.subject.mine.nohttp.CallServer;
import com.app.njl.subject.mine.nohttp.Constants;
import com.app.njl.subject.mine.nohttp.HttpConstants;
import com.app.njl.subject.mine.nohttp.HttpListener;
import com.app.njl.subject.mine.nohttp.StringRequestImpl;
import com.app.njl.utils.Toast;
import com.app.njl.utils.Utils;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.Response;

import butterknife.Bind;

/**
 * Created by jiaxx on 2016/4/21 0021.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener, HttpListener<String> {
    @Bind(R.id.title)
    TextView tv_title;
    @Bind(R.id.back_ll)
    LinearLayout back_ll;
    @Bind(R.id.register_phoneNum_et)
    EditText mPhoneNumEt;
    @Bind(R.id.register_phoneNum_cancelIv)
    ImageView mPhoneNumCancelIv;
    @Bind(R.id.register_pwd_et)
    EditText mPwdEt;
    @Bind(R.id.register_pwd_cancelIv)
    ImageView mPwdCancelIv;
    @Bind(R.id.register_code_et)
    EditText mCodeEt; //验证码
    @Bind(R.id.register_code_btn)
    Button mCodeBtn; //获取验证码按钮
    @Bind(R.id.register_sureBtn)
    Button mSureBtn; //确认并注册按钮
    private String numberStr;
    private String pwdStr;
    private String codeStr;
    boolean mPhoneNumIsUseful; //手机号是否可用的标识
    boolean mPwdIsUseful; //密码是否可用的标识
    boolean mCodeIsUseful; //验证码是否可用的标识
    boolean mPwdType; //密码模式
    TimeCount time; //倒计时
    boolean isInTime = false; //是否在倒计时中

    @Override
    protected void initContentView() {
        setContentView(R.layout.njl_layout_register_next);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        tv_title.setText("注册");
        back_ll.setOnClickListener(this);
        mSureBtn.setOnClickListener(this);
        mCodeBtn.setOnClickListener(this);


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
                        if (!isInTime) {
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

        mPwdEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("onTextChanged", s + " " + start + " " + before + " " + count + " " + s.length());
                int length = s.length();
                if (length != 0) {
                    if (length >= 6 && length <= 30)
                        mPwdIsUseful = true;
                    else
                        mPwdIsUseful = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPwdCancelIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*mPwdEt.setText("");
                mPwdCancelIv.setVisibility(View.INVISIBLE);
                mPwdIsUseful = false;*/
                if (!mPwdType) {
                    mPwdCancelIv.setBackgroundResource(R.mipmap.eye_opened);
                    mPwdEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    mPwdCancelIv.setBackgroundResource(R.mipmap.eye_closed);
                    mPwdEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                mPwdType = !mPwdType;
                mPwdEt.postInvalidate();
                //切换后将EditText光标置于末尾
                CharSequence charSequence = mPwdEt.getText();
                if (charSequence instanceof Spannable) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());
                }
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
                if (length == 4)
                    mCodeIsUseful = true;
                else
                    mCodeIsUseful = false;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        int code = 0;
        String result = response.get();
        switch (what) {
            case HttpConstants.GET_REGISTER_CODE:
                Log.i("result", "result get code:" + result);
                break;
            case HttpConstants.USER_REGISTER:
                Log.i("result", "result user resister:" + result);
                break;
        }
    }

    @Override
    public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
        exception.printStackTrace();
        Log.i("result", "result:" + exception);
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

    @Override
    protected void initControl() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_ll:
                finish();
                break;
            case R.id.register_sureBtn:
                checkPhonePwdCode();
                break;
            case R.id.register_code_btn:
                Log.i("result", "result get code start");
                getCode();
                time = new TimeCount(10000, 1000);//构造CountDownTimer对象
                time.start();
                break;
        }
    }

    /**
     * 验证手机号，密码，验证码是否输入或者是否合法
     */
    private void checkPhonePwdCode() {
        //检测手机号是否合法
        if(!mPhoneNumIsUseful) {
            Toast.show("请输入正确的手机号码");
            return;
        }
        //检测密码
        if(!mPwdIsUseful) {
            Toast.show("请输入6-30位数字或者字母");
            return;
        }
        //检测验证码
        if(!mCodeIsUseful) {
            Toast.show("请输入正确的验证码");
            return;
        }
        Toast.show("验证通过，可以注册");
        TelephonyManager telephonyManager=(TelephonyManager) RegisterActivity.this.getSystemService(Context.TELEPHONY_SERVICE);
        String imei=telephonyManager.getDeviceId();
        userRegister(mPhoneNumEt.getText().toString(), mPwdEt.getText().toString(), mCodeEt.getText().toString(), imei);
    }

    /**
     * 获取验证码
     */
    private void getCode() {
        Request<String> mRequest = new StringRequestImpl(Constants.BASE_PATH + "user/sendSmsCode", RequestMethod.POST);
        mRequest.add("phoneNumber", "17755170613");// String类型
        mRequest.add("expireSeconds", 600000);
        mRequest.add("isUserExist", 0);// int类型
        Log.i("result", "result get code is doing");
        // 添加到请求队列
        CallServer.getRequestInstance().add(this, HttpConstants.GET_REGISTER_CODE, mRequest, this, true, true);
    }

    /**
     * 用户注册
     */
    private void userRegister(String number, String  passwd, String smsCode, String deviceId) {
        Log.i("register", "result register:" + number + " " + smsCode + " " + passwd + " " + deviceId);
        Request<String> mRequest = new StringRequestImpl(Constants.BASE_PATH + "user/add", RequestMethod.POST);
        mRequest.add("phoneNumber", number);// String类型
        mRequest.add("smsCode", Integer.parseInt(smsCode)); //验证码
        mRequest.add("passwd", passwd);// 密码
        mRequest.add("deviceId", deviceId); //设备id
        mRequest.add("expireDays", 2); //过期时间

        // 添加到请求队列
        CallServer.getRequestInstance().add(this, HttpConstants.USER_REGISTER, mRequest, this, true, true);
    }
}
