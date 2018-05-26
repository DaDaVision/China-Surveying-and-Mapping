package com.example.mine.mapping.utils.zgch.api;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.example.mine.mapping.utils.zgch.bean.Control;
import com.google.gson.Gson;
import com.example.mine.mapping.utils.http.EAPIConsts;
import com.example.mine.mapping.utils.http.IBindData;
import com.example.mine.mapping.utils.zgch.bean.Devices;


/**
 * 设备请求网络的接口
 */
public class DeviceReqUtil extends ReqBase {

	//添加设备
	public static void doAddDevice(Context context, IBindData bind,
								   Devices devices, Handler handler){
		String url = EAPIConsts.COMMON_URL+EAPIConsts.ReqUrl.ADDDEVICE;
//		System.out.println(url);
		Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
		String param = getDeviceParams(devices);
		doExecute(context,bind,param,handler,url,EAPIConsts.ReqType.ADDDEVICE);
	}


	//更新设备
	public static void doUpdateDevice(Context context, IBindData bind,
								   Devices devices, Handler handler){
		String url = EAPIConsts.COMMON_URL+EAPIConsts.ReqUrl.UPDATEDEVICE;
		//		System.out.println(url);
		Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
		String param = getDeviceParams(devices);
		doExecute(context,bind,param,handler,url,EAPIConsts.ReqType.UPDATEDEVICE);
	}

	//更新设备
	public static void doGetAllDeviceById(Context context, IBindData bind,
									  int userId, Handler handler){
		String url = EAPIConsts.COMMON_URL+EAPIConsts.ReqUrl.GETALLDEVICE + userId;
		//		System.out.println(url);
		Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
//		String param = getDeviceParams(devices);
		doExecute(context,bind,"",handler,url,EAPIConsts.ReqType.GETALLDEVICE);
	}

	//发送控制器的控制指令
	public static void doAcionControl(Context context, IBindData bind,
									  Control control, Handler handler){
		String url = EAPIConsts.COMMON_URL + EAPIConsts.ReqUrl.ACTIONPUBLIC;
//		System.out.println(url);
		String param =getControlParams(control);
		doExecute(context,bind,param,handler,url,EAPIConsts.ReqType.ACTIONPUBLIC);
	}


	/**
	 * 工具函数
	 * @param devices
	 * @return
	 */
	public static String getDeviceParams(Devices devices){
		Gson gson=new Gson();
		String params = gson.toJson(devices);
		return "devicesInfo=" + params;
	}

	public static String getControlParams(Control control){
		Gson gson=new Gson();
		String params = gson.toJson(control);
		return "devicesInfo=" + params;
	}

}
