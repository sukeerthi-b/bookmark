package com.bookmark.rest;

import com.bookmark.domain.model.Bookmark;
import com.bookmark.domain.model.Group;
import com.bookmark.domain.port.RequestGroup;
import com.bookmark.rest.model.BookmarkDetails;
import com.bookmark.rest.model.GroupDetails;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = BookmarksRestAdapterApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@ActiveProfiles("test")
public class GroupResourceTest {

    @Autowired
    private RequestGroup requestGroup;
    private static final String LOCALHOST= "http://localhost:";
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("should get groups with support of stub")
    public void shouldGetgroupsWithSupportOfStub() throws JsonProcessingException {
        //given
        Mockito.lenient().when(requestGroup.getGroups()).thenReturn(mockGroups());
        //when
        String url = LOCALHOST + port + "/api/v1/bookmarks/groups";
        ResponseEntity<GroupDetails> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, generateHeader(), GroupDetails.class);
        //Then
        GroupDetails groupDetails = responseEntity.getBody();
        assertThat(groupDetails).isNotNull();
        assertThat(groupDetails.getGroups()).isNotNull();
    }

    @Test
    @DisplayName("should create bookmark with support of stub")
    public void shouldCreateBookmarksWithSupportOfStub() {
        //given
        Group group = mockGroups().get(0);
        Mockito.doNothing().when(requestGroup).save(group);
        //when
        String url = LOCALHOST + port + "/api/v1/bookmarks";
        HttpEntity<com.bookmark.rest.model.Group> entity =
                new HttpEntity<>(com.bookmark.rest.model.Group.builder().name("test").description("test").build());
        ResponseEntity responseEntity = restTemplate.postForEntity(url, entity, GroupDetails.class);
        //Then
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }


    HttpEntity<MultiValueMap<String, String>> generateHeader() {
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<>(map, headers);
        return request;
    }

    private List<Group> mockGroups() {
        List<Group> groups = new ArrayList<>();
        groups.add(Group.builder().name("test").description("test").build());
        return  groups;
    }
}
