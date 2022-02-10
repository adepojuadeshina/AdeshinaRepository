package com.etz.comparedoc.model;

import javax.persistence.*;

@Entity
@Table(name="documents")
public class Doc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String studentName;
    @Lob
    private byte[] data;

    public Doc(){

    }

    public Doc(String studentName, byte[] data) {
        this.studentName = studentName;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}

