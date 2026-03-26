package com.tde.motorDBInelibus.persistence.destino.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tde.motorDBInelibus.persistence.destino.entity.DescargasOdometroD;


@Repository
public interface DescargasOdometroRepoD extends CrudRepository<DescargasOdometroD, Long>{

	
	Optional<DescargasOdometroD> findTopByOrderByIdDgprsDesc();
}
