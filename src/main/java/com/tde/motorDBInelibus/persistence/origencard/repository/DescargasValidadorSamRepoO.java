package com.tde.motorDBInelibus.persistence.origencard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tde.motorDBInelibus.persistence.origencard.entity.DescargasValidadorSamO;

@Repository
public interface DescargasValidadorSamRepoO extends CrudRepository<DescargasValidadorSamO, Long> {

    @Query(value = "SELECT TOP (500) * " +
            "FROM [INTELIBUS].[dbo].[tblDescargasValidadorSAM] " +
            
            "ORDER BY id",
            nativeQuery = true)
    Iterable<DescargasValidadorSamO> findTop();
}
