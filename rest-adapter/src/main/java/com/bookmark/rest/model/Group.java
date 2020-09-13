package com.bookmark.rest.model;


import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Group {

    private Long id;
    private String name;
    private String description;
    private String createdBy;
    private LocalDate createdOn;
}
