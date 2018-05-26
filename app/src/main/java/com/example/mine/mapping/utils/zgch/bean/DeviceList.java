package com.example.mine.mapping.utils.zgch.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DeviceList implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private int userId;
	private List<Devices> list = new ArrayList<Devices>();
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<Devices> getList() {
		return list;
	}
	public void setList(List<Devices> list) {
		this.list = list;
	}

}
