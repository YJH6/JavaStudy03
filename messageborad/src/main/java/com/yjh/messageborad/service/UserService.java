package com.yjh.messageborad.service;

public interface UserService {
    Boolean updateUser(String userName, String password,String newPassword);
    Boolean checkLogin(String userName, String password);
    Boolean register(String userName,String password,String eMail);
    Boolean checkMail(double userVerificationCode);
}
