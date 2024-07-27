package com.edigest.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void testSendEmail() {
        emailService.sendMail(
                 "sharmamedhansh21@gmail.com"
                ,"Update on my SpringBoot Journey"
                ,"Hi i am Medhansh Sharma , i am 3rd Year student ");

    }
}
