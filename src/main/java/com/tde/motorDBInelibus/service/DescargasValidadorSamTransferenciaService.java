package com.tde.motorDBInelibus.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tde.motorDBInelibus.persistence.origencard.entity.DescargasValidadorSamO;
import com.tde.motorDBInelibus.persistence.origencard.repository.DescargasValidadorSamRepoO;
import com.tde.motorDBInelibus.persistence.destino.entity.DescargasValidadorSamD;
import com.tde.motorDBInelibus.persistence.destino.repository.DescargasValidadorSamRepoD;

@Service
public class DescargasValidadorSamTransferenciaService {

    @Autowired
    private DescargasValidadorSamRepoO descargasValidadorSamRepoO;

    @Autowired
    private DescargasValidadorSamRepoD descargasValidadorSamRepoD;

    @Transactional
    public void transferirDatos(Integer varControl) {
        List<DescargasValidadorSamO> registros = new ArrayList<>();
        descargasValidadorSamRepoO.findTopByVarControl().ifPresent(registros::add);

        System.out.println("REGISTROS ENCONTRADOS EN VALIDADOR SAM: " + registros.size());

        List<DescargasValidadorSamO> registrosFallidos = new ArrayList<>();

        for (DescargasValidadorSamO origen : registros) {
            try {
                DescargasValidadorSamD destino = convertirADestino(origen);
                descargasValidadorSamRepoD.save(destino);
            } catch (Exception e) {
                registrosFallidos.add(origen);
                System.err.println("Error al transferir registro ID: " + origen.getId() + " -> " + e.getMessage());
            }
        }

        registros.removeAll(registrosFallidos);
        descargasValidadorSamRepoO.deleteAll(registros);

        System.out.println("FIN DEL PROCESO DE TRANSFERENCIA DE VALIDADOR SAM");
    }

    private DescargasValidadorSamD convertirADestino(DescargasValidadorSamO origen) {
        DescargasValidadorSamD destino = new DescargasValidadorSamD();

        destino.setIdDgprs(origen.getIdDgprs());
        destino.setCtdEventoValido(origen.getCtdEventoValido());
        destino.setIdRuta(origen.getIdRuta());
        destino.setIdValidador(origen.getIdValidador());
        destino.setFechaEvento(origen.getFechaEvento());
        destino.setTipoTecnologia(origen.getTipoTecnologia());
        destino.setEvento(origen.getEvento());
        destino.setUidTarjeta(origen.getUidTarjeta());
        destino.setTipoTarjeta(origen.getTipoTarjeta());
        destino.setSaldoInicial(origen.getSaldoInicial());
        destino.setCobro(origen.getCobro());
        destino.setSaldoFinal(origen.getSaldoFinal());
        destino.setIdPuntoVentaRec(origen.getIdPuntoVentaRec());
        destino.setFolioVenta(origen.getFolioVenta());
        destino.setIdSamDebita(origen.getIdSamDebita());
        destino.setIdSamRecarga(origen.getIdSamRecarga());
        destino.setIdSamActiva(origen.getIdSamActiva());
        destino.setModemId(origen.getModemId());
        destino.setFechaHoraInsert(origen.getFechaHoraInsert());

        return destino;
    }
}
