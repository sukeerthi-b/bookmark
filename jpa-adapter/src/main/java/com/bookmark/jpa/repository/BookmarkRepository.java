package com.bookmark.jpa.repository;

import static com.bookmark.jpa.mapper.BookmarkMapper.BOOKMARK_MAPPER;
import static java.util.Objects.nonNull;

import com.bookmark.domain.model.Bookmark;
import com.bookmark.domain.port.ObtainBookmark;
import com.bookmark.jpa.dao.BookmarkDao;
import com.bookmark.jpa.entity.BookmarkEntity;

import java.time.LocalDate;
import java.util.List;

public class BookmarkRepository implements ObtainBookmark {
	private BookmarkDao bookmarkDao;
	public BookmarkRepository(final BookmarkDao bookmarkDao) {
		this.bookmarkDao = bookmarkDao;
	}

	@Override
	public List<Bookmark> getBookmarks(final Long groupId) {
		if (nonNull(groupId)) {
			return BOOKMARK_MAPPER.constructBookmark(bookmarkDao.findAllByGroupEntityId(groupId));
		} else {
			return BOOKMARK_MAPPER.constructBookmark(bookmarkDao.findAllByExpiredDateGreaterThanOrExpiredDateNull(LocalDate.now()));
		}
	}

	@Override
	public Bookmark getBookmarksByShortUrl(final String shortUrl) {
		if (nonNull(shortUrl)) {
			return BOOKMARK_MAPPER.constructBookmark(bookmarkDao.findByShortUrl(shortUrl));
		} else {
			return null;
		}
	}

	@Override
	public Bookmark save(Bookmark bookmark) {
		if (nonNull(bookmark)) {
			BookmarkEntity bookmarkEntity = bookmarkDao.save(BOOKMARK_MAPPER.constructBookmarkEntity(bookmark));
			return BOOKMARK_MAPPER.constructBookmark(bookmarkEntity);
		}
		return null;
	}
}
