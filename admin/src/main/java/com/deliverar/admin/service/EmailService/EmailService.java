package com.deliverar.admin.service.EmailService;

public interface EmailService {

    void sendEmail(String destination, String subject, String content);
}
