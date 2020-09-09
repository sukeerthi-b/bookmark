package com.bookmark.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
