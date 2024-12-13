package com.tde.motorDBInelibus.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tde.motorDBInelibus.persistence.origen.repository.TablaOrigenRepo;

@Service
public class TablaOrigenService {

    @Autowired
    private TablaOrigenRepo tablaOrigenRepo;

    public Map<String, String> verificarDatos() {
        Map<String, String> estadoTablas = new HashMap<>();
        
        estadoTablas.put("rxmensaje", tablaOrigenRepo.countRxmensaje() > 0 ? "Sí" : "No");
        //estadoTablas.put("rxavl", tablaOrigenRepo.countRxavl() > 0 ? "Sí" : "No");
        //estadoTablas.put("evento_mini_sigo", tablaOrigenRepo.countEventoMiniSigo() > 0 ? "Sí" : "No");
        
        return estadoTablas;
    }
}
