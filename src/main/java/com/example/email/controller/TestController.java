package com.example.email.controller;

import com.example.email.dto.MailDto;
import com.example.email.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final MailService mailService;

    @PostMapping("/send-mail")
    public void sendMail(MailDto mailDto) {
        mailService.sendMail(mailDto);
    }
}
