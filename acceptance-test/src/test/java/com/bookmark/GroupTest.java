package com.bookmark;

import com.bookmark.domain.BookmarkDomain;
import com.bookmark.domain.model.Group;
import com.bookmark.domain.port.ObtainBookmark;
import com.bookmark.domain.port.ObtainGroup;
import com.bookmark.domain.port.RequestGroup;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class GroupTest {

    @Test
    @DisplayName("should get groups with support of stub")
    public void shouldGetGroupsWithSupportOfStub(@Mock ObtainGroup obtainGroup) {
        //Given
        RequestGroup requestGroup = BookmarkDomain.builder().obtainGroup(obtainGroup).build();
        Mockito.when(obtainGroup.getGroups()).thenReturn(mockGroups());
        //when
        List<Group> groups = requestGroup.getGroups();
        Assertions.assertThat(groups).isNotNull();
        Assertions.assertThat(groups.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("should create group with support of stub")
    public void shouldCreateGroupWithSupportOfStub(@Mock ObtainGroup obtainGroup) {
        //Given
        RequestGroup requestGroup = BookmarkDomain.builder().obtainGroup(obtainGroup).build();
        //Mockito.doNothing().when(obtainGroup).save(mockGroups().get(0));
        //when
        //requestGroup.save(mockGroups().get(0));
        //Mockito.verify(obtainGroup, Mockito.times(1)).save(mockGroups().get(0));
    }

    private List<com.bookmark.domain.model.Group> mockGroups() {
        List<com.bookmark.domain.model.Group> groups = new ArrayList<>();
        groups.add(Group.builder().name("test").description("test").build());
        return groups;
    }
}
