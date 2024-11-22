package com.tde.motorDBInelibus.persistence.destino.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tde.motorDBInelibus.persistence.destino.entity.DescargasMiniSigoD;


@Repository
public interface DescargasMiniSigoRepoD extends CrudRepository<DescargasMiniSigoD, Long> {

}
