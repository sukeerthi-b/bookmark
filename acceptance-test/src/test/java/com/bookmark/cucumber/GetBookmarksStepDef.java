package com.bookmark.cucumber;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

import com.bookmark.BookmarkE2EApplication;
import com.bookmark.jpa.dao.BookmarkDao;
import com.bookmark.jpa.entity.BookmarkEntity;
import com.bookmark.rest.model.Bookmark;
import com.bookmark.rest.model.BookmarkDetails;
import cucumber.api.DataTable;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import cucumber.api.java8.En;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BookmarkE2EApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = BookmarkE2EApplication.class, loader = SpringBootContextLoader.class)
@TestPropertySource(locations = "classpath:application-test.yml")
@ActiveProfiles("test")
public class GetBookmarksStepDef implements En{
    private static final String LOCALHOST = "http://localhost:";
    private static final String API_URL = "api/v1/bookmarks";
    private BookmarkDao bookmarkDao;
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;
    private ResponseEntity<BookmarkDetails> responseEntity;

    public GetBookmarksStepDef(TestRestTemplate template, BookmarkDao bookmarkDao) {
        this.restTemplate = template;
        this.bookmarkDao = bookmarkDao;

        Given("^the following bookmark information", (DataTable bookmarkDataTable) -> {
            List<BookmarkEntity> bookmarkEntities = bookmarkDataTable.asList(BookmarkEntity.class);
            bookmarkDao.saveAll(bookmarkEntities);
        });

        When("user requests for bookmark details", () -> {
            String url = LOCALHOST + port + "/api/v1/bookmarks";
            responseEntity = restTemplate.exchange(url, HttpMethod.GET, generateHeader(), BookmarkDetails.class);
        });

        Then("user gets bookmark information as", (DataTable response) ->{
            List<Bookmark> bookmarks = response.asList(Bookmark.class);
            List<Bookmark> bookmarksFormResponse = responseEntity.getBody().getBookmarks();
            assertThat(responseEntity).extracting("status").isEqualTo(HttpStatus.OK.value());
            assertThat(bookmarksFormResponse).extracting("title", "source")
                    .contains(tuple(bookmarks.get(0).getTitle(), bookmarks.get(0).getSource()));
        });
    }

    HttpEntity<MultiValueMap<String, String>> generateHeader()  {
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<>(map, headers);
        return request;
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
