package com.bookmark.jpa.dao;

import com.bookmark.jpa.entity.BookmarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkDao extends JpaRepository<BookmarkEntity, Long> {

}
