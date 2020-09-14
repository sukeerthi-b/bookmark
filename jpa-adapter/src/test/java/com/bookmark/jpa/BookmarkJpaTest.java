package com.bookmark.jpa;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

import com.bookmark.domain.model.Bookmark;
import com.bookmark.jpa.dao.BookmarkDao;
import com.bookmark.jpa.dao.GroupDao;
import com.bookmark.jpa.entity.BookmarkEntity;
import com.bookmark.jpa.entity.GroupEntity;
import com.bookmark.jpa.repository.BookmarkRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookmarkJpaTest {

    @Autowired
    private BookmarkDao bookmarkDao;
    @Autowired
    private GroupDao groupDao;
    @Autowired
    private BookmarkRepository bookmarkRepository;

    @BeforeEach
    public void setup() {
        dbCleanup();
    }

    @AfterEach
    public void cleanUp() {
        dbCleanup();
    }

    private void dbCleanup() {
        bookmarkDao.deleteAll();
    }

    @Test
    @DisplayName("should get bookmarks by groupId using database")
    public void shouldGetBookmarksByGroupIdUsingDataBase() {
        //Given

        BookmarkEntity bookmarkEntity = mockBookmarkEntityWithGroup();
        bookmarkDao.save(bookmarkEntity);
        //When
        List<Bookmark> bookmarks = bookmarkRepository.getBookmarks(bookmarkEntity.getGroupEntity().getId());
        //Then
        assertThat(bookmarks).isNotEmpty();
        assertThat(bookmarks).extracting("title", "description")
                .contains(tuple(bookmarkEntity.getTitle(), bookmarkEntity.getDescription()));
    }


    @Test
    @DisplayName("should get bookmarks by short url using database")
    public void shouldGetBookmarksByShortUrlUsingDataBase() {
        //Given

        BookmarkEntity bookmarkEntity = mockBookmarkEntityWithGroup();
        bookmarkEntity = bookmarkDao.save(bookmarkEntity);
        //When
        Bookmark bookmarks = bookmarkRepository.getBookmarksByShortUrl(bookmarkEntity.getShortUrl());
        //Then
        assertThat(bookmarks).isNotNull();
        assertThat(bookmarks).extracting("title", "description")
                .contains(bookmarkEntity.getTitle(), bookmarkEntity.getDescription());
    }


    @Test
    @DisplayName("should get bookmarks using database")
    public void shouldGetBookmarksUsingDataBase() {
        //Given
        BookmarkEntity bookmarkEntity = mockBookmarkEntity();
        bookmarkDao.save(bookmarkEntity);
        //When
        List<Bookmark> bookmarks = bookmarkRepository.getBookmarks(null);
        //Then
        assertThat(bookmarks).isNotEmpty();
        assertThat(bookmarks).extracting("title", "description")
                .contains(tuple(bookmarkEntity.getTitle(), bookmarkEntity.getDescription()));
    }

    private BookmarkEntity mockBookmarkEntity() {
        return BookmarkEntity.builder().actualUrl("https://www.google.com/").shortUrl("https://g.com").expiredDate(LocalDate.now().plusDays(1))
                .description("test").title("Google").source("sg").createdBy("keerti").createdOn(LocalDate.now()).build();
    }

    private BookmarkEntity mockBookmarkEntityWithGroup() {
        GroupEntity groupEntity = mockGroupEntity();
        groupDao.save(groupEntity);
        return BookmarkEntity.builder().groupEntity(groupEntity).actualUrl("https://www.google.com/").shortUrl("https://g.com")
                .description("test").title("Google").source("sg").createdBy("keerti").createdOn(LocalDate.now().plusDays(1)).build();
    }

    private GroupEntity mockGroupEntity() {
        return GroupEntity.builder().description("itec desc").name("ITEC").build();
    }

}
