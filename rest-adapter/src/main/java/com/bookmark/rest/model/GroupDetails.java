package com.bookmark.rest.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GroupDetails {
    private List<Group> groups;
}
