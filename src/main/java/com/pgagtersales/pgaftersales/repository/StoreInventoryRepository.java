/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */
package com.pgagtersales.pgaftersales.repository;
import com.pgagtersales.pgaftersales.io.entity.InventoryEntity;
import com.pgagtersales.pgaftersales.io.entity.StoreInventoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreInventoryRepository extends PagingAndSortingRepository<StoreInventoryEntity,Integer> {
    StoreInventoryEntity findByInventoryId(String teamTitle);
    StoreInventoryEntity findByInventoryName(String teamTitle);
}
