package com.bookmark.domain.port;

import com.bookmark.domain.model.Group;

import java.util.List;

public interface RequestGroup {
    List<Group> getGroups();

    void save(Group group);
}
