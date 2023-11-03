package com.example.tyc.utils;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @title MainUtils
 * @Author ycf
 * @Date: 2023-04-04 11:45
 * @Version: 1.0
 */

@Component
public class MailUtils {
    @Autowired
    private JavaMailSenderImpl mailSender;


    public void sendMail(String title,String context) {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        //需要借助Helper类
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage);
        try {
            helper.setFrom("yanchengfei666@163.com");
            helper.setTo("619748460@qq.com");
            //helper.setBcc("密送人");
            helper.setSubject(title);
            helper.setSentDate(new Date());
            helper.setText(context, true);

            mailSender.send(mailMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendErrMail(String context) {
        MimeMessage mailMessage = mailSender.createMimeMessage();
        //需要借助Helper类
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage);
        String title = "异常提醒";
        try {
            helper.setFrom("yanchengfei666@163.com");
            helper.setTo("cfyan@ipi-tech.com");
            //helper.setBcc("密送人");
            helper.setSubject(title);
            helper.setSentDate(new Date());
            helper.setText(context, true);

            mailSender.send(mailMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sendSuccMail(String context) {
        MimeMessage mailMessage = mailSender.createMimeMessage();
        //需要借助Helper类
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage);
        String title = "成功提醒";
        try {
            helper.setFrom("yanchengfei666@163.com");
            helper.setTo("619748460@qq.com");
            //helper.setBcc("密送人");
            helper.setSubject(title);
            helper.setSentDate(new Date());
            helper.setText(context, true);

            mailSender.send(mailMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
