package com.bookmark.jpa.repository;

import static com.bookmark.jpa.mapper.GroupMapper.GROUP_MAPPER;

import com.bookmark.domain.model.Group;
import com.bookmark.domain.port.ObtainGroup;
import com.bookmark.jpa.dao.GroupDao;

import java.util.List;
import java.util.Objects;

public class GroupRepository implements ObtainGroup {
    private GroupDao groupDao;

    public GroupRepository(final GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public List<Group> getGroups() {
        return GROUP_MAPPER.constructGroup(groupDao.findAll());
    }

    @Override
    public void save(Group group) {
        if(Objects.nonNull(group)) {
            groupDao.save(GROUP_MAPPER.constructGroupEntity(group));
        }
    }
}
