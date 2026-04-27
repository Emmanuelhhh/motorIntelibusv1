package com.tde.motorDBInelibus.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tde.motorDBInelibus.persistence.destino.entity.DescargasAVLCardD;
import com.tde.motorDBInelibus.persistence.destino.repository.DescargasAVLCardRepoD;
import com.tde.motorDBInelibus.persistence.origencard.entity.DescargasAvlCardO;
import com.tde.motorDBInelibus.persistence.origencard.repository.DescargasAvlCardRepoO;

@Service
public class AVLCardTransferenciaService {
    private static final int BATCH_SIZE = 500; // ajusta a tu carga/ventana

    @Autowired
    private DescargasAvlCardRepoO descargasAvlCardRepoO;

    @Autowired
    private DescargasAVLCardRepoD descargasAVLCardRepoD;

    @Transactional
    public void transferirDatos() {
       
        

        List<DescargasAvlCardO> origen = leerLoteOrigen();
        
        System.out.println("AVL VALIDADOR VALIDADOR REGISTROS ENCONTRADOS: " + origen.size());
        
        if (origen.isEmpty()) {
            System.out.println("AVL VALIDADOR  - Sin registros nuevos para transferir.");
            System.out.println("AVL VALIDADOR  - FIN DEL PROCESO");
            return;
        }
        
        List<DescargasAVLCardD> destino = new ArrayList<>(origen.size());  
        
        for (DescargasAvlCardO o : origen) {
            destino.add(convertirADestino(o));
        }
        
        long insertados = 0;
        long duplicados = 0;
        long fallidos = 0;
        
        try {
            descargasAVLCardRepoD.saveAll(destino);
            insertados = destino.size();
            origen.forEach(r -> r.setVarControl(0));
            descargasAvlCardRepoO.saveAll(origen);
            
            
            
        } catch (DataIntegrityViolationException bulkEx) {
            System.err.println("AVL VALIDADOR  - saveAll falló (posibles duplicados). Fallback a inserción individual. Detalle: "
                    + bulkEx.getMostSpecificCause().getMessage());
            
            List<DescargasAvlCardO> procesados = new ArrayList<>();
            
            for (DescargasAVLCardD d : destino) {
                try {
                    descargasAVLCardRepoD.save(d);
                    insertados++;

                    origen.stream()
                        .filter(o -> o.getId().equals(d.getId()))
                        .findFirst()
                        .ifPresent(procesados::add);

                } catch (DataIntegrityViolationException dup) {
                    duplicados++;
                } catch (Exception e) {
                    fallidos++;
                }
            }

            procesados.forEach(r -> r.setVarControl(0));
            descargasAvlCardRepoO.saveAll(procesados);
        }
        
        

        System.out.println("AVL VALIDADOR  - Leidos: " + origen.size()
                + " | Insertados: " + insertados
                + " | Duplicados: " + duplicados
                + " | Fallidos: " + fallidos);
        System.out.println("AVL VALIDADOR  - FIN DEL PROCESO");
    }

    private List<DescargasAvlCardO> leerLoteOrigen() {
      
    	Pageable pageable = PageRequest.of(0, 1, Sort.by("id").ascending());

    	
        return descargasAvlCardRepoO.findByVarControl(8, pageable);
    }



    private DescargasAVLCardD convertirADestino(DescargasAvlCardO origen) {
        DescargasAVLCardD destino = new DescargasAVLCardD();

        destino.setId(origen.getId());
        destino.setInttipoavl(origen.getTipoAvl());
        destino.setStrmodemid(origen.getIdModem());
        destino.setFlongitud_grad(origen.getLongitudGrad());
        destino.setFlatitud_grad(origen.getLatidudGrad());
        destino.setIntvelocidad(origen.getVelocidad());
        destino.setIntnum_sat(origen.getNumSat());
        destino.setDfecha_hora_sat(origen.getFechaHoraSat());
        destino.setInttipo_evento(origen.getTipoEvento());
        destino.setIntvariable1(origen.getVariable1());
        destino.setDfechahoracomputadora(origen.getFechaHoraComputadora());
        destino.setIntvarcontrol(origen.getVarControl());

        return destino;
    }
}