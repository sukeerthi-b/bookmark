package com.bookmark.jpa.config;

import com.bookmark.domain.port.ObtainGroup;
import com.bookmark.jpa.repository.BookmarkRepository;
import com.bookmark.jpa.repository.GroupRepository;
import com.bookmark.jpa.dao.BookmarkDao;
import com.bookmark.jpa.dao.GroupDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bookmark.domain.port.ObtainBookmark;

@Configuration
public class BookmarkJpaConfig {

	@Bean
	public ObtainBookmark getBookmarkJpaAdapter(final BookmarkDao bookmarkDao) {
		return new BookmarkRepository(bookmarkDao);
	}

	@Bean
	public ObtainGroup getGroupJpaAdapter(final GroupDao groupDao) {
		return new GroupRepository(groupDao);
	}
}
