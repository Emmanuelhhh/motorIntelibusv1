package com.tde.motorDBInelibus.persistence.destino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.tde.motorDBInelibus.persistence.destino.entity.DescargasMiniSigoD;

@Repository
public interface StoredProcedureRepository extends JpaRepository<DescargasMiniSigoD, Long> {

    @Procedure(procedureName = "sp_AnalizaPasaje")
    void spAnalizaPasaje();
}
