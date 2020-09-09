package com.bookmark.jpa.repository;

import com.bookmark.domain.port.ObtainGroup;
import com.bookmark.jpa.dao.GroupDao;

public class GroupRepository implements ObtainGroup {
    private GroupDao groupDao;

    public GroupRepository(final GroupDao groupDao) {
        this.groupDao = groupDao;
    }
}
