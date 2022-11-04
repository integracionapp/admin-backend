package com.deliverar.admin.service.EmailService;

import com.deliverar.admin.exceptions.EmailNotSendException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    JavaMailSender sender;

    @Override
    public void sendEmail(String destination, String subject, String content) {
        this.sendEmailTool(destination,subject,content);
    }

    private void sendEmailTool(String email, String subject,String textMessage) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(email);
            helper.setText(textMessage, true);
            helper.setSubject(subject);
            sender.send(message);
        } catch (MessagingException e) {
            throw new EmailNotSendException("No se pudo enviar el correo");
        }
    }
}
