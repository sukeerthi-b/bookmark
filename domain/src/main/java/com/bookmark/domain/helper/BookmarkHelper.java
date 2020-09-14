package com.bookmark.domain.helper;

import static java.util.Objects.isNull;
import static com.bookmark.domain.common.BookmarkConstants.PORTS_NOT_AVAILABLE;
import static java.util.Objects.nonNull;

import com.bookmark.domain.exception.BookmarkException;
import com.bookmark.domain.model.Bookmark;
import com.bookmark.domain.port.ObtainBookmark;
import java.util.List;
import java.util.Objects;

public enum  BookmarkHelper {
    BOOKMARK_HELPER;

    private ObtainBookmark obtainBookmark;

    public void init(final ObtainBookmark obtainBookmark) {
        this.obtainBookmark = obtainBookmark;
    }

    public String getOriginalURL(final String shortUrl) {
        Bookmark bookmark = obtainBookmark.getBookmarksByShortUrl(shortUrl);
        if(nonNull(bookmark)) {
            return bookmark.getActualUrl();
        }
        throw new BookmarkException("Url is expired");
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
        Bookmark bookmarkAfterSave = obtainBookmark.save(bookmark);
        updateShortcutUrl(bookmarkAfterSave);
    }

    private boolean isAllPortsNotAvailable() {
        return isNull(this.obtainBookmark);
    }

    private String updateShortcutUrl(Bookmark bookmark) {
        String shortString = ConversionHelper.CONVERSION_HELPER.encode(bookmark.getId());
        StringBuilder shortUrlBuffer = new StringBuilder();
        shortUrlBuffer.append("http://bookmarks/");
        if (Objects.nonNull(bookmark.getGroup())) {
            shortUrlBuffer.append(bookmark.getGroup().getName());
            shortUrlBuffer.append("/");
        } else {
            shortUrlBuffer.append("tiny/");
        }
        shortUrlBuffer.append(shortString);
        return shortUrlBuffer.toString();
    }
}
