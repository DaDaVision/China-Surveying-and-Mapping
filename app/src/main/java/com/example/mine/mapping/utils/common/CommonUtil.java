package com.example.mine.mapping.utils.common;

import android.widget.Toast;

import org.litepal.LitePalApplication;

public class CommonUtil {

	// 短显示消息 (传进来的参数没有 Context )
	public static void showToast(String text) {
		Toast.makeText(LitePalApplication.getContext(), text, Toast.LENGTH_SHORT).show();
	}

	// 长显示消息 (传进来的参数没有 Context )
	public static void showLongToast(String text) {
		Toast.makeText(LitePalApplication.getContext(), text, Toast.LENGTH_SHORT).show();
	}

}
