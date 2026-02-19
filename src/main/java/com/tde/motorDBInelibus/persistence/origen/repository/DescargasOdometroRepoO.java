package com.tde.motorDBInelibus.persistence.origen.repository;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tde.motorDBInelibus.persistence.origen.entity.DescargasOdometroO;


@Repository
public interface DescargasOdometroRepoO extends CrudRepository<DescargasOdometroO, Long>{

	
	@Query(value = "SELECT TOP (100) * " +
            "FROM [INTELIBUS].[dbo].[tblDescargasOdometro] " +
            "WHERE  intVarControl = :varControl " +
            "ORDER BY [id_DGPRS] ", 
            nativeQuery = true)
	Iterable<DescargasOdometroO> findTopByVarControl(@Param("varControl") Integer varControl);
	
	@Query(value = "SELECT TOP (500) * " +
            "FROM [INTELIBUS].[dbo].[tblDescargasOdometro] " +
           
            "ORDER BY [id_DGPRS] ", 
            nativeQuery = true)
	Iterable<DescargasOdometroO> findTop();
}
