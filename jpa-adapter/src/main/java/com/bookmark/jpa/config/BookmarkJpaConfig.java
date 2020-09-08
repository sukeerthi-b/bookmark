package com.bookmark.jpa.config;

import com.bookmark.jpa.BookmarkJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bookmark.domain.port.ObtainBookmark;

@Configuration
public class BookmarkJpaConfig {

	@Bean
	public ObtainBookmark getBookmarkJpaAdapter() {
		return new BookmarkJpaAdapter();
	}
}
