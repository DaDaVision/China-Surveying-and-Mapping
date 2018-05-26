package com.example.mine.mapping.utils.http;

public class EAPIConsts {
	private final static int DEVPUBLIC = 1;  //开发（内网）
	private final static int ONLINE=2;      //上线
	
		public static int ENVIRONMENT = DEVPUBLIC; // api接口地址
		
		// 内网开发环境地址
		public static final String LAN_URL_DEVPUBLIC = "http://172.16.153.18:8086/zgch";
		//外网开发环境地址
		public static final String WAN_URL_DEVPUBLIC = "http://172.16.153.18:8086/zgch";
		
		// 普通接口地址（调用方法）
		public static final String COMMON_URL = getCOMMONSUrl();
		
		public static String getCOMMONSUrl(){
			switch (ENVIRONMENT) {
			case DEVPUBLIC:  //局域网
				return LAN_URL_DEVPUBLIC;
			case ONLINE: //广域网 （上线）
				return WAN_URL_DEVPUBLIC;
			default:
				return WAN_URL_DEVPUBLIC;
			}
		}
		/**
		 * 请求地址的URL（后面的串）
		 * @author 
		 */
		public static class ReqUrl {
			public static final String LOGIN = "/userResource/login/"; // 登录  后加 gson用户信息
			public static final String REGISTER = "/userResource/register/"; //注册    后加 gson用户信息
			public static final String GETUSERINFO="/userResource/getUserInfoById/"; //获取用户信息 后加  userid
			public static final String ADDDEVICE="/deviceResource/addDevice/"; //添加设备
            public static final String UPDATEDEVICE = "/deviceResource/updateDevice";//更新设备
			public static final String ACTIONPUBLIC="/deviceResource/controlDevice/"; //控制设备
			public static final String GETALLDEVICE="/deviceResource/findAllDeviceByUserId/";//获取所有设备
			public static final String DELETEDEVICE = "/deviceResource/deleteDeviceById/";//删除设备
		}
		/**
		 * 用作数据的标记
		 * @author 
		 */
		public static class ReqType{
			public static final int LOGIN=1;//登录
			public static final int REGISTER=2;//注册
			public static final int GETUSERINFO=3;//获取用户信息
			public static final int ADDDEVICE=4;//添加设备
            public static final int UPDATEDEVICE=5;//更新设备
			public static final int ACTIONPUBLIC=6;//发送控制指令
			public static final int GETALLDEVICE = 7;//获取所有设备
			public static final int DELETEDEVICE = 8;//删除设备
		}
}
