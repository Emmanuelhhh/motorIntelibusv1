
package com.tde.motorDBInelibus.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tde.motorDBInelibus.persistence.destino.entity.DescargasOdometroD;
import com.tde.motorDBInelibus.persistence.destino.repository.DescargasOdometroRepoD;

import com.tde.motorDBInelibus.persistence.origen.entity.DescargasOdometroO;
import com.tde.motorDBInelibus.persistence.origen.repository.DescargasOdometroRepoO;

@Service
public class OdometroTransferenciaService {

    @Autowired
    private DescargasOdometroRepoO odometroORepo;

    @Autowired
    private DescargasOdometroRepoD odometroDRepo;

    @Transactional
    public void transferirDatos(Integer status) {

        List<DescargasOdometroO> registros = new ArrayList<>();

        odometroORepo.findTop().forEach(registros::add);

        System.out.println("REGISTROS ODOMETRO ENCONTRADOS: " + registros.size());

        List<DescargasOdometroO> registrosFallidos = new ArrayList<>();

        for (DescargasOdometroO registroO : registros) {
            try {
                DescargasOdometroD registroD = convertirADestino(registroO);
                odometroDRepo.save(registroD);
            } catch (Exception e) {
                registrosFallidos.add(registroO);
                System.err.println("Error al transferir registro: " + registroO.getModemId());
            }
        }

        registros.removeAll(registrosFallidos);
        odometroORepo.deleteAll(registros);

        System.out.println("FIN DEL PROCESO ODOMETROS");
    }

    private DescargasOdometroD convertirADestino(DescargasOdometroO origen) {
        DescargasOdometroD destino = new DescargasOdometroD();

        destino.setIdDgprs(origen.getIdDgprs());
        destino.setOdometro(origen.getOdometro());
        destino.setModemId(origen.getModemId());
        destino.setVarControl(origen.getVarControl());

        return destino;
    }
}
