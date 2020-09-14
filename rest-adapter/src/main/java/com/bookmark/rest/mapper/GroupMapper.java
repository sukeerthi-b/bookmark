package com.bookmark.rest.mapper;


import com.bookmark.domain.model.Group;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;
import static org.springframework.util.CollectionUtils.isEmpty;

public enum GroupMapper {
    GROUP_MAPPER;

    public List<com.bookmark.rest.model.Group> constructGroup(final List<Group> groups) {
        return !isEmpty(groups) ? groups.stream().map(this::constructGroup).collect(Collectors.toList())
                : Collections.emptyList();
    }

    public com.bookmark.rest.model.Group constructGroup(final Group group) {
        return nonNull(group) ? com.bookmark.rest.model.Group.builder().description(group.getDescription()).id(group.getId())
                .name(group.getName()).build() : null;
    }

    public List<Group> constructGroups(final List<com.bookmark.rest.model.Group> groupEntities) {
        return isEmpty(groupEntities) ? groupEntities.stream().map(this::constructGroup).collect(Collectors.toList())
                : Collections.emptyList();
    }

    public Group constructGroup(final com.bookmark.rest.model.Group group) {
        return nonNull(group) ? Group.builder().description(group.getDescription()).id(group.getId())
                .name(group.getName()).build() : null;
    }
}
