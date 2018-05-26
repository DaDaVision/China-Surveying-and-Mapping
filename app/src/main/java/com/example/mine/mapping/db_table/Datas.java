package com.example.mine.mapping.db_table;

import org.litepal.crud.DataSupport;

/**
 * Created by dell on 2018/3/31.
 */

public class Datas extends DataSupport {
    private int dataId;
    private int userId;
    private String startTime;
    private String currTime;
    private String testType;
    private String dataTest;
    private String dataReal;

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getCurrTime() {
        return currTime;
    }

    public void setCurrTime(String currTime) {
        this.currTime = currTime;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getDataTest() {
        return dataTest;
    }

    public void setDataTest(String dataTest) {
        this.dataTest = dataTest;
    }

    public String getDataReal() {
        return dataReal;
    }

    public void setDataReal(String dataReal) {
        this.dataReal = dataReal;
    }

    @Override
    public String toString() {
        return "Datas{" +
                "dataId=" + dataId +
                ", userId=" + userId +
                ", startTime='" + startTime + '\'' +
                ", currTime='" + currTime + '\'' +
                ", testType='" + testType + '\'' +
                ", dataTest='" + dataTest + '\'' +
                ", dataReal='" + dataReal + '\'' +
                '}';
    }
}
