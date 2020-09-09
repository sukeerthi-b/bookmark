package com.bookmark.jpa.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@Entity
@Table(name = "t_groups")
public class GroupEntity {
    @Id
    @Column(name = "group_tecid")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_on")
    private LocalDate createdOn;
}
