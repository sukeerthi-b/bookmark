package com.bookmark.rest.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class Group {

    private Long id;
    private String name;
    private String description;
    private String createdBy;
    private LocalDate createdOn;
}
