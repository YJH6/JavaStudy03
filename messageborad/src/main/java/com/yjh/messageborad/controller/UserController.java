package com.yjh.messageborad.controller;

import com.yjh.messageborad.*;


import com.yjh.messageborad.model.User;
import com.yjh.messageborad.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes("user")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    //注册
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> register(@RequestParam("username")final String username,
                                       @RequestParam("password")final String password,
                                       @RequestParam("email")final String email) {
        logger.info("提交的参数{}{}{}",username,password,email);
        Map<String,Object> ans = new HashMap<>();
        if (!emailFormat(email)){
            ans.put("code",404);
            ans.put("info","失败");
            ans.put("bady","邮箱错误");
        }else if (userService.register(username,password,email)){
            ans.put("code",200);
            ans.put("info","成功");
        }else {
            ans.put("code",404);
            ans.put("info","失败");
            ans.put("bady","用户名重复");
        }
        return ans;
    }

    public static boolean emailFormat(String email){
        boolean tag = true;
        if (!email.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")){
            tag = false;
        }	return tag;
    }

    //登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(@RequestParam("username")final String userName,
                                    @RequestParam("password")final String password,
                                    Model model,
                                    HttpServletRequest httpServletRequest){
        Map<String,Object> ans = new HashMap<>();
        ans.put("code",404);
        ans.put("info","失败");
        if (userService.checkLogin(userName,password)){
            ans.put("code",200);
            ans.put("info","成功");
            User user = new User();
            user.setUserName(userName);
            model.addAttribute("user",user);
            httpServletRequest.getSession();
        }
        return ans;
    }

    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateUser(@RequestParam("username")final String username,
                                         @RequestParam("password") final String password,
                                         @RequestParam("newpassword") final String newPassword){
        Map<String,Object> ans = new HashMap<>();
        logger.info("提交的参数{}{}",username,password);
        if (userService.updateUser(username,password,newPassword)){
            ans.put("info","成功");
            ans.put("code",200);
        }else {
            ans.put("info","失败");
            ans.put("code",404);
        }
        return ans;
    }

    @RequestMapping(value = "/checkEMail",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> checkEMail(@RequestParam("verificationCode") final double verificationCode){
        Map<String,Object> ans = new HashMap<>();
        logger.info("提交的参数{}{}",verificationCode);
        if (userService.checkMail(verificationCode)){
            ans.put("info","成功");
            ans.put("code",200);
        }else {
            ans.put("info","失败");
            ans.put("code",404);
            ans.put("bady","验证码错误");
        }
        return ans;
    }

}