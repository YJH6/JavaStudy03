package demo;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;

public class sendMail {
    public static void main(String[] args)throws Exception{

        Properties prop = new Properties();
        prop.setProperty("mail.smtp.host", "smtp.163.com");
        prop.setProperty("mail.transport.protocol","smtp");
        prop.setProperty("mail.smtp.auth","true");
        //使用JavaMail发送邮件的5个步骤
        //1.创建session
        Session session = Session.getDefaultInstance(prop);
        //打开Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        //2.通过session得到transport对象
        Transport transport = session.getTransport();
        /**3.使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发送人需要提交邮箱的用户名和密码给smtp服务器，
        用户名和密码都通过验证之后才能够正常发送邮件给收件人*/
        transport.connect("ash_yjh@163.com","yu17623064506");
        //4.创建邮件
        Message message = createMail.createSimpleMail(session);
        //5.发送邮件
        transport.sendMessage(message,message.getAllRecipients());
        transport.close();

    }
}
