package com.tde.motorDBInelibus.persistence.origen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tde.motorDBInelibus.persistence.origen.entity.DescargasMiniSigoO;

import org.springframework.data.domain.Pageable;

@Repository
public interface DescargasMiniSigoRepoO extends CrudRepository<DescargasMiniSigoO, Long> {

	
	List<DescargasMiniSigoO> findByIdDGPRSGreaterThan(
	        Long lastId,
	        Pageable pageable
	);

 
}
