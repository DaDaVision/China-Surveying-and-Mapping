package com.example.mine.mapping.utils.zgch.bean;

import java.io.Serializable;

/**
 * Created by dell on 2018/3/31.
 */

public class ReferencePoint implements Serializable {
    private int Id;
    private String pointName;
    private String pointDescription;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public String getPointDescription() {
        return pointDescription;
    }

    public void setPointDescription(String pointDescription) {
        this.pointDescription = pointDescription;
    }

    @Override
    public String toString() {
        return "ReferencePoint{" +
                "Id=" + Id +
                ", pointName='" + pointName + '\'' +
                ", pointDescription='" + pointDescription + '\'' +
                '}';
    }
}
