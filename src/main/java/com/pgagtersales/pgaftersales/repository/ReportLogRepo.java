/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.repository;

import com.pgagtersales.pgaftersales.io.entity.ClientsEntity;
import com.pgagtersales.pgaftersales.io.entity.InventoryItemEntity;
import com.pgagtersales.pgaftersales.io.entity.ReportLogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportLogRepo extends PagingAndSortingRepository<ReportLogEntity,Integer> {
    @Query(value = "Select * from reports_log r where r.user_id = ?1 && r.date = ?2 \n#Pageable\n", nativeQuery = true)
    Page<ReportLogEntity> findByUserByDate(String alias1, String alias2, Pageable pageable);
    Page<ReportLogEntity> findByUserId(String userId, Pageable pageable);
}
