package com.bookmark.domain;

import static com.bookmark.domain.helper.BookmarkHelper.BOOKMARK_HELPER;

import com.bookmark.domain.model.Bookmark;
import com.bookmark.domain.port.ObtainBookmark;
import com.bookmark.domain.port.ObtainGroup;
import com.bookmark.domain.port.RequestBookmark;
import com.bookmark.domain.port.RequestGroup;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Builder
public class BookmarkDomain implements RequestBookmark, RequestGroup {

	private ObtainBookmark obtainBookmark;
	private ObtainGroup obtainGroup;

	public List<Bookmark> getBookmarks() {
		BOOKMARK_HELPER.init(obtainBookmark);
		return BOOKMARK_HELPER.getBookmarks();
	}
}
