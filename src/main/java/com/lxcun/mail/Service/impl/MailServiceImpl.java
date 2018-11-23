package com.lxcun.mail.Service.impl;

import com.lxcun.mail.Service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MailServiceImpl implements MailService{
    @Autowired
    private JavaMailSender mailSender;

    /**发送邮件
     * 1.传入参数为接收者的recipient1，邮件标题title，邮件内容text
     * @param recipient1
     * @param title
     * @param text
     * @return
     */
    @Override
    public boolean sendEmail(String recipient1,String title,String text) {
        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            if(isEmail(recipient1)){
                helper.setFrom("atwmanage@qq.com");
                helper.setTo(recipient1);
                helper.setSubject(title);
                StringBuffer sb = new StringBuffer();

                sb.append("<h1>"+title+"</h1>")
                        .append("<p >"+text+"</p>")
                        .append("<p>请点击链接</p>")
                        .append("<a></a>")
                        .append("<p>进入系统</p>");
                helper.setText(sb.toString(),true);
                mailSender.send(message);
                return true;
            }else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public static boolean isEmail(String string) {
        if (string == null)
            return false;
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(string);
        if (m.matches())
            return true;
        else
            return false;
    }

}