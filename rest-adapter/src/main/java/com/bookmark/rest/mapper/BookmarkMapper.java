package com.bookmark.rest.mapper;


import static java.util.Objects.nonNull;
import static org.springframework.util.CollectionUtils.isEmpty;

import com.bookmark.domain.exception.BookmarkException;
import com.bookmark.domain.model.Bookmark;
import org.springframework.web.multipart.MultipartFile;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum BookmarkMapper {
	BOOKMARK_MAPPER;

	private static final String FILE_NAME_REGEX = "[a-zA-Z0-9&&[^\\x00+]]{1,200}\\.[a-zA-Z]{1,10}";
	private static final String VALID_EXTENSIONS[] = {".jpg"};
	private static final String INVALID_FILE = "Invalid file";
	private static final String UN_SUPPORTED_fILE = "Un supported file";
	public List<com.bookmark.rest.model.Bookmark> constructBookmark(final List<Bookmark> bookmarks) {
		return !isEmpty(bookmarks) ? bookmarks.stream().map(this::constructBookmark).collect(Collectors.toList())
				: Collections.emptyList();
	}

	public com.bookmark.rest.model.Bookmark constructBookmark(final Bookmark bookmark) {
		return nonNull(bookmark) ? com.bookmark.rest.model.Bookmark.builder().actualUrl(bookmark.getActualUrl()).createdBy(bookmark.getCreatedBy())
				.description(bookmark.getDescription()).favicon(bookmark.getFavicon()).expiredDate(bookmark.getExpiredDate())
				.groupId(bookmark.getGroupId()).id(bookmark.getId()).shortUrl(bookmark.getShortUrl()).source(bookmark.getSource())
				.title(bookmark.getTitle()).build() : null;
	}

	public List<Bookmark> constructBookmarkEntity(final List<com.bookmark.rest.model.Bookmark> bookmarkEntities) {
		return isEmpty(bookmarkEntities) ? bookmarkEntities.stream().map(this::constructBookmark).collect(Collectors.toList())
				: Collections.emptyList();
	}

	public Bookmark constructBookmark(final com.bookmark.rest.model.Bookmark bookmark) {
		return nonNull(bookmark) ? Bookmark.builder().actualUrl(bookmark.getActualUrl()).favicon(checkFileConstrains(bookmark.getImage()))
				.description(bookmark.getDescription()).favicon(bookmark.getFavicon()).expiredDate(bookmark.getExpiredDate())
				.groupId(bookmark.getGroupId()).id(bookmark.getId()).shortUrl(bookmark.getShortUrl()).source(bookmark.getSource())
				.title(bookmark.getTitle()).createdBy(bookmark.getCreatedBy()).build() : null;
	}

	private byte[] checkFileConstrains(MultipartFile file) {
		if (nonNull(file)) {
			String fileName = file.getOriginalFilename();
			if(!fileName.matches(FILE_NAME_REGEX) ||
					Arrays.stream(VALID_EXTENSIONS).noneMatch(extension -> fileName.toLowerCase().endsWith(extension))) {
				throw new BookmarkException(UN_SUPPORTED_fILE);
			}
		} else {
			return null;
		}
		try {
			return file.getBytes();
		} catch (Exception e) {
			throw new BookmarkException(INVALID_FILE);
		}
	}
}
