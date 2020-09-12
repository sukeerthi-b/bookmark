package com.bookmark.cucumber;

//import cucumber.api.java8.En;

import com.bookmark.BookmarkE2EApplication;
import com.bookmark.domain.model.Bookmark;
import com.bookmark.jpa.dao.BookmarkDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BookmarkE2EApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = BookmarkE2EApplication.class, loader = SpringBootContextLoader.class)
@TestPropertySource(locations = "classpath:application-test.yml")
@ActiveProfiles("test")
public class GetBookmarksStepDef {
    private static final String LOCALHOST = "http://localhost:";
    private static final String API_URL = "api/v1/bookmarks";
    private BookmarkDao bookmarkDao;
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;
    private ResponseEntity<List<Bookmark>> bookmarkResponse;

    public GetBookmarksStepDef(TestRestTemplate template, BookmarkDao bookmarkDao) {
        this.restTemplate = template;
        this.bookmarkDao = bookmarkDao;

//        Given("^the following bookmark information", (DataTable privateListDataTable) -> {
//
//        });
    }

    private void dbCleanUp() {
        bookmarkDao.deleteAll();
    }
    @BeforeEach
    public void setup() {
        dbCleanUp();
    }

    @AfterEach
    public void cleanUp() {
        dbCleanUp();
    }
}
