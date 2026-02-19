package com.tde.motorDBInelibus.persistence.origencard.repository;


import org.springframework.data.repository.CrudRepository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tde.motorDBInelibus.persistence.origencard.entity.DescargasAvlCardO;



@Repository
public interface DescargasAvlCardRepoO extends CrudRepository<DescargasAvlCardO, Long> {

	
	
	   // @Query("SELECT  a FROM OpeDescargasAVL a WHERE a.varControl = :varControl ORDER BY a.fechaHoraComputadora ASC")

			@Query(value = "SELECT TOP (1) * " +
            "FROM [INTELIBUS].[dbo].[tblDescargasAVL] " +
            "ORDER BY id", 
            nativeQuery = true)
			Iterable<DescargasAvlCardO> findTopByVarControl();

			
			@Query(value = "SELECT TOP (500) * " +
		            "FROM [INTELIBUS].[dbo].[tblDescargasAVL] " +
		            "ORDER BY id", 
		            nativeQuery = true)
					Iterable<DescargasAvlCardO> findTop();

			List<DescargasAvlCardO> findByIdGreaterThanOrderByIdAsc(
			        Long lastId,
			        Pageable pageable
			);
			/*@Transactional
			 @Modifying
			    @Query("UPDATE DescargasAvl d SET d.status = 'SENT' WHERE d.id = ?1")
			    void markAsSendVarControl(Long id);
*/
}
