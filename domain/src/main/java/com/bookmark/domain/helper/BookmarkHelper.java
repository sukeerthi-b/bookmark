package com.bookmark.domain.helper;

import static java.util.Objects.isNull;
import static com.bookmark.domain.common.BookmarkConstants.PORTS_NOT_AVAILABLE;

import com.bookmark.domain.exception.BookmarkException;
import com.bookmark.domain.model.Bookmark;
import com.bookmark.domain.port.ObtainBookmark;
import java.util.List;

public enum  BookmarkHelper {
    BOOKMARK_HELPER;

    private ObtainBookmark obtainBookmark;

    public void init(final ObtainBookmark obtainBookmark) {
        this.obtainBookmark = obtainBookmark;
    }

    public List<Bookmark> getBookmarks() {
        if(isAllPortsNotAvailable()) {
            throw new BookmarkException(PORTS_NOT_AVAILABLE);
        }
        return obtainBookmark.getBookmarks(null);
    }

    public void save(final Bookmark bookmark) {
        if(isAllPortsNotAvailable() || isNull(bookmark)) {
            throw new BookmarkException(PORTS_NOT_AVAILABLE);
        }
        obtainBookmark.save(bookmark);
    }

    private boolean isAllPortsNotAvailable() {
        return isNull(this.obtainBookmark);
    }
}
