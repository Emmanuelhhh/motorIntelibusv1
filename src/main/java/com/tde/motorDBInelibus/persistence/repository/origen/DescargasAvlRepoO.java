package com.tde.motorDBInelibus.persistence.repository.origen;


import com.tde.motorDBInelibus.persistence.entity.origen.DescargasAvlO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import jakarta.transaction.Transactional;


public interface DescargasAvlRepoO extends CrudRepository<DescargasAvlO, Long> {

	
	
	   // @Query("SELECT  a FROM OpeDescargasAVL a WHERE a.varControl = :varControl ORDER BY a.fechaHoraComputadora ASC")

			@Query(value = "SELECT TOP (100) * " +
            "FROM [SIGOWEB].[dbo].[tblDescargasAVL_MINISIGO] " +
            "WHERE id > 402045214 AND strModemID = 4675784291 AND int_var_control = :varControl " +
            "ORDER BY id ASC", 
            nativeQuery = true)
			Iterable<DescargasAvlO> findTopByVarControl(@Param("varControl") Integer varControl);

			/*@Transactional
			 @Modifying
			    @Query("UPDATE DescargasAvl d SET d.status = 'SENT' WHERE d.id = ?1")
			    void markAsSendVarControl(Long id);
*/
}
