package com.bookmark.jpa.mapper;

import static java.util.Objects.nonNull;
import static org.springframework.util.CollectionUtils.isEmpty;

import com.bookmark.domain.model.Bookmark;
import com.bookmark.jpa.entity.BookmarkEntity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum BookmarkMapper {
	BOOKMARK_MAPPER;

	public List<Bookmark> constructBookmark(final List<BookmarkEntity> bookmarkEntities) {
		return !isEmpty(bookmarkEntities) ? bookmarkEntities.stream().map(this::constructBookmark).collect(Collectors.toList())
				: Collections.emptyList();
	}

	public Bookmark constructBookmark(final BookmarkEntity bookmarkEntity) {
		return nonNull(bookmarkEntity) ? Bookmark.builder().actualUrl(bookmarkEntity.getActualUrl())
				.description(bookmarkEntity.getDescription()).favicon(bookmarkEntity.getFavicon()).expiredDate(bookmarkEntity.getExpiredDate())
				.id(bookmarkEntity.getId()).shortUrl(bookmarkEntity.getShortUrl()).source(bookmarkEntity.getSource())
				.title(bookmarkEntity.getTitle()).group(GroupMapper.GROUP_MAPPER.constructGroup(bookmarkEntity.getGroupEntity())).build() : null;
	}

	public List<BookmarkEntity> constructBookmarkEntity(final List<Bookmark> bookmarkEntities) {
		return isEmpty(bookmarkEntities) ? bookmarkEntities.stream().map(this::constructBookmarkEntity).collect(Collectors.toList())
				: Collections.emptyList();
	}

	public BookmarkEntity constructBookmarkEntity(final Bookmark bookmark) {
		return nonNull(bookmark) ? BookmarkEntity.builder().actualUrl(bookmark.getActualUrl())
				.description(bookmark.getDescription()).favicon(bookmark.getFavicon()).expiredDate(bookmark.getExpiredDate())
				.groupEntity(GroupMapper.GROUP_MAPPER.constructGroup(bookmark.getGroup())).id(bookmark.getId()).shortUrl(bookmark.getShortUrl()).source(bookmark.getSource())
				.title(bookmark.getTitle()).build() : null;
	}
}
