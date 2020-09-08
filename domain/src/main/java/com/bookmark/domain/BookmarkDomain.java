package com.bookmark.domain;

import com.bookmark.domain.port.ObtainBookmark;
import com.bookmark.domain.port.RequestBookmark;

public class BookmarkDomain implements RequestBookmark {

	private ObtainBookmark obtainBookmark;

	public BookmarkDomain(final ObtainBookmark obtainBookmark) {
		this.obtainBookmark = obtainBookmark;
	}

}
