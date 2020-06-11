package com.pgagtersales.pgaftersales.repository;

import com.pgagtersales.pgaftersales.io.entity.ClientsEntity;
import com.pgagtersales.pgaftersales.io.entity.GeneratorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends PagingAndSortingRepository<ClientsEntity,Integer> {

    @Query(value = "SELECT * FROM `clients` WHERE (CONVERT(`id` USING utf8) LIKE %?1% OR " +
            "CONVERT(`username` USING utf8) LIKE %?1% OR CONVERT(`password` USING utf8) LIKE %?1% OR " +
            "CONVERT(`first_name` USING utf8) LIKE %?1% OR CONVERT(`last_name` USING utf8) LIKE %?1% OR" +
            " CONVERT(`title` USING utf8) LIKE %?1% OR CONVERT(`phone` USING utf8) LIKE %?1% OR " +
            "CONVERT(`email` USING utf8) LIKE %?1% OR CONVERT(`company_name` USING utf8) LIKE %?1% OR " +
            "CONVERT(`address` USING utf8) LIKE %?1% OR CONVERT(`cluster` USING utf8) LIKE %?1% OR" +
            " CONVERT(`visible` USING utf8) LIKE %?1% )\n#Pageable\n", nativeQuery = true)
    Page<ClientsEntity> searchClient(String alias, Pageable pageable);
    ClientsEntity findByUsername(String username);
    ClientsEntity findById(int id);
}
