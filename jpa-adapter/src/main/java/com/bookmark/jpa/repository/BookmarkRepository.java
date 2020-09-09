package com.bookmark.jpa.repository;

import com.bookmark.domain.port.ObtainBookmark;
import com.bookmark.jpa.dao.BookmarkDao;

public class BookmarkRepository implements ObtainBookmark {
	private BookmarkDao bookmarkDao;
	public BookmarkRepository(final BookmarkDao bookmarkDao) {
		this.bookmarkDao = bookmarkDao;
	}
}
