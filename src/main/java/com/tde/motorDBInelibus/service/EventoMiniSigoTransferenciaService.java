package com.tde.motorDBInelibus.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tde.motorDBInelibus.persistence.origen.entity.EventoMiniSigoO;
import com.tde.motorDBInelibus.persistence.origen.repository.EventoMiniSigoRepoO;
import com.tde.motorDBInelibus.persistence.destino.entity.EventoMiniSigoD;
import com.tde.motorDBInelibus.persistence.destino.repository.EventoMiniSigoRepoD;

@Service
public class EventoMiniSigoTransferenciaService {

    @Autowired
    private EventoMiniSigoRepoO eventoMiniSigoRepoO;

    @Autowired
    private EventoMiniSigoRepoD eventoMiniSigoRepoD;

    @Transactional
    public void transferirDatos(Integer status) {
        // Convertimos el Iterable a una lista para poder manipularlo
        List<EventoMiniSigoO> registros = new ArrayList<>();
        eventoMiniSigoRepoO.findTopByVarControl(status).forEach(registros::add);

        System.out.println("REGISTROS ENCONTRADOS EN EVENTO MINI SIGO: " + registros.size());

        // Lista para almacenar los registros que fallaron
        List<EventoMiniSigoO> registrosFallidos = new ArrayList<>();

        for (EventoMiniSigoO registroO : registros) {
            try {
                EventoMiniSigoD registroD = convertirADestino(registroO);
                eventoMiniSigoRepoD.save(registroD); // Guardar registro individualmente
            } catch (Exception e) {
                registrosFallidos.add(registroO); // Almacenar los fallidos
                System.err.println("Error al transferir registro: " + registroO.getIdModem() + ", " + e.getMessage());
            }
        }

        // Elimina solo los registros que no fallaron
        registros.removeAll(registrosFallidos);
        eventoMiniSigoRepoO.deleteAll(registros);

        System.out.println("FIN DEL PROCESO DE TRANSFERENCIA MINI SIGO");
    }

    private EventoMiniSigoD convertirADestino(EventoMiniSigoO origen) {
        EventoMiniSigoD destino = new EventoMiniSigoD();

        destino.setIdDgprs(origen.getIdDgprs());
        destino.setIdModem(origen.getIdModem());
        destino.setIdMiniSigo(origen.getIdMiniSigo());
        destino.setCiudad(origen.getCiudad());
        destino.setRuta(origen.getRuta());
        destino.setNumOperador(origen.getNumOperador());
        destino.setIdEventoMiniSigo(origen.getIdEventoMiniSigo());
        destino.setVarControl(origen.getVarControl());
        destino.setCreatedAt(origen.getCreatedAt()); // Asegúrate de que ambos tengan este campo.

        return destino;
    }
}
