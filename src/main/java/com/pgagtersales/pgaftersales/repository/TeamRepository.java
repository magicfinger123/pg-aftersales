/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.repository;
import com.pgagtersales.pgaftersales.io.entity.ClientsEntity;
import com.pgagtersales.pgaftersales.io.entity.TeamEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends PagingAndSortingRepository<TeamEntity,Integer> {
    TeamEntity findByTeamId(String teamId);
    TeamEntity findByTeamTitle(String teamTitle);

    @Query(value = "SELECT * FROM team where team_member_1 = ?1 or " +
            "team_member_2 = ?1 or team_member_3 = ?1 or " +
            "team_member_4 = ?1 or team_lead = ?1 \n#Pageable\n",nativeQuery = true)
    Page<TeamEntity> findByUserId(String alias, Pageable pageable);

}

