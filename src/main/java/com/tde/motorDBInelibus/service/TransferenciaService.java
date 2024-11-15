package com.tde.motorDBInelibus.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.tde.motorDBInelibus.persistence.repository.destino.DescargasAvlRepoD;
import com.tde.motorDBInelibus.persistence.repository.origen.DescargasAvlRepoO;
import com.tde.motorDBInelibus.persistence.entity.origen.*;
import com.tde.motorDBInelibus.persistence.entity.destino.*;


@Service
public class TransferenciaService {

    @Autowired
    private DescargasAvlRepoO descargasAvlRepoO;

    @Autowired
    private DescargasAvlRepoD descargasAvlRepoD;

    @Transactional
    public void transferirDatos(Integer status) {
        // Paso 1: Obtener datos de la base de datos origen con el estatus específico
        Iterable<DescargasAvlO> registros = descargasAvlRepoO.findTopByVarControl(status);

        // Paso 2: Guardar en la base de datos destino
        List<DescargasAvlD> registrosDestino = new ArrayList<>();
        
        for (DescargasAvlO registroO : registros) {
            DescargasAvlD registroD = convertirADestino(registroO);
            registrosDestino.add(registroD);
        }
        
        descargasAvlRepoD.saveAll(registrosDestino);

        // Paso 3: Eliminar de la base de datos origen los registros que @@fueron transferidos
        descargasAvlRepoO.deleteAll(registros);
    }
    
    

private DescargasAvlD convertirADestino(DescargasAvlO origen) {
    DescargasAvlD destino = new DescargasAvlD();

    destino.setTipoAvl(origen.getTipoAvl());
    destino.setIdModem(origen.getIdModem());
    destino.setLongitudGrad(origen.getLongitudGrad());
    destino.setLatidudGrad(origen.getLatidudGrad());
    destino.setVelocidad(origen.getVelocidad());
    destino.setNumSat(origen.getNumSat());
    destino.setHeading(origen.getHeading());
    destino.setFechaHoraSat(origen.getFechaHoraSat());
    destino.setTipoEvento(origen.getTipoEvento());
    destino.setVariable1(origen.getVariable1());
    destino.setFechaHoraComputadora(origen.getFechaHoraComputadora());
    destino.setVarControl(origen.getVarControl());

    // Campos adicionales en DescargasAvlD que no están en DescargasAvlO
    // Puedes inicializar `avl` como null o asignar un valor predeterminado
    destino.setAvl(null);  // O asigna un objeto `Avl` según tu lógica.

    return destino;
}
}
