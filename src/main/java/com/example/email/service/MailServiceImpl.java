package com.example.email.service;

import com.example.email.dto.MailDto;
import com.example.email.util.MailHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.from-address}")
    private String fromAddress;

    @Override
    public void sendMail(MailDto mailDto) {
        try {
            MailHandler mailHandler = new MailHandler(mailSender);

            mailHandler.setTo(mailDto.getToAddress());
            mailHandler.setFrom(fromAddress);
            mailHandler.setSubject(mailDto.getSubject());

            String htmlContent = "<p>" + mailDto.getText() + "<p><img src='cid:logo-img'>";
            mailHandler.setText(htmlContent, true);

            mailHandler.setInline("logo-img", "static/logo.png");

            if (!CollectionUtils.isEmpty(mailDto.getAttachmentFile())) {
                for (MultipartFile file : mailDto.getAttachmentFile()) {
                    mailHandler.setAttach(file);
                }
            }

            mailHandler.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
