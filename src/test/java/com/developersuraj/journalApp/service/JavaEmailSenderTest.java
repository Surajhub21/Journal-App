package com.developersuraj.journalApp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled
public class JavaEmailSenderTest {

    @Autowired
    private EmailService emailService;

    @Test
    void testSendMail(){
        emailService.sendEmail(
                "otutorbot@gmail.com",
                "Wishing",
                "Good Morning OTutor Bete."
        );
    }
}
