package com.bookmark.rest;

import static com.bookmark.rest.mapper.GroupMapper.GROUP_MAPPER;

import com.bookmark.domain.port.RequestGroup;
import com.bookmark.rest.model.Group;
import com.bookmark.rest.model.GroupDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookmarks")
@CrossOrigin(origins = "http://localhost:4200")
public class GroupController {

    private RequestGroup requestGroup;

    public GroupController(final RequestGroup requestGroup) {
        this.requestGroup = requestGroup;
    }

    @Operation(summary = "Create a new group")
    @ApiResponse(responseCode = "200", description = "Successfully created the groups")
    @ApiResponse(responseCode = "404", description = "Service not available")
    @PostMapping(value = "/groups", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createBookmark(@RequestBody Group group) {
        requestGroup.save(GROUP_MAPPER.constructGroup(group));
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "Get list of groups")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all groups")
    @ApiResponse(responseCode = "404", description = "Service not available")
    @GetMapping(value = "/groups", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupDetails> getGroups() {
        return ResponseEntity.ok(GroupDetails.builder().groups(GROUP_MAPPER.constructGroup(requestGroup.getGroups())).build());
    }
}
