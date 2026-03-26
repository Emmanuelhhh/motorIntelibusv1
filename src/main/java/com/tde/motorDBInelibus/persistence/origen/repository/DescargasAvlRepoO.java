package com.tde.motorDBInelibus.persistence.origen.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.tde.motorDBInelibus.persistence.origen.entity.DescargasAvlO;

@Repository
public interface DescargasAvlRepoO extends CrudRepository<DescargasAvlO, Long> {

	List<DescargasAvlO> findByIdGreaterThan(
			Long lastId,
	        Pageable pageable);
			

	
}
