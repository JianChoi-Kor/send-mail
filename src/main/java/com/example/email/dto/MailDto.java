package com.example.email.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MailDto {

    private String toAddress;
    private String subject;
    private String text;
    private List<MultipartFile> attachmentFile;
}
