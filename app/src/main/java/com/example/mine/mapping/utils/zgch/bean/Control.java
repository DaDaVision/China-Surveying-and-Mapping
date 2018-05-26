package com.example.mine.mapping.utils.zgch.bean;

import java.io.Serializable;


public class Control implements Serializable {
	
	private int userId;
	private int deviceId;
	private String cmd;
	private String controlTime;
	private String ip;
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	
	public String getControlTime() {
		return controlTime;
	}
	public void setControlTime(String controlTime) {
		this.controlTime = controlTime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Override
	public String toString() {
		return "Control [userId=" + userId + ", deviceId=" + deviceId
				+ ", cmd=" + cmd + ", controlTime=" + controlTime + ", ip="
				+ ip + "]";
	}
	
}
