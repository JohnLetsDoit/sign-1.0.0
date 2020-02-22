package com.tsw.sign.message.modle;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Message implements Serializable {
    private Integer id;
    private String signname;
    private String Gender;
    private String birthPlace;
    private Date dayOfBirth;
    private String College;
    private String Classes;
    private String signDepartment;
    private float Grade;
    private String phone;
    private String qq;
    private String hobby;
    private String signreason;
    private Date signTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSignname() {
        return signname;
    }

    public void setSignname(String signname) {
        this.signname = signname;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(Date dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }
    public String getCollege() {
        return College;
    }

    public void setCollege(String college) {
        College = college;
    }

    public String getClasses() {
        return Classes;
    }

    public void setClasses(String classes) {
        Classes = classes;
    }
    public String getSignDepartment() {
        return signDepartment;
    }

    public void setSignDepartment(String signDepartment) {
        this.signDepartment = signDepartment;
    }

    public float getGrade() {
        return Grade;
    }

    public void setGrade(float grade) {
        Grade = grade;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getSignreason() {
        return signreason;
    }

    public void setSignreason(String signreason) {
        this.signreason = signreason;
    }
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

}
