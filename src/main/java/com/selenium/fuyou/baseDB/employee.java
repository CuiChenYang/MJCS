package com.selenium.fuyou.baseDB;

public class employee {
    private String realName;
    private int departmentID;
    private String userID;
    private String phoneNum;
    private String date;

    public String getRealName(){
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getDepartmentID(){
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getUserID(){
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPhoneNum(){
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public employee(String realName,int departmentID,String userID,String phoneNum,String date){
        this.realName = realName;
        this.departmentID = departmentID;
        this.userID = userID;
        this.phoneNum = phoneNum;
        this.date = date;
    }
}
