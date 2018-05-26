package com.example.mine.mapping.utils.zgch.api;

import android.content.Context;
import android.os.Handler;

import com.example.mine.mapping.utils.http.APIAsyncTask;
import com.example.mine.mapping.utils.http.IBindData;


/**
 * 网络请求入口类
 *
 */
 
public class ReqBase {
	/**
	 * 网络请求的通用接口
	 * @param context
	 * @param bind
	 * @param param
	 * @param handler
	 * @param url
	 * @param type
	 */
	public static void doExecute(Context context, IBindData bind, String param,
								 Handler handler, String url, int type) {
		if(handler == null){
			handler =  new HandlerAPI();
		}
		APIAsyncTask.doExecute(context,bind,param,handler,url,type);
	}
}
