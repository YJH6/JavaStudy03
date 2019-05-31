package com.yjh.messageborad.service.Impl;


import com.yjh.mail.SendMail;
import com.yjh.messageborad.mapper.SqlMapper;
import com.yjh.messageborad.model.User;
import com.yjh.messageborad.service.UserService;
import com.yjh.messageborad.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SqlMapper sqlMapper;

    public User user = new User();
    @Override
    public Boolean register(String userName, String password,String eMail) {
        String newPassword = MD5Util.getMD5(password);
        int verificationCode = (int)(Math.random()*9000+1000);
        String vftc = String.valueOf(verificationCode);
        user.setUserName(userName);
        user.setVerificationCode(vftc);
        user.setEMail(eMail);
        try {
            SendMail.sendMail(user);
            String sql1 = "select count(*) from yjh.user where username= #{username}";
            String sql2 = "INSERT INTO yjh.user SET username =#{username},password =#{password}";
            Map<String, Object> checkRepetition = new HashMap<>();
            checkRepetition.put("sql", sql1);
            checkRepetition.put("username", userName);
            Map<String, Object> register = new HashMap<>();
            register.put("sql", sql2);
            register.put("username", userName);
            register.put("password", newPassword);
            List<Map<String, Object>> res = sqlMapper.sql(checkRepetition);
            if (Objects.nonNull(res) && res.size() > 0) {
                Map<String, Object> tmp = res.get(0);
                long cnt = (long) tmp.get("count(*)");
                if (cnt == 1) {
                    return false;
                } else {
                    List<Map<String, Object>> res2 = sqlMapper.sql(register);
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean checkLogin(String userName, String password) {
        String newPassword = MD5Util.getMD5(password);
        String sql = "select count(*) from yjh.user where username=#{username} and password=#{password}";
        Map<String, Object> params = new HashMap<>();
        params.put("sql", sql);
        params.put("username", userName);
        params.put("password", newPassword);
        List<Map<String, Object>> res = sqlMapper.sql(params);
        if (Objects.nonNull(res) && res.size() > 0) {
            Map<String, Object> tmp = res.get(0);
            long cnt = (long) tmp.get("count(*)");
            if (cnt == 1) {
                return true;
            }
        }
        return false;
    }

    //修改
    @Override
    public Boolean updateUser(String userName, String password, String newPassword) {
        String checkPassword = MD5Util.getMD5(password);
        String sql1 = "UPDATE yjh.user SET password =#{password} WHERE username =#{username}";
        Map<String, Object> updatemap = new HashMap<>();
        updatemap.put("sql", sql1);
        updatemap.put("username", userName);
        updatemap.put("password", newPassword);
        String sql2 = "select count(*) from yjh.user where username=#{username} and password=#{password}";
        Map<String, Object> params = new HashMap<>();
        params.put("sql", sql2);
        params.put("username", userName);
        params.put("password", checkPassword);
        List<Map<String, Object>> checkres = sqlMapper.sql(params);
        if (Objects.nonNull(checkres) && checkres.size() > 0) {
            Map<String, Object> tmp = checkres.get(0);
            long cnt = (long) tmp.get("count(*)");
            if (cnt == 1) {
                List<Map<String, Object>> res = sqlMapper.sql(updatemap);
                return true;
            }
        }
        return false;
    }

    //验证邮箱
    @Override
    public  Boolean checkMail(double userVerificationCode){

        String vftc = String.valueOf(userVerificationCode);
        if(user.getVerificationCode()==vftc){
            return true;
        }else {
            return false;
        }
    }
}
