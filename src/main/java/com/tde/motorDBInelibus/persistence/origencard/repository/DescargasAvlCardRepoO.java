package com.tde.motorDBInelibus.persistence.origencard.repository;


import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tde.motorDBInelibus.persistence.origen.entity.DescargasAvlO;
import com.tde.motorDBInelibus.persistence.origencard.entity.DescargasAvlCardO;



@Repository
public interface DescargasAvlCardRepoO extends CrudRepository<DescargasAvlCardO, Long> {

	
	
	   // @Query("SELECT  a FROM OpeDescargasAVL a WHERE a.varControl = :varControl ORDER BY a.fechaHoraComputadora ASC")

			@Query(value = "SELECT TOP (1) * " +
            "FROM [INTELIBUS].[dbo].[tblDescargasAVL] " +
            "WHERE  1 = 1" +
            "ORDER BY id", 
            nativeQuery = true)
			Iterable<DescargasAvlCardO> findTopByVarControl();

			/*@Transactional
			 @Modifying
			    @Query("UPDATE DescargasAvl d SET d.status = 'SENT' WHERE d.id = ?1")
			    void markAsSendVarControl(Long id);
*/
}
