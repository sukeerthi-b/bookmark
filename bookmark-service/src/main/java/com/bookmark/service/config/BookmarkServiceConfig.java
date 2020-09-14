package com.bookmark.service.config;

import com.bookmark.domain.port.ObtainGroup;
import com.bookmark.domain.port.RequestGroup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.bookmark.domain.BookmarkDomain;
import com.bookmark.domain.port.ObtainBookmark;
import com.bookmark.domain.port.RequestBookmark;
import com.bookmark.jpa.config.BookmarkJpaConfig;
import org.springframework.context.annotation.Primary;

import java.awt.print.Book;

@Configuration
@Import(BookmarkJpaConfig.class)
public class BookmarkServiceConfig {

	@Primary
	@Bean
	public RequestBookmark getRequestBookmark(final ObtainBookmark obtainBookmark) {
		return BookmarkDomain.builder().obtainBookmark(obtainBookmark).build();
	}

	@Bean
	public RequestGroup getRequestGroup(final ObtainGroup obtainGroup) {
		return BookmarkDomain.builder().obtainGroup(obtainGroup).build();
	}
}
