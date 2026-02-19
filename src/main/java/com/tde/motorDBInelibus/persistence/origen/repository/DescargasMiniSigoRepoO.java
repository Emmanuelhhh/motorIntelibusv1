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

	
	List<DescargasMiniSigoO> findByValControlAndIdDGPRSGreaterThan(Integer valControl, Long lastId, Pageable pageable);

	//se creo un metodo son el filtro valControl
	List<DescargasMiniSigoO> findByIdDGPRSGreaterThanOrderByIdDGPRSAsc(
	        Long lastId,
	        Pageable pageable
	);

	@Query(value = "SELECT TOP (100) * " +
            "FROM [INTELIBUS].[dbo].[tblDescarga_MiniSigo] " +
            "WHERE  intVarControl = :varControl " +
            "ORDER BY id_DGPRS", 
            nativeQuery = true)
			Iterable<DescargasMiniSigoO> findTopByVarControl(@Param("varControl") Integer varControl);
	
	 @Modifying
	    @Query("DELETE FROM DescargasMiniSigoO t WHERE t.idDGPRS IN :ids")
	    int deleteByIdDGprsIn(@Param("ids") List<Long> ids);
}
