package com.example.FetchingData.RestObject;

import com.example.FetchingData.DataBase.ClgStudent;

import java.util.List;

public class StudentRestObject {
    private int totalResult;
    private List<ClgStudent> restStudentList;

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public List<ClgStudent> getRestStudentList() {
        return restStudentList;
    }

    public void setRestStudentList(List<ClgStudent> restStudentList) {
        this.restStudentList = restStudentList;
    }

    @Override
    public String toString() {
        return "StudentRestObject{" +
                "totalResult='" + totalResult + '\'' +
                ", restStudentList=" + restStudentList +
                '}';
    }
}
