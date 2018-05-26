package com.example.mine.mapping.utils.common;

import android.app.Application;
import android.content.Context;

/**
 * 获得该应用的 全局对象
 *
 */
public class MyApplication extends Application {

	private static Context context;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		context=getApplicationContext();
	}

	public static Context getApp(){
		return context;
	}
}
