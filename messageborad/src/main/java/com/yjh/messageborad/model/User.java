package com.yjh.messageborad.model;

public class User {
    private String userName;  // 用户名
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    private double verificationCode;//验证码
    public double getVerificationCode(){return verificationCode;}
    public void setVerificationCode(double verificationCode){this.verificationCode=verificationCode;}
    private String eMail;  // 邮箱
    public String geteMail() {
        return eMail;
    }
    public void seteMail(String userName) {
        this.eMail = eMail;
    }
}
