package com.tde.motorDBInelibus.persistence.origen.repository;


import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tde.motorDBInelibus.persistence.origen.entity.DescargasAvlO;



@Repository
public interface DescargasAvlRepoO extends CrudRepository<DescargasAvlO, Long> {

	
	
	   // @Query("SELECT  a FROM OpeDescargasAVL a WHERE a.varControl = :varControl ORDER BY a.fechaHoraComputadora ASC")

			@Query(value = "SELECT TOP (2000) * " +
            "FROM [INTELIBUS].[dbo].[tblDescargasAVL] " +
            "WHERE  intVarControl = :varControl " +
            "ORDER BY id", 
            nativeQuery = true)
			Iterable<DescargasAvlO> findTopByVarControl(@Param("varControl") Integer varControl);

			/*@Transactional
			 @Modifying
			    @Query("UPDATE DescargasAvl d SET d.status = 'SENT' WHERE d.id = ?1")
			    void markAsSendVarControl(Long id);
*/
}
