package com.example.email.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MailHandler {

    private JavaMailSender mailSender;
    private MimeMessage mimeMessage;
    private MimeMessageHelper mimeMessageHelper;

    public MailHandler(JavaMailSender mailSender) throws MessagingException {
        this.mailSender = mailSender;
        this.mimeMessage = mailSender.createMimeMessage();
        this.mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
    }

    //보내는 주소
    public void setFrom(String fromAddress) throws MessagingException {
        mimeMessageHelper.setFrom(fromAddress);
    }

    //받는 주소
    public void setTo(String toAddress) throws MessagingException {
        mimeMessageHelper.setTo(toAddress);
    }

    //받는 주소
    public void setTo(String[] toAddresses) throws MessagingException {
        mimeMessageHelper.setTo(toAddresses);
    }

    //setCc() 참조자 설정, setBcc() 숨은 참조자 설정

    //제목
    public void setSubject(String subject) throws MessagingException {
        mimeMessageHelper.setSubject(subject);
    }

    //내용
    public void setText(String text, boolean useHtml) throws MessagingException {
        mimeMessageHelper.setText(text, useHtml);
    }

    //첨부파일
    public void setAttach(MultipartFile attachmentFile) throws IOException, MessagingException {
        mimeMessageHelper.addAttachment(Objects.requireNonNull(attachmentFile.getOriginalFilename()), attachmentFile);
    }

    //이미지 삽입
    public void setInline(String contentId, String pathToInline) throws IOException, MessagingException {
        File file = new ClassPathResource(pathToInline).getFile();
        FileSystemResource fileSystemResource = new FileSystemResource(file);

        mimeMessageHelper.addInline(contentId, fileSystemResource);
    }

    //발송
    public void send() {
        mailSender.send(mimeMessage);
    }
}
