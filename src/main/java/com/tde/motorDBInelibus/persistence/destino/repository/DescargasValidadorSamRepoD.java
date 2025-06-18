package com.tde.motorDBInelibus.persistence.destino.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tde.motorDBInelibus.persistence.destino.entity.DescargasValidadorSamD;
import com.tde.motorDBInelibus.persistence.origencard.entity.DescargasValidadorSamO;

@Repository
public interface DescargasValidadorSamRepoD extends CrudRepository<DescargasValidadorSamD, Long> {

   
    Optional<DescargasValidadorSamD> findTopByIdDgprs(@Param("idDgprs") Long idDgprs);
}