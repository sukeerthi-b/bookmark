package com.bookmark;

import com.bookmark.domain.BookmarkDomain;
import com.bookmark.domain.port.ObtainBookmark;
import com.bookmark.domain.port.ObtainGroup;
import com.bookmark.domain.port.RequestBookmark;
import com.bookmark.domain.port.RequestGroup;
import com.bookmark.jpa.config.BookmarkJpaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;


@SpringBootApplication
public class BookmarkE2EApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookmarkE2EApplication.class, args);
    }

    @TestConfiguration
    @Import({BookmarkJpaConfig.class})
    static class BookmarkConfig {

        @Primary
        @Bean
        public RequestBookmark getRequestBookmark(final ObtainBookmark obtainBookmark,
                                                  final ObtainGroup obtainGroup) {
            return BookmarkDomain.builder().obtainGroup(obtainGroup).obtainBookmark(obtainBookmark).build();
        }

        @Bean
        public RequestGroup getRequestGroup(final ObtainBookmark obtainBookmark, final ObtainGroup obtainGroup) {
            return BookmarkDomain.builder().obtainGroup(obtainGroup).obtainBookmark(obtainBookmark).build();
        }
    }
}
