package com.tde.motorDBInelibus.persistence.origen.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tde.motorDBInelibus.persistence.origen.entity.DescargasMiniSigoO;



@Repository
public interface DescargasMiniSigoRepoO extends CrudRepository<DescargasMiniSigoO, Long> {

	
	
	@Query(value = "SELECT TOP (100) * " +
            "FROM [INTELIBUS].[dbo].[tblDescarga_MiniSigo] " +
            "WHERE  intVarControl = :varControl " +
            "ORDER BY id_DGPRS desc", 
            nativeQuery = true)
			Iterable<DescargasMiniSigoO> findTopByVarControl(@Param("varControl") Integer varControl);
}
