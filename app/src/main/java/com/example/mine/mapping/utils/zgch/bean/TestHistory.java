package com.example.mine.mapping.utils.zgch.bean;

import java.io.Serializable;

/**
 * Created by dell on 2018/3/31.
 */

public class TestHistory implements Serializable {
    private int testId;
    private int userId;
    private String testType;
    private String discription;
    private String phone;
    private String testTime;

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTestTime() {
        return testTime;
    }

    public void setTestTime(String testTime) {
        this.testTime = testTime;
    }

    @Override
    public String toString() {
        return "TestHistory{" +
                "testId=" + testId +
                ", userId=" + userId +
                ", testType='" + testType + '\'' +
                ", discription='" + discription + '\'' +
                ", phone='" + phone + '\'' +
                ", testTime='" + testTime + '\'' +
                '}';
    }
}
