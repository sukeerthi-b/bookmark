package com.bookmark.rest.model;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
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
}
