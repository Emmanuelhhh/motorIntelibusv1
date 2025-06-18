package com.tde.motorDBInelibus.service;

import com.tde.motorDBInelibus.persistence.destino.entity.DescargasTDEBarrasD;
import com.tde.motorDBInelibus.persistence.destino.repository.DescargasTDEBarrasRepoD;
import com.tde.motorDBInelibus.persistence.origen.entity.DescargasTDEO;
import com.tde.motorDBInelibus.persistence.origen.repository.DescargasTdeRepoO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BarrasTranferenciaService {

    @Autowired
    private DescargasTdeRepoO descargasTdeRepoO;

    @Autowired
    private DescargasTDEBarrasRepoD descargasTDEBarrasRepoD;

    @Transactional
    public void transferirDatos(Integer status) {

        List<DescargasTDEO> registros = new ArrayList<>();
        descargasTdeRepoO.findTopByVarControl(status).forEach(registros::add);

        System.out.println("REGISTROS TDE ENCONTRADOS: " + registros.size());

        List<DescargasTDEO> registrosFallidos = new ArrayList<>();

        for (DescargasTDEO origen : registros) {
            try {
                DescargasTDEBarrasD destino = convertirADestino(origen);
                descargasTDEBarrasRepoD.save(destino);
            } catch (Exception e) {
                registrosFallidos.add(origen);
                System.err.println("Error al transferir registro: " + origen.getId_modem() + ", " + e.getMessage());
            }
        }

        registros.removeAll(registrosFallidos);
        descargasTdeRepoO.deleteAll(registros);

        System.out.println("FIN DEL PROCESO BARRAS DescargasTDE");
    }

    private DescargasTDEBarrasD convertirADestino(DescargasTDEO origen) {
        DescargasTDEBarrasD destino = new DescargasTDEBarrasD();

        destino.setIdDGPRS(origen.getId());
        destino.setTipoFrame(origen.getIdframe());
        destino.setSubidasPta1(origen.getSubidaspta1());
        destino.setBajadasPta1(origen.getBajadaspta1());
        destino.setBloqueosPta1(origen.getBloqueospta1());
        destino.setPablosPta1(origen.getPablospta1());
        destino.setSubidasPta2(origen.getSubidaspta2());
        destino.setBajadasPta2(origen.getBajadaspta2());
        destino.setBloqueosPta2(origen.getBloqueospta2());
        destino.setPablosPta2(origen.getPablospta2());
        destino.setNumOperador(origen.getNumOperador());
        destino.setBanderaLiquidacion(origen.getBanderaLiquidacion());
        destino.setVarControl(origen.getVarControl());
        destino.setNumApagadosPta1(origen.getNumApagadosPta1());
        destino.setNumApagadosPta2(origen.getNumApagadosPta2());
        destino.setIdAsignacion(Long.valueOf(origen.getIdAsignacion()));
        destino.setStatus(origen.getStatus());
        destino.setModemId(origen.getId_modem());
        destino.setFechaHoraInsert(new Date());

        return destino;
    }
}
