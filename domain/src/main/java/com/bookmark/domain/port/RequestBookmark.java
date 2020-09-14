package com.bookmark.domain.port;

import com.bookmark.domain.model.Bookmark;

import java.util.List;

public interface RequestBookmark {
    List<Bookmark> getBookmarks();

    void save(Bookmark bookmark);

    String getOriginalURL(String shortUrl);
}
