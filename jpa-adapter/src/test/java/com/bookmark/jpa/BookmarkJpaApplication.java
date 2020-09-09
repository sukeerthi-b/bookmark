package com.bookmark.jpa;

import com.bookmark.domain.port.ObtainBookmark;
import com.bookmark.domain.port.ObtainGroup;
import com.bookmark.jpa.dao.BookmarkDao;
import com.bookmark.jpa.dao.GroupDao;
import com.bookmark.jpa.repository.BookmarkRepository;
import com.bookmark.jpa.repository.GroupRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookmarkJpaApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookmarkJpaApplication.class);
    }

    @TestConfiguration
    static class BookmarkJpaTestConfiguration {
        @Bean
        public ObtainBookmark getBookmarkRepository(final BookmarkDao bookmarkDao) {
            return new BookmarkRepository(bookmarkDao);
        }

        @Bean
        public ObtainGroup getGroupRepository(final GroupDao groupDao) {
            return new GroupRepository(groupDao);
        }
    }
}
