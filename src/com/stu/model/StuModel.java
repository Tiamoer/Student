package com.stu.model;

public class StuModel {
    private int no;
    private String stu_name;
    private int age;
    private String stu_account;
    private String stu_password;
    private int stu_status;

    public StuModel(int no, String stu_name, int age, String stu_account, String stu_password, int stu_status) {
        this.no = no;
        this.stu_name = stu_name;
        this.age = age;
        this.stu_account = stu_account;
        this.stu_password = stu_password;
        this.stu_status = stu_status;
    }

    public StuModel(String stu_name, int age, String stu_account, String stu_password, int stu_status) {
        this.stu_name = stu_name;
        this.age = age;
        this.stu_account = stu_account;
        this.stu_password = stu_password;
        this.stu_status = stu_status;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStu_account() {
        return stu_account;
    }

    public void setStu_account(String stu_account) {
        this.stu_account = stu_account;
    }

    public String getStu_password() {
        return stu_password;
    }

    public void setStu_password(String stu_password) {
        this.stu_password = stu_password;
    }

    public int getStu_status() {
        return stu_status;
    }

    public void setStu_status(int stu_status) {
        this.stu_status = stu_status;
    }
}
