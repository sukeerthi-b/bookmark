package com.bookmark.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bookmark.domain.model.Bookmark;
import com.bookmark.domain.port.RequestBookmark;
import com.bookmark.rest.model.BookmarkDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = BookmarksRestAdapterApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@ActiveProfiles("test")
public class BookmarkResourceTest {

    private static final String LOCALHOST= "http://localhost:";
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private RequestBookmark requestBookmark;

    @Test
    @DisplayName("should get bookmarks with support of stub")
    public void shouldGetBookmarksWithSupportOfStub() throws JsonProcessingException {
        //given
        //BookmarkDetails bookmarkDetails = BookmarkDetails.builder().bookmarks(mockBookmarks()).build();
        Mockito.lenient().when(requestBookmark.getBookmarks()).thenReturn(mockBookmarks());
        //when
        String url = LOCALHOST + port + "/api/v1/bookmarks";
        ResponseEntity<BookmarkDetails> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, generateHeader(), BookmarkDetails.class);
        //Then
        BookmarkDetails bookmarkDetails = responseEntity.getBody();
        assertThat(bookmarkDetails).isNotNull();
        assertThat(bookmarkDetails.getBookmarks()).isNotNull();
    }

    @Test
    @DisplayName("should get bookmarks with support of stub")
    public void shouldGetBookmarksByGroupIdWithSupportOfStub() throws JsonProcessingException {
        //given
        //BookmarkDetails bookmarkDetails = BookmarkDetails.builder().bookmarks(mockBookmarks()).build();
        Mockito.lenient().when(requestBookmark.getBookmarks(1001L)).thenReturn(mockBookmarks());
        //when
        String url = LOCALHOST + port + "/api/v1/bookmarks/1001";
        ResponseEntity<BookmarkDetails> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, generateHeader(), BookmarkDetails.class);
        //Then
        BookmarkDetails bookmarkDetails = responseEntity.getBody();
        assertThat(bookmarkDetails).isNotNull();
        assertThat(bookmarkDetails.getBookmarks()).isNotNull();
    }

    @Test
    @DisplayName("should redirect to actual url with support of stub")
    public void shouldRedirectToActualUrlWithSupportOfStub() throws JsonProcessingException {
        //given
        //BookmarkDetails bookmarkDetails = BookmarkDetails.builder().bookmarks(mockBookmarks()).build();
        Mockito.lenient().when(requestBookmark.getOriginalURL("test.com")).thenReturn("https:google.com");
        //when
        String url = LOCALHOST + port + "/api/v1/bookmarks/redirect/test.com";
        ResponseEntity<BookmarkDetails> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, generateHeader(), BookmarkDetails.class);
        //Then
        assertEquals(responseEntity.getStatusCode(), HttpStatus.FOUND);
    }

    @Test
    @DisplayName("should create bookmark with support of stub")
    public void shouldCreateBookmarksWithSupportOfStub() {
        //given
        Bookmark bookmark = mockBookmarks().get(0);
        Mockito.doNothing().when(requestBookmark).save(bookmark);
        //when
        String url = LOCALHOST + port + "/api/v1/bookmarks";
        HttpEntity<com.bookmark.rest.model.Bookmark> entity =
                new HttpEntity<>(com.bookmark.rest.model.Bookmark.builder().
                        actualUrl("/test/url").description("test url").title("test").build());
        ResponseEntity responseEntity =
                restTemplate.postForEntity(url, entity, BookmarkDetails.class);
        //Then
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    HttpEntity<MultiValueMap<String, String>> generateHeader() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<>(map, headers);
        return request;
    }

    private List<com.bookmark.domain.model.Bookmark> mockBookmarks() {
        List<Bookmark> bookmarks = new ArrayList<>();
        bookmarks.add(com.bookmark.domain.model.Bookmark.builder().groupId(1001L).actualUrl("/test/url").description("test url").title("test").build());
        return  bookmarks;
    }
}
