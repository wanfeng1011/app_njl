package com.app.njl.subject.mine.nohttp;

public class HttpConstants {
	public static final byte USER_REGISTER = 0x01; //用户注册
	public static final byte GET_REGISTER_CODE = 0x02; //用户获取注册验证码
	public static final byte get_phone_verifictioncode = 0x03; //获取手机登录验证码
	public static final byte findback_password = 0x04; //找回密码
	public static final byte mobile_register = 0x05; //手机注册
	public static final byte get_my_message = 0x06; //获取我的界面信息
	public static final byte QUERY_ALL_SCENERY = 0x07; //获取所有景点
	public static final byte QUERY_HOT_SCENERY = 0x08; //获取热门景点
	public static final byte QUERY_STORES_BYOPTIONS = 0x09; //获取景点的所有店家列表
	public static final byte QUERY_STORE_LANDMARKS = 0x10; //获取景区地标
	public static final byte GET_POPULARSHOPLIST = 0x34; //获取人气展示列表
}
