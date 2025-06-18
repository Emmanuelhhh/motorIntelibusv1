package com.tde.motorDBInelibus.persistence.origencard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tde.motorDBInelibus.persistence.origencard.entity.DescargasValidadorSamO;

@Repository
public interface DescargasValidadorSamRepoO extends CrudRepository<DescargasValidadorSamO, Long> {

    @Query(value = "SELECT TOP (1) * " +
            "FROM [INTELIBUS].[dbo].[tblDescargasValidadorSAM] " +
            "WHERE 1=1 " +
            "ORDER BY id DESC",
            nativeQuery = true)
    Optional<DescargasValidadorSamO> findTopByVarControl();
}
