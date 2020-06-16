/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.repository;

import com.pgagtersales.pgaftersales.io.entity.ScheduleEntity;
import com.pgagtersales.pgaftersales.io.entity.TeamEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends PagingAndSortingRepository<ScheduleEntity,Integer> {
    ScheduleEntity findByScheduleId(String scheduleId);
    Page<ScheduleEntity> findByTeamId(String teamId, Pageable pageable);
    ScheduleEntity findByScheduleName(String teamTitle);
}