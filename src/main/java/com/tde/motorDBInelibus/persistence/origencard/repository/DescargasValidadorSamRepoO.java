package com.tde.motorDBInelibus.persistence.origencard.repository;

import java.util.List;


import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.tde.motorDBInelibus.persistence.origencard.entity.DescargasValidadorSamO;

@Repository
public interface DescargasValidadorSamRepoO extends CrudRepository<DescargasValidadorSamO, Long> {

	List<DescargasValidadorSamO> findByIdDgprsGreaterThan(
	        Long lastId,
	        Pageable pageable
	);
    
}
