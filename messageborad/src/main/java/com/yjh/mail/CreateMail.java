package com.yjh.mail;

import com.yjh.messageborad.service.*;
import com.yjh.messageborad.service.Impl.UserServiceImpl;
import com.yjh.messageborad.model.User;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class CreateMail {
    public static MimeMessage createSimpleMail(Session session,User user) throws Exception{
        //创建邮件对象
        MimeMessage message = new MimeMessage((session));
        //指明邮件的发件人
        message.setFrom(new InternetAddress("ash_yjh@163.com"));
        //指明邮件的收件人
        message.setRecipient(Message.RecipientType.TO,new InternetAddress("191148912@qq.com"));
        //邮件的标题
        message.setSubject("验证码");
        //邮件的文本内容
        message.setContent(user.getVerificationCode(),"text/html;charset=UTF-8");
        //返回创建好的邮件对象
        return message;
    }
}
