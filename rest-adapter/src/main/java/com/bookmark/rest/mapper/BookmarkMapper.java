package com.bookmark.rest.mapper;


import static java.util.Objects.nonNull;
import static org.springframework.util.CollectionUtils.isEmpty;

import com.bookmark.domain.model.Bookmark;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum BookmarkMapper {
	BOOKMARK_MAPPER;

	public List<com.bookmark.rest.model.Bookmark> constructBookmark(final List<Bookmark> bookmarks) {
		return !isEmpty(bookmarks) ? bookmarks.stream().map(this::constructBookmark).collect(Collectors.toList())
				: Collections.emptyList();
	}

	public com.bookmark.rest.model.Bookmark constructBookmark(final Bookmark bookmark) {
		return nonNull(bookmark) ? com.bookmark.rest.model.Bookmark.builder().actualUrl(bookmark.getActualUrl())
				.description(bookmark.getDescription()).favicon(bookmark.getFavicon()).expiredDate(bookmark.getExpiredDate())
				.groupId(bookmark.getGroupId()).id(bookmark.getId()).shortUrl(bookmark.getShortUrl()).source(bookmark.getSource())
				.title(bookmark.getTitle()).build() : null;
	}

	public List<Bookmark> constructBookmarkEntity(final List<com.bookmark.rest.model.Bookmark> bookmarkEntities) {
		return isEmpty(bookmarkEntities) ? bookmarkEntities.stream().map(this::constructBookmark).collect(Collectors.toList())
				: Collections.emptyList();
	}

	public Bookmark constructBookmark(final com.bookmark.rest.model.Bookmark bookmark) {
		return nonNull(bookmark) ? Bookmark.builder().actualUrl(bookmark.getActualUrl())
				.description(bookmark.getDescription()).favicon(bookmark.getFavicon()).expiredDate(bookmark.getExpiredDate())
				.groupId(bookmark.getGroupId()).id(bookmark.getId()).shortUrl(bookmark.getShortUrl()).source(bookmark.getSource())
				.title(bookmark.getTitle()).build() : null;
	}
}
