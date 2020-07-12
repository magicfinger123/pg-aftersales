/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.repository;

import com.pgagtersales.pgaftersales.io.entity.TicketEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TicketRepository extends PagingAndSortingRepository<TicketEntity,Integer> {
    Page<TicketEntity> findByClientId(String clientId, Pageable pageable);
    Page<TicketEntity> findByStatus(int status, Pageable pageable);
    TicketEntity findById(int id);
}
