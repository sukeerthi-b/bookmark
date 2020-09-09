package com.bookmark.jpa.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
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
    @Column(name = "group_tecid")
    private BigDecimal groupId;
    @Column(name = "expired_date")
    private LocalDate expiredDate;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_on")
    private LocalDate createdOn;

}
