package com.tde.motorDBInelibus.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tde.motorDBInelibus.persistence.origen.*;
import com.tde.motorDBInelibus.persistence.origen.entity.DescargasAvlO;
import com.tde.motorDBInelibus.persistence.origen.repository.DescargasAvlRepoO;
import com.tde.motorDBInelibus.persistence.destino.entity.DescargasAvlD;
import com.tde.motorDBInelibus.persistence.destino.repository.DescargasAvlRepoD;
import com.tde.motorDBInelibus.persistence.destino.*;


@Service
public class AVLTransferenciaService {

    @Autowired
    private DescargasAvlRepoO descargasAvlRepoO;

    @Autowired
    private DescargasAvlRepoD descargasAvlRepoD;

    @Transactional
    public void transferirDatos(Integer status) {
        // Convertimos el Iterable a una lista para poder manipularlo
        List<DescargasAvlO> registros = new ArrayList<>();
        descargasAvlRepoO.findTopByVarControl(status).forEach(registros::add);

        System.out.println("REGISTROS AVL ENCONTRADOS  " + registros);

        // Lista para almacenar los registros que fallaron
        List<DescargasAvlO> registrosFallidos = new ArrayList<>();

        for (DescargasAvlO registroO : registros) {
            try {
                DescargasAvlD registroD = convertirADestino(registroO);
                descargasAvlRepoD.save(registroD); // Guardar registro individualmente
            } catch (Exception e) {
                registrosFallidos.add(registroO); // Almacenar los fallidos
                System.err.println("Error al transferir registro: " + registroO.getIdModem() + ", " + e.getMessage());
            }
        }

        // Elimina solo los registros que no fallaron
        registros.removeAll(registrosFallidos);
        descargasAvlRepoO.deleteAll(registros);

        System.out.println("FIN DEL PROCESO");
    }

    

private DescargasAvlD convertirADestino(DescargasAvlO origen) {
    DescargasAvlD destino = new DescargasAvlD();

    destino.setId(origen.getId());
    destino.setTipoAvl(origen.getTipoAvl());
    destino.setIdModem(origen.getIdModem());
    destino.setLongitudGrad(origen.getLongitudGrad());
    destino.setLatidudGrad(origen.getLatidudGrad());
    destino.setVelocidad(origen.getVelocidad());
    destino.setNumSat(origen.getNumSat());
    destino.setFechaHoraSat(origen.getFechaHoraSat());
    destino.setTipoEvento(origen.getTipoEvento());
    destino.setVariable1(origen.getVariable1());
    destino.setFechaHoraComputadora(origen.getFechaHoraComputadora());
    destino.setVarControl(origen.getVarControl());

    // Campos adicionales en DescargasAvlD que no están en DescargasAvlO
    // Puedes inicializar `avl` como null o asignar un valor predeterminado
  //  destino.setAvl(null);  // O asigna un objeto `Avl` según tu lógica.

    return destino;
}
}
