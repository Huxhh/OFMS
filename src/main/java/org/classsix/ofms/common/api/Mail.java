package org.classsix.ofms.common.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by jiang on 2017/5/14.
 * 面向运气，面向心情，面向Bug。
 */
public class Mail {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public void sendVerfMail(String receiveMail){
        int verNum = (int) (Math.random()*8999) + 1000;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("2673245113@qq.com");//发送者.
        message.setTo(receiveMail);//接收者.
        message.setSubject("(重要！)Ofms验证邮件");//邮件主题.
        message.setText("您的验证码是："+verNum+"/n"+"请注意：验证码在5分钟内有效，请注意时限！");//邮件内容.
        stringRedisTemplate.opsForValue().set(String.valueOf(verNum),"get",5, TimeUnit.MINUTES);
        mailSender.send(message);//发送邮件
    }
}
