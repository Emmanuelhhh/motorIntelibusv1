package com.tde.motorDBInelibus.persistence.origen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tde.motorDBInelibus.persistence.origen.entity.RxMensajeO;

@Repository
public interface TablaOrigenRepo extends JpaRepository<RxMensajeO, Long> {
    
    @Query(value = "SELECT COUNT(*)"
    		+ " FROM tblRXMensaje t", 
            nativeQuery = true)
    long 	countRxmensaje();

    //@Query("SELECT COUNT(t) FROM rxavl t")
    //long countRxavl();

    //@Query("SELECT COUNT(t) FROM evento_mini_sigo t")
    //long countEventoMiniSigo();
}
