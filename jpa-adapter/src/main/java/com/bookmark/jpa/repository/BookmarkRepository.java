package com.bookmark.jpa.repository;

import static com.bookmark.jpa.mapper.BookmarkMapper.BOOKMARK_MAPPER;

import com.bookmark.domain.model.Bookmark;
import com.bookmark.domain.port.ObtainBookmark;
import com.bookmark.jpa.dao.BookmarkDao;

import java.util.List;

public class BookmarkRepository implements ObtainBookmark {
	private BookmarkDao bookmarkDao;
	public BookmarkRepository(final BookmarkDao bookmarkDao) {
		this.bookmarkDao = bookmarkDao;
	}

	public List<Bookmark> getBookmarks() {
		return BOOKMARK_MAPPER.constructBookmark(bookmarkDao.findAll());
	}
}
