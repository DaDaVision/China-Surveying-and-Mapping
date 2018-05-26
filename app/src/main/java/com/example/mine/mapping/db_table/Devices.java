package com.example.mine.mapping.db_table;

import org.litepal.crud.DataSupport;

/**
 * Created by dell on 2018/3/31.
 */

public class Devices extends DataSupport {
    private int deviceId;
    private String deviceName;
    private String deviceType;
    private int userId;
    private String mac;
    private byte online;
    private byte deviceStatus;

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public byte getOnline() {
        return online;
    }

    public void setOnline(byte online) {
        this.online = online;
    }

    public byte getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(byte deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    @Override
    public String toString() {
        return "Devices{" +
                "deviceId=" + deviceId +
                ", deviceName='" + deviceName + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", userId=" + userId +
                ", mac='" + mac + '\'' +
                ", online=" + online +
                ", deviceStatus=" + deviceStatus +
                '}';
    }
}
