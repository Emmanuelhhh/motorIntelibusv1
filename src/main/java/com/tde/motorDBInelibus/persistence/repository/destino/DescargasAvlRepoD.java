package com.tde.motorDBInelibus.persistence.repository.destino;

import com.tde.motorDBInelibus.persistence.entity.destino.*;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


public interface DescargasAvlRepoD extends CrudRepository<DescargasAvlD , Long> {

	Optional<DescargasAvlD> findById(Long id);
	
	
}