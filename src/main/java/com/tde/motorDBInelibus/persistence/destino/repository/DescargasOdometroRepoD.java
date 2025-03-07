package com.tde.motorDBInelibus.persistence.destino.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tde.motorDBInelibus.persistence.destino.entity.DescargasOdometroD;


@Repository
public interface DescargasOdometroRepoD extends CrudRepository<DescargasOdometroD, Long>{

}
