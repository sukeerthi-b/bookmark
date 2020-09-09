package com.bookmark.jpa.dao;

import com.bookmark.jpa.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupDao extends JpaRepository<GroupEntity, Long> {
}
