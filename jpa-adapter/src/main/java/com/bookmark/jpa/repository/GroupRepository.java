package com.bookmark.jpa.repository;

import static com.bookmark.jpa.mapper.GroupMapper.GROUP_MAPPER;

import com.bookmark.domain.model.Group;
import com.bookmark.domain.port.ObtainGroup;
import com.bookmark.jpa.dao.GroupDao;

import java.util.List;

public class GroupRepository implements ObtainGroup {
    private GroupDao groupDao;

    public GroupRepository(final GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public List<Group> getGroups() {
        return GROUP_MAPPER.constructGroup(groupDao.findAll());
    }
}
