package com.bookmark.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_groups")
@SequenceGenerator(name="bookmark_seq", sequenceName = "bookmark_seq", allocationSize = 1)
public class GroupEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="bookmark_seq")
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
