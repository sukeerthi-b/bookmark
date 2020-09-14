package com.bookmark.domain.port;

import com.bookmark.domain.model.Bookmark;

import java.util.List;

public interface ObtainBookmark {
    List<Bookmark> getBookmarks(final Long groupId);
    Bookmark save(Bookmark bookmark);
    Bookmark getBookmarksByShortUrl(final String shortUrl);
}
