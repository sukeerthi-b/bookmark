package com.bookmark.cucumber;

import com.bookmark.BookmarkE2EApplication;
import com.bookmark.jpa.dao.GroupDao;
import com.bookmark.jpa.entity.GroupEntity;
import com.bookmark.rest.model.Group;
import cucumber.api.DataTable;
import cucumber.api.java8.En;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BookmarkE2EApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = BookmarkE2EApplication.class, loader = SpringBootContextLoader.class)
@TestPropertySource(locations = "classpath:application-test.yml")
@ActiveProfiles("test")
public class CreateGroupStepDef implements En {
    private static final String LOCALHOST = "http://localhost:";
    private static final String API_URL = "api/v1/bookmarks";
    private GroupDao groupDao;
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;
    private ResponseEntity responseEntity;
    private Group group;

    public CreateGroupStepDef(TestRestTemplate template, GroupDao groupDao) {
        this.restTemplate = template;
        this.groupDao = groupDao;

        Given("^the following group details", (DataTable groupDataTable) -> {
            group = groupDataTable.asList(Group.class).get(0);
        });

        When("^user creates a group", () -> {
            String url = LOCALHOST + port + "/api/v1/bookmarks/groups";
            HttpEntity<Group> httpEntity = new HttpEntity<>(group);
            responseEntity = restTemplate.postForEntity(url, httpEntity, Group.class);
        });

        Then("^the following group created", (DataTable response) ->{
            List<GroupEntity> groups = groupDao.findAll();
            assertThat(responseEntity).extracting("status").isEqualTo(HttpStatus.OK.value());
            assertThat(groups.size()).isEqualTo(1);
            assertThat(groups.get(0)).extracting("name", "description")
                    .contains(tuple(group.getName(), group.getDescription()));
        });

    }

    HttpEntity<MultiValueMap<String, String>> generateHeader()  {
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<>(map, headers);
        return request;
    }

    private void dbCleanUp() {
        groupDao.deleteAll();
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
