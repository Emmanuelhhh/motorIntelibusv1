package com.tde.motorDBInelibus.persistence.origen.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import com.tde.motorDBInelibus.persistence.origen.entity.DescargasTDEO;

public interface DescargasTdeRepoO extends CrudRepository<DescargasTDEO, Long> {

	
	@Query(value = "SELECT TOP (100) * " +
            "FROM [INTELIBUS].[dbo].[tblDescargasTDE] " +
            "WHERE  intVarControl = :varControl " +
            "ORDER BY id_DGPRS", 
            nativeQuery = true)
			Iterable<DescargasTDEO> findTopByVarControl(@Param("varControl") Integer varControl);
}
