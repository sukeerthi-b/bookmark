package com.bookmark.domain.port;

import com.bookmark.domain.model.Group;

import java.util.List;

public interface ObtainGroup {
    List<Group> getGroups();
    void save(Group group);
}
