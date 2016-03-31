package com.app.njl.fragment;


import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.njl.R;
import com.app.njl.activity.BaseFragmentActivity;
import com.app.njl.utils.Validator;
import com.lostad.applib.util.EffectUtil;

public class LoginActivity extends BaseFragmentActivity {

    private TextView et_phone;

    private TextView et_password;


    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loast_activity_login);
        et_phone = (TextView)findViewById(R.id.et_phone);
        et_password = (TextView)findViewById(R.id.et_password);
        btn_login = (Button)findViewById(R.id.btn_login);
        //使用背景图片浸染
        setStatusBarStyle(R.color.transparent);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLoginByPhone(v);
            }
        });
    }

    private void onClickLoginByPhone(View v) {

        String username = et_phone.getText().toString();
        String pwd = et_password.getText().toString();
        if (Validator.isBlank(username)) {
            et_phone.requestFocus();
            et_phone.setError(Html.fromHtml("<font color=#FFFFFF>手机号不能为空</font>"));
            EffectUtil.showShake(this, et_phone);

            /*String errorText = "出错啦~~！";
//获取到自定义图标
            Drawable errorIcon = getResources().getDrawable(R.mipmap.logo);
// 设置图片大小
            errorIcon.setBounds(new Rect(0, 0, errorIcon.getIntrinsicWidth(),
                    errorIcon.getIntrinsicHeight()));
// 将提示文字改为红色
            ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.RED);
            SpannableStringBuilder ssbuilder = new SpannableStringBuilder(errorText);
            ssbuilder.setSpan(fgcspan, 0, errorText.length(), 0);
//显示
            et_phone.setError(ssbuilder, errorIcon);
            et_phone.requestFocus();*/
            return;
        }

        if (!Validator.isMobile(username)) {
            et_phone.setError(Html.fromHtml("<font color=#FFFFFF>手机号不正确</font>"));
            et_phone.requestFocus();
            et_phone.setText("");
            return;
        }
//        LoginConfig mLoginConfig = new LoginConfig();
//        mLoginConfig.setPhone(username);
        if (Validator.isBlank(pwd)) {
            et_password.setError(Html.fromHtml("<font color=#FFFFFF>请输入验密码</font>"));
            et_password.requestFocus();
            EffectUtil.showShake(this, et_password);
            return;
        }

    }

}

