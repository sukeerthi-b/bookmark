package com.bookmark.rest;

import static com.bookmark.rest.mapper.GroupMapper.GROUP_MAPPER;

import com.bookmark.domain.port.RequestGroup;
import com.bookmark.rest.model.Group;
import com.bookmark.rest.model.GroupDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookmarks")
@CrossOrigin(origins = "http://localhost:4200")
public class GroupController {

    @Autowired
    private RequestGroup requestGroup;

    @PostMapping(value = "/groups", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createBookmark(@RequestBody Group group) {
        requestGroup.save(GROUP_MAPPER.constructGroup(group));
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/groups", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupDetails> getBookmarks() {
        return ResponseEntity.ok(GroupDetails.builder().groups(GROUP_MAPPER.constructGroup(requestGroup.getGroups())).build());
    }
}
