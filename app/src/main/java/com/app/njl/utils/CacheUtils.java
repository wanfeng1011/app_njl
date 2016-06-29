package com.app.njl.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class CacheUtils {
	public static final String KEY_MEMBER_PIC_URL = "key_member_pic_url"; //用户头像图片
	public static final String KEY_TOKEN = "key_token"; //用户登录token
	public static final String KEY_MEMBER_NAME = "key_member_name"; //用户名
	public static final String KEY_LOGIN_TYPE = "key_login_type"; //登录的方式（1、普通登录 2、qq登录 3、新浪登录）

	/**
	 * sharedpreference工具类
	 */
	private static final String NAME = "BdApp";
	private static SharedPreferences sp;

	// 存布尔值
	public static void putBoolean(Context context, String key, Boolean value) {
		if (sp == null) {
			sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
		}
		sp.edit().putBoolean(key, value).commit();
	}

	// 取布尔值
	public static Boolean getBoolean(Context context, String key,
			Boolean defValue) {
		if (sp == null) {
			sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
		}
		return sp.getBoolean(key, defValue);
	}

	// 存String值
	public static void putString(Context context, String key, String value) {
		if (sp == null) {
			sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
		}
		sp.edit().putString(key, value).commit();
	}

	// 取String值
	public static String getString(Context context, String key, String defValue) {
		if (sp == null) {
			sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
		}
		return sp.getString(key, defValue);
	}
}
