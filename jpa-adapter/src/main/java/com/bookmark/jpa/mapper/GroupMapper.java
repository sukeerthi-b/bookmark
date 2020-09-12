package com.bookmark.jpa.mapper;


import static java.util.Objects.nonNull;
import static org.springframework.util.CollectionUtils.isEmpty;

import com.bookmark.domain.model.Group;
import com.bookmark.jpa.entity.GroupEntity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum GroupMapper {
    GROUP_MAPPER;

    public List<Group> constructGroup(final List<GroupEntity> groupEntities) {
        return !isEmpty(groupEntities) ? groupEntities.stream().map(this::constructGroup).collect(Collectors.toList())
                : Collections.emptyList();
    }

    public Group constructGroup(final GroupEntity groupEntity) {
        return nonNull(groupEntity) ? Group.builder().description(groupEntity.getDescription()).id(groupEntity.getId())
                .name(groupEntity.getName()).build() : null;
    }

    public List<GroupEntity> constructGroupEntity(final List<Group> groupEntities) {
        return isEmpty(groupEntities) ? groupEntities.stream().map(this::constructGroupEntity).collect(Collectors.toList())
                : Collections.emptyList();
    }

    public GroupEntity constructGroupEntity(final Group group) {
        return nonNull(group) ? GroupEntity.builder().description(group.getDescription()).id(group.getId())
                .name(group.getName()).build() : null;
    }
}
