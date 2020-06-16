/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.repository;

import com.pgagtersales.pgaftersales.io.entity.ScheduleEntity;
import com.pgagtersales.pgaftersales.io.entity.ScheduleTaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ScheduleTasKRepository extends PagingAndSortingRepository<ScheduleTaskEntity,Integer> {
    ScheduleTaskEntity findByTaskId(String taskId);
    Page<ScheduleTaskEntity> findByScheduleId(String scheduleId, Pageable pageable);
    ScheduleTaskEntity findByTaskName(String taskName);
}
