package com.example.mine.mapping.utils.http;

import com.example.mine.mapping.utils.zgch.bean.DeviceList;
import com.example.mine.mapping.utils.zgch.bean.SimpleResult;
import com.example.mine.mapping.utils.zgch.bean.UserInfo;
import com.google.gson.Gson;


/**
 * 根据tag将服务器返回的数据加工成不同的object
 */
public class APIDataFactory {
	
	private GetPostUtil getPostUtil=new GetPostUtil();
	private String response;
	private String url;
	private String param;
	private SimpleResult simpleResult;
    private UserInfo userInfo;
    private DeviceList deviceList;
	private Gson gson=new Gson();
	public APIDataFactory(String url,String param){
		this.url=url;
		this.param=param;
	}
	/**
	 * 根据tag判断何种方式访问网络 get或者post  并加工成object
	 * @param tag
	 * @return
	 */
  public Object createMsgObject(int tag){
		if (tag == EAPIConsts.ReqType.LOGIN
				|| tag == EAPIConsts.ReqType.REGISTER
				|| tag == EAPIConsts.ReqType.ADDDEVICE
				|| tag == EAPIConsts.ReqType.UPDATEDEVICE
				|| tag == EAPIConsts.ReqType.ACTIONPUBLIC) {
			response = getPostUtil.sendPost(url, param);
			simpleResult = gson.fromJson(response, SimpleResult.class);
			return simpleResult;
		}

		else if(tag == EAPIConsts.ReqType.GETUSERINFO ){
			response=getPostUtil.sendGet(url, param);
			userInfo=gson.fromJson(response, UserInfo.class);
			return userInfo;
		} else if(tag == EAPIConsts.ReqType.GETALLDEVICE) {
			response=getPostUtil.sendGet(url, param);
			deviceList = gson.fromJson(response, DeviceList.class);
			return deviceList;
		}

		return null;
  }
 
}
