package com.bookmark.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
