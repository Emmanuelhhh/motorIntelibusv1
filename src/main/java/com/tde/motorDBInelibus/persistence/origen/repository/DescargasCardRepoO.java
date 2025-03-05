package com.tde.motorDBInelibus.persistence.origen.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tde.motorDBInelibus.persistence.origen.entity.DescargasCardO;



@Repository
public interface DescargasCardRepoO extends CrudRepository<DescargasCardO, Long> {
	
	
	@Query(value = "SELECT TOP (100) * " +
            "FROM [INTELIBUS].[dbo].[tblDescargasCARD] " +
            "WHERE  intVarControl = :varControl " +
            "ORDER BY id", 
            nativeQuery = true)
Iterable<DescargasCardO> findTopByVarControl(@Param("varControl") Integer varControl); 	

}
