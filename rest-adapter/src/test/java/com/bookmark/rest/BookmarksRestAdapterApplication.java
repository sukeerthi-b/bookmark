package com.bookmark.rest;

import com.bookmark.domain.port.ObtainBookmark;
import com.bookmark.domain.port.ObtainGroup;
import com.bookmark.domain.port.RequestBookmark;
import com.bookmark.domain.port.RequestGroup;
import com.bookmark.rest.model.Bookmark;
import org.mockito.Mock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootApplication
public class BookmarksRestAdapterApplication {

    @MockBean
    private RequestBookmark requestBookmark;

    @MockBean
    private RequestGroup requestGroup;

    @Mock
    private ObtainGroup obtainGroup;

    @Mock
    private ObtainBookmark obtainBookmark;

    public static void main(String[] args) {
        SpringApplication.run(BookmarksRestAdapterApplication.class, args);
    }
}
