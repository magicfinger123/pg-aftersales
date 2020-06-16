/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */
package com.pgagtersales.pgaftersales.repository;
import com.pgagtersales.pgaftersales.io.entity.InventoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends PagingAndSortingRepository<InventoryEntity,Integer> {
    Page<InventoryEntity> findByScheduleId(String scheduleId, Pageable pageable);
    Page<InventoryEntity> findByTeamId(String teamId, Pageable pageable);
    InventoryEntity findByInventoryId(String teamTitle);
    InventoryEntity findByInventoryName(String teamTitle);

}
