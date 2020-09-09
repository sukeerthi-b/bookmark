package com.bookmark.domain.port;

import com.bookmark.domain.model.Bookmark;

import java.util.List;

public interface ObtainBookmark {
    List<Bookmark> getBookmarks();
}
