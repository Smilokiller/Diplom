package com.example.diplom.dal.api;

import com.example.diplom.entity.GroupsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupDao extends JpaRepository<GroupsEntity, Integer> {

}