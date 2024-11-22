package com.tde.motorDBInelibus.persistence.destino.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tde.motorDBInelibus.persistence.destino.entity.DescargasAvlD;

@Repository
public interface DescargasAvlRepoD extends CrudRepository<DescargasAvlD , Long> {

	Optional<DescargasAvlD> findById(Long id);
	
	
}