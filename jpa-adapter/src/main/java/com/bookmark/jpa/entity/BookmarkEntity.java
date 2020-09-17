package com.bookmark.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_bookmarks")
public class BookmarkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmrak_tecid")
    private Long id;
    @Column(name = "actual_url")
    private String actualUrl;
    @Lob
    @Column(name = "favicon")
    private byte[] favicon;
    @Column(name = "description")
    private String description;
    @Column(name = "title")
    private String title;
    @Column(name = "source")
    private String source;
    @Column(name = "short_url")
    private String shortUrl;
    @Column(name = "expired_date")
    private LocalDate expiredDate;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_on")
    private LocalDate createdOn;
    @ManyToOne
    @JoinColumn(name = "group_tecid")
    private GroupEntity groupEntity;
}
