package com.tde.motorDBInelibus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tde.motorDBInelibus.persistence.destino.repository.StoredProcedureRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoredProcedureServiceJPA {

    private static final Logger log = LoggerFactory.getLogger(StoredProcedureServiceJPA.class);

    @Autowired
    private StoredProcedureRepository spRepository;

    public boolean ejecutarStoredProcedure() {
        try {
            log.info("Iniciando ejecución de sp_AnalizaPasaje...");
            spRepository.spAnalizaPasaje();
            log.info("Stored procedure ejecutado correctamente");
            return true;
        } catch (Exception e) {
            log.error("Error al ejecutar sp_AnalizaPasaje: {}", e.getMessage(), e);
            // Aquí podrías optar por re-lanzar una excepción personalizada si lo deseas.
            return false;
        }
    }
}
