package com.tde.motorDBInelibus.persistence.origencard.repository;


import org.springframework.data.repository.CrudRepository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tde.motorDBInelibus.persistence.origencard.entity.DescargasAvlCardO;



@Repository
public interface DescargasAvlCardRepoO extends CrudRepository<DescargasAvlCardO, Long> {

	
			List<DescargasAvlCardO> findByIdGreaterThan(
			        Long lastId,
			        Pageable pageable
			);
			
			List<DescargasAvlCardO> findByVarControl(Integer control, Pageable pageable);
}
