package com.kiszka.restaurantpage.web.services;

import com.kiszka.restaurantpage.web.entity.forms.FormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String smtpEmail;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    public void sendEmail(FormData data){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(smtpEmail);
        mailMessage.setTo(smtpEmail);
        mailMessage.setSubject(data.getTopic());
        mailMessage.setText("Imię: "+data.getName()+"\nNumer telefonu: "+data.getPhoneNumber()+
                "\nEmail Address: "+data.getEmail()+"\n"+data.getMessage());
        mailSender.send(mailMessage);
    }
}
