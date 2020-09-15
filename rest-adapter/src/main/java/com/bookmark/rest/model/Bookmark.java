package com.bookmark.rest.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Builder
@Getter
public class Bookmark {
    private Long id;
    private String actualUrl;
    private byte[] favicon;
    private String description;
    private String title;
    private String source;
    private String shortUrl;
    private Long groupId;
    private LocalDate expiredDate;
    private String createdBy;
    private LocalDate createdOn;
    private MultipartFile image;
}
