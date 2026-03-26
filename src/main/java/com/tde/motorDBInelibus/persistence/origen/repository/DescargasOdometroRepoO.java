package com.tde.motorDBInelibus.persistence.origen.repository;



import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import com.tde.motorDBInelibus.persistence.origen.entity.DescargasOdometroO;


@Repository
public interface DescargasOdometroRepoO extends CrudRepository<DescargasOdometroO, Long>{

	
	List<DescargasOdometroO> findByIdDgprsGreaterThan(Long lastId, Pageable page);
}
