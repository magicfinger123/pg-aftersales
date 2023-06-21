/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */
package com.pgagtersales.pgaftersales.repository;
import com.pgagtersales.pgaftersales.io.entity.InventoryItemEntity;
import com.pgagtersales.pgaftersales.io.entity.StoreInventoryItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreInventoryItemRepository extends PagingAndSortingRepository<StoreInventoryItemEntity,Integer> {
    Page<StoreInventoryItemEntity> findByInventoryId(String inventoryId, Pageable pageable);
    StoreInventoryItemEntity findByInventoryItemId(String inventoryItemId);
}
