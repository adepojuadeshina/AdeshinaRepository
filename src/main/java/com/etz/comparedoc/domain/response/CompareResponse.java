package com.etz.comparedoc.domain.response;

import javax.persistence.*;

@Entity
@Table(name = "comparedresponse")
public class CompareResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstStudentName;
    private String secondStudentName;
    private String similarLineText;
    private String percentageSimilarities;

    public CompareResponse() {
    }

    public CompareResponse(String firstStudentName, String secondStudentName, String similarLineText, String percentageSimilarities) {
        this.firstStudentName = firstStudentName;
        this.secondStudentName = secondStudentName;
        this.similarLineText = similarLineText;
        this.percentageSimilarities = percentageSimilarities;
    }

    public String getFirstStudentName() {
        return firstStudentName;
    }

    public void setFirstStudentName(String firstStudentName) {
        this.firstStudentName = firstStudentName;
    }

    public String getSecondStudentName() {
        return secondStudentName;
    }

    public void setSecondStudentName(String secondStudentName) {
        this.secondStudentName = secondStudentName;
    }

    public String getSimilarLineText() {
        return similarLineText;
    }

    public void setSimilarLineText(String similarLineText) {
        this.similarLineText = similarLineText;
    }

    public String getPercentageSimilarities() {
        return percentageSimilarities;
    }

    public void setPercentageSimilarities(String percentageSimilarities) {
        this.percentageSimilarities = percentageSimilarities;
    }
}
