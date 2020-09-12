package com.bookmark.jpa;


import static org.assertj.core.api.Assertions.assertThat;

import com.bookmark.domain.model.Group;
import com.bookmark.jpa.dao.GroupDao;
import com.bookmark.jpa.entity.GroupEntity;
import com.bookmark.jpa.repository.GroupRepository;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class GroupJpaTest {

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private GroupRepository groupRepository;

    @DisplayName("should get groups using data base")
    @Test
    public void shouldGetGroupsUsingDatabase() {
        //given group details
        GroupEntity groupEntity = mockGroupEntity();
        groupDao.save(groupEntity);
        //when
        List<Group> groups = groupRepository.getGroups();
        //then
        assertThat(groups).isNotNull();
        assertThat(groups).extracting("name", "description").contains(
                Tuple.tuple(groupEntity.getName(), groupEntity.getDescription()));
    }

    private GroupEntity mockGroupEntity() {
        return GroupEntity.builder().description("itec desc").name("ITEC").build();
    }
}
