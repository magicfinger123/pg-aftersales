/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.repository;

import com.pgagtersales.pgaftersales.io.entity.OutstandingEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutstandingRepository extends PagingAndSortingRepository<OutstandingEntity,Integer> {
}
