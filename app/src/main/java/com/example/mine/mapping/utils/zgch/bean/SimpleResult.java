package com.example.mine.mapping.utils.zgch.bean;

import java.io.Serializable;

/**
 * 客户端在登录或者注册时返回成功与否的信息
 */
public class SimpleResult implements Serializable {
	private boolean result;
	private String reason;  //失败原因或者是传递的信息
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}
