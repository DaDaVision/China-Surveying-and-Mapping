package com.example.mine.mapping.db_table;

import org.litepal.crud.DataSupport;

/**
 * Created by dell on 2018/3/31.
 */

public class AnalysisResult extends DataSupport {

    private int Id;
    private String startTime;
    private String testType;
    private String SER;
    private int userId;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getSER() {
        return SER;
    }

    public void setSER(String SER) {
        this.SER = SER;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "AnalysisResult{" +
                "Id=" + Id +
                ", startTime='" + startTime + '\'' +
                ", testType='" + testType + '\'' +
                ", SER='" + SER + '\'' +
                ", userId=" + userId +
                '}';
    }
}
