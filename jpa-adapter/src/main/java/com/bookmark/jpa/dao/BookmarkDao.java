package com.bookmark.jpa.dao;

import com.bookmark.jpa.entity.BookmarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookmarkDao extends JpaRepository<BookmarkEntity, Long> {
    List<BookmarkEntity> findAllByGroupEntityId(final Long groupId);
    List<BookmarkEntity> findAllByExpiredDateGreaterThanOrExpiredDateNull(LocalDate date);
    BookmarkEntity findByShortUrl(final String shortUrl);
}
