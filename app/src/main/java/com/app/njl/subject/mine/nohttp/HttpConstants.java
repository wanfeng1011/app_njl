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
	public static final byte QUERY_SHOPDETAIL_BYOPTIONS = 0x11; //获取店家所有住宿信息
	public static final byte QUERY_SHOPORDER_DETAIL_BYOPTIONS = 0x12; //获取店家食物的信息
	public static final byte QUERY_SHOPPLAY_DETAIL_BYOPTIONS = 0x13; //获取店家所有游玩信息
	public static final byte QUERY_SHOPSPECIALTY_DETAIL_BYOPTIONS = 0x14; //获取店家所有特产信息
	public static final byte QUERY_SHOPROOM_BYOPTIONS = 0x15; //获取房间信息
	public static final byte QUERY_SHOPROOMPICS_BYOPTIONS = 0x16; //房间图片信息
	public static final byte QUERY_SHOPCOURSE_BYOPTIONS = 0x17; //获取菜品信息
	public static final byte QUERY_SHOPCOURSEPICS_BYOPTIONS = 0x18; //菜品图片信息
	public static final byte QUERY_SHOPSPECIAL_BYOPTIONS = 0x19; //特产信息
	public static final byte QUERY_SHOPSPECIALEPICS_BYOPTIONS = 0x20; //特产图片信息
	public static final byte QUERY_SHOPPLAY_TICKETMESSAGE_BYOPTIONS = 0x21; //游玩的门票信息
	public static final byte QUERY_SHOPPLAY_TICKETMESSAGE_PIC_BYOPTIONS = 0x21; //游玩的门票图片
	public static final byte QUERY_SHOPPLAY_SELFSERVICE_BYOPTIONS = 0x22; //游玩的自助游
	public static final byte QUERY_SHOPPLAY_SELFSERVICE_PIC_BYOPTIONS = 0x23; //游玩的自助游图片
	public static final byte QUERY_SHOPPLAY_TOURGUIDE_BYOPTIONS = 0x24; //游玩的导游
	public static final byte QUERY_SHOPPLAY_TOURGUIDE_PIC_BYOPTIONS = 0x25; //游玩的导游图片
	public static final byte QUERY_STORE_PICS_BYOPTIONS = 0x26; //店家图片

	public static final byte GET_POPULARSHOPLIST = 0x34; //获取人气展示列表
}
