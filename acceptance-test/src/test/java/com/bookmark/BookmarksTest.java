package com.bookmark;

import org.mockito.ArgumentMatcher;

import com.bookmark.domain.BookmarkDomain;
import com.bookmark.domain.model.Bookmark;
import com.bookmark.domain.port.ObtainBookmark;
import com.bookmark.domain.port.RequestBookmark;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class BookmarksTest {

    @Test
    @DisplayName("should get bookmarks with support of stub")
    public void shouldGetBookmarksWithSupportOfStub(@Mock ObtainBookmark obtainBookmark) {
        //Given
        RequestBookmark requestBookmark = BookmarkDomain.builder().obtainBookmark(obtainBookmark).build();
        Mockito.when(obtainBookmark.getBookmarks(null)).thenReturn(mockBookmarks());
        //When
        List<Bookmark> bookmarks = requestBookmark.getBookmarks(null);
        //Then
        Mockito.verify(obtainBookmark, Mockito.times(1)).getBookmarks(null);
        Assertions.assertThat(bookmarks).isNotNull();
        Assertions.assertThat(bookmarks.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("should get bookmarks by group id with support of stub")
    public void shouldGetBookmarksByGroupIdWithSupportOfStub(@Mock ObtainBookmark obtainBookmark) {
        //Given
        RequestBookmark requestBookmark = BookmarkDomain.builder().obtainBookmark(obtainBookmark).build();
        Mockito.when(obtainBookmark.getBookmarks(1001L)).thenReturn(mockBookmarks());
        //When
        List<Bookmark> bookmarks = requestBookmark.getBookmarks(1001L);
        //Then
        Mockito.verify(obtainBookmark, Mockito.times(1)).getBookmarks(1001L);
        Assertions.assertThat(bookmarks).isNotNull();
        Assertions.assertThat(bookmarks.size()).isEqualTo(1);
    }


    @Test
    @DisplayName("should save bookmark with support of stub")
    public void shouldSaveBookmarkWithSupportOfStub(@Mock ObtainBookmark obtainBookmark) {
        //Given
        RequestBookmark requestBookmark = BookmarkDomain.builder().obtainBookmark(obtainBookmark).build();
        //Mockito.when(obtainBookmark.save(any())).thenReturn(Bookmark.builder().build());
        //When
        //requestBookmark.save(mockBookmarks().get(0));
        //Then
        //Mockito.verify(obtainBookmark, Mockito.times(2)).save();
    }

    private List<Bookmark> mockBookmarks() {
        List<Bookmark> bookmarks = new ArrayList<>();
        bookmarks.add(Bookmark.builder().groupId(1001L).source("sg").title("test").description("test").actualUrl("www.google.com").build());
        return bookmarks;
    }
}
