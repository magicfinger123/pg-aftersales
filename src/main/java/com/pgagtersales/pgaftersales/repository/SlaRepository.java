/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.repository;

import com.pgagtersales.pgaftersales.io.entity.SlaEntity;
import com.pgagtersales.pgaftersales.io.entity.SlaPriceListEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlaRepository extends CrudRepository<SlaEntity, Long> {
    Page<SlaPriceListEntity> findAll(Pageable pageable);
}
