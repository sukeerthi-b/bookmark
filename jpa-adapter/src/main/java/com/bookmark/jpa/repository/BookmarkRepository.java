package com.bookmark.jpa.repository;

import static com.bookmark.jpa.mapper.BookmarkMapper.BOOKMARK_MAPPER;
import static java.util.Objects.nonNull;

import com.bookmark.domain.model.Bookmark;
import com.bookmark.domain.port.ObtainBookmark;
import com.bookmark.jpa.dao.BookmarkDao;
import com.bookmark.jpa.dao.GroupDao;
import com.bookmark.jpa.entity.BookmarkEntity;
import com.bookmark.jpa.entity.GroupEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class BookmarkRepository implements ObtainBookmark {
	private BookmarkDao bookmarkDao;
	private GroupDao groupDao;

	public BookmarkRepository(final BookmarkDao bookmarkDao, final GroupDao groupDao) {
		this.bookmarkDao = bookmarkDao;
		this.groupDao = groupDao;
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
			GroupEntity groupEntity = Objects.nonNull(bookmark.getGroupId()) ? groupDao.findById(bookmark.getGroupId()).orElse(null) : null;
			BookmarkEntity bookmarkEntity = BOOKMARK_MAPPER.constructBookmarkEntity(bookmark);
			bookmarkEntity.setGroupEntity(groupEntity);
			bookmarkEntity = bookmarkDao.save(BOOKMARK_MAPPER.constructBookmarkEntity(bookmark));
			return BOOKMARK_MAPPER.constructBookmark(bookmarkEntity);
		}
		return null;
	}
}
