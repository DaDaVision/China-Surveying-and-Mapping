package com.example.mine.mapping.utils.zgch.api;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.example.mine.mapping.utils.common.CommonUtil;

/**
 * 在请求网络时  传进次参数，可以直接给UI主线程发送  Message
 *
 */
public class HandlerAPI extends Handler {

	public HandlerAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HandlerAPI(Looper looper) {
		super(looper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		switch (msg.what) {  
        case 0: {  
            Bundle bundle = (Bundle)msg.getData();
            String error = (String)bundle.getString("error");
            CommonUtil.showToast(error);  //显示错误的提示
        }
            break;  
        
        default:  
            break;  

        } 
	}
	
	
}
