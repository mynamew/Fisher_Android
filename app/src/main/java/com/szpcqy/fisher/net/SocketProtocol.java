package com.szpcqy.fisher.net;

public class SocketProtocol {

	//登录
	public static final int LOGIN_REQ = 0X100001;
	public static final int LOGIN_RES = 0X100002;
	//获取所有游戏机信息
	public static final int GET_DESK_ALL_REQ = 0X100003;
	public static final int GET_DESK_ALL_RES = 0X100004;
	//获取某台游戏机信息
	public static final int GET_DESK_SINGLE_REQ = 0X100005;
	public static final int GET_DESK_SINGLE_RES = 0X100006;
	//加入控制位
	public static final int JOIN_SLOT_REQ = 0X100007;
	public static final int JOIN_SLOT_RES = 0X100008;
	//切换大炮
	public static final int SWITCH_STRENTH_REQ = 0X100009;
	public static final int SWITCH_STRENTH_RES = 0X100010;
	//方向和发射
	public static final int DIRECTION_FIRE_REQ = 0X100011;
	public static final int DIRECTION_FIRE_RES = 0X100012;
	//投币
	public static final int COININ_REQ = 0X100013;
	public static final int COININ_RES = 0X100014;
	//退币
	public static final int COINOUT_REQ = 0X100015;
	public static final int COINOUT_RES = 0X100016;
	//退出控制位
	public static final int QUIT_SLOT_REQ = 0X100017;
	public static final int QUIT_SLOT_RES = 0X100018;




	//账号被迫下线
	public static final int KICK_OUT_RES = 0X200001;
	//游戏设备信息更新-加入+开关+机位开关等等
	public static final int DEVICE_UPDATE_RES = 0X200002;


}

