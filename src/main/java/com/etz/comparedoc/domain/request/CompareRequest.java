package com.etz.comparedoc.domain.request;

public class CompareRequest {

    private int firstStudentId;
    private int secondStudentId;

    public int getFirstStudentId() {
        return firstStudentId;
    }

    public void setFirstStudentId(int firstStudentId) {
        this.firstStudentId = firstStudentId;
    }

    public int getSecondStudentId() {
        return secondStudentId;
    }

    public void setSecondStudentId(int secondStudentId) {
        this.secondStudentId = secondStudentId;
    }
}
