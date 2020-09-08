package com.bookmark.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.bookmark.domain.BookmarkDomain;
import com.bookmark.domain.port.ObtainBookmark;
import com.bookmark.domain.port.RequestBookmark;
import com.bookmark.jpa.config.BookmarkJpaConfig;

@Configuration
@Import(BookmarkJpaConfig.class)
public class BookmarkServiceConfig {

	@Bean
	public RequestBookmark getRequestBookmark(final ObtainBookmark obtainBookmark) {
		return new BookmarkDomain(obtainBookmark);
	}
}
