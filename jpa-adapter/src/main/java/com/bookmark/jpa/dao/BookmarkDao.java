package com.bookmark.jpa.dao;

import com.bookmark.jpa.entity.BookmarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkDao extends JpaRepository<BookmarkEntity, Long> {
    List<BookmarkEntity> findAllByGroupId(final Long groupId);
}
