package com.bookmark.jpa.repository;

import static com.bookmark.jpa.mapper.BookmarkMapper.BOOKMARK_MAPPER;

import com.bookmark.domain.model.Bookmark;
import com.bookmark.domain.port.ObtainBookmark;
import com.bookmark.jpa.dao.BookmarkDao;

import java.util.List;
import java.util.Objects;

public class BookmarkRepository implements ObtainBookmark {
	private BookmarkDao bookmarkDao;
	public BookmarkRepository(final BookmarkDao bookmarkDao) {
		this.bookmarkDao = bookmarkDao;
	}

	@Override
	public List<Bookmark> getBookmarks(final Long groupId) {
		if (Objects.nonNull(groupId)) {
			return BOOKMARK_MAPPER.constructBookmark(bookmarkDao.findAllByGroupId(groupId));
		} else {
			return BOOKMARK_MAPPER.constructBookmark(bookmarkDao.findAll());
		}
	}

	@Override
	public void save(Bookmark bookmark) {
		if (Objects.nonNull(bookmark)) {
			bookmarkDao.save(BOOKMARK_MAPPER.constructBookmarkEntity(bookmark));
		}
	}
}
