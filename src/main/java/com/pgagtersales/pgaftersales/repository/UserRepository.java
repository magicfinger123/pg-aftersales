package com.pgagtersales.pgaftersales.repository;

import com.pgagtersales.pgaftersales.io.entity.TeamEntity;
import com.pgagtersales.pgaftersales.io.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface  UserRepository extends PagingAndSortingRepository<UserEntity,Integer> {
    UserEntity findByUsername(String username);
    UserEntity findByUserId(String username);
}
