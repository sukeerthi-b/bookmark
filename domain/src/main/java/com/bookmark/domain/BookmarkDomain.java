package com.bookmark.domain;

import static com.bookmark.domain.helper.GroupHelper.GROUP_HELPER;
import static com.bookmark.domain.helper.BookmarkHelper.BOOKMARK_HELPER;

import com.bookmark.domain.model.Bookmark;
import com.bookmark.domain.model.Group;
import com.bookmark.domain.port.ObtainBookmark;
import com.bookmark.domain.port.ObtainGroup;
import com.bookmark.domain.port.RequestBookmark;
import com.bookmark.domain.port.RequestGroup;
import lombok.Builder;

import java.util.List;

@Builder
public class BookmarkDomain implements RequestBookmark, RequestGroup {

	private ObtainBookmark obtainBookmark;
	private ObtainGroup obtainGroup;

	public List<Bookmark> getBookmarks() {
		BOOKMARK_HELPER.init(obtainBookmark);
		return BOOKMARK_HELPER.getBookmarks();
	}

	@Override
	public void save(Bookmark bookmark) {
		BOOKMARK_HELPER.init(obtainBookmark);
		BOOKMARK_HELPER.save(bookmark);
	}

	@Override
	public String getOriginalURL(String shortUrl) {
		return null;
	}

	@Override
	public List<Group> getGroups() {
		GROUP_HELPER.init(obtainGroup);
		return GROUP_HELPER.getGroups();
	}

	@Override
	public void save(final Group group) {
		GROUP_HELPER.init(obtainGroup);
		GROUP_HELPER.save(group);
	}
}
