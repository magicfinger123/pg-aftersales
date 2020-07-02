package com.pgagtersales.pgaftersales.repository;


import com.pgagtersales.pgaftersales.io.entity.ClientsEntity;
import com.pgagtersales.pgaftersales.io.entity.GeneratorEntity;
import com.pgagtersales.pgaftersales.io.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
public interface GeneratorRepository extends PagingAndSortingRepository<GeneratorEntity,Integer> {

    @Query(value = "SELECT * FROM `generators` WHERE (CONVERT(`id` USING utf8) LIKE %?1% OR " +
            "CONVERT(`client_id` USING utf8) LIKE %?1% OR CONVERT(`location` USING utf8) LIKE %?1% OR " +
            "CONVERT(`engine_serial` USING utf8) LIKE %?1% OR CONVERT(`alternator_serial` USING utf8) LIKE %?1% OR " +
            "CONVERT(`engine_model` USING utf8) LIKE %?1% OR CONVERT(`fuel_capacity` USING utf8) LIKE %?1% OR " +
            "CONVERT(`fuel_consumption_rate` USING utf8) LIKE %?1% OR CONVERT(`size` USING utf8) LIKE %?1% OR " +
            "CONVERT(`generator_type` USING utf8) LIKE %?1% OR CONVERT(`purchase_year` USING utf8) LIKE %?1% )\n#Pageable\n", nativeQuery = true)
    Page<GeneratorEntity> searchGenerator(String alias, Pageable pageable);
    @Query(value = "select * FROM `generators` where `client_id` = ?1 \n#Pageable\n", nativeQuery = true)
    Page<GeneratorEntity> findByClientId(String clientId, Pageable pageable);
    GeneratorEntity findById(int id);
    GeneratorEntity findByEngineSerial(String engineSerial);

   // List<GeneratorEntity> findAllByClientDto(ClientsEntity clientsEntity);
    List<GeneratorEntity> findAllByOrderByDateDesc();
    /*@Query(value = "SELECT DISTINCT c.id As id, g.engine_serial from clients c inner join generators g on c.id=g.client_id where c.id = '1'", nativeQuery = true)
    List<Object> findAllByClientDto(String id);*/

}
