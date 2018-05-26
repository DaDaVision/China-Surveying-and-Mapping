package com.example.mine.mapping.db_table;

import org.litepal.crud.DataSupport;


public class Control extends DataSupport {
	
	private int userId;
	private int deviceId;
	private String cmd;
	private String controlTime;
	
	
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
	@Override
	public String toString() {
		return "Control [userId=" + userId + ", deviceId=" + deviceId
				+ ", cmd=" + cmd + ", controlTime=" + controlTime + "]";
	}
	
	
}
