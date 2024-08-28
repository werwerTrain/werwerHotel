package com.buaa.werwerhotel.service;

import org.springframework.stereotype.Component;

@Component
public interface IEmailService {
    public void sendSimpleMail(String to, String subject, String text);
}
