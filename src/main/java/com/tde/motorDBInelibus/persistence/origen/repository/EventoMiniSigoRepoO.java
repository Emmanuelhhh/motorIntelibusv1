package com.tde.motorDBInelibus.persistence.origen.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tde.motorDBInelibus.persistence.destino.entity.EventoMiniSigoD;
import com.tde.motorDBInelibus.persistence.origen.entity.EventoMiniSigoO;

@Repository
public interface EventoMiniSigoRepoO extends CrudRepository<EventoMiniSigoO, Long> {

    @Query(value = "SELECT TOP (100) * " +
            "FROM [INTELIBUS].[dbo].[tblEvento_MiniSigo] " +
            "WHERE intVarControl = :varControl " +
            "ORDER BY id_dgprs", 
            nativeQuery = true)
    Iterable<EventoMiniSigoO> findTopByVarControl(@Param("varControl") Integer varControl);

}
