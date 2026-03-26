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
        Long lastId = descargasAVLCardRepoD.findTopByOrderByIdDesc()
                .map(DescargasAVLCardD::getId)
                .orElse(0L);
        System.out.println("AVL VALIDADOR  - LAST ID DESTINO: " + lastId);

        List<DescargasAvlCardO> origen = leerLoteOrigen(lastId);
        
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
            
            
        } catch (DataIntegrityViolationException bulkEx) {
            System.err.println("AVL VALIDADOR  - saveAll falló (posibles duplicados). Fallback a inserción individual. Detalle: "
                    + bulkEx.getMostSpecificCause().getMessage());
            
            List<DescargasAvlCardO> origenEliminar = new ArrayList<>();
            
            for (DescargasAVLCardD d : destino) {
                try {
                    descargasAVLCardRepoD.save(d);
                    insertados++;
                    
                    // Buscar y agregar a la lista de eliminación el registro correspondiente
                    Optional<DescargasAvlCardO> origenOpt = origen.stream()
                        .filter(o -> o.getId().equals(d.getId()))
                        .findFirst();
                    
                    if (origenOpt.isPresent()) {
                        origenEliminar.add(origenOpt.get());
                    }
                    
                } catch (DataIntegrityViolationException dup) {
                    duplicados++;
                } catch (Exception e) {
                    fallidos++;
                    System.err.println("AVL VALIDADOR  - Error insertando id=" + d.getId() + ". Detalle: " + e.getMessage());
                }
            }
            
            
        }
        
        Long maxIdLote = obtenerMaxId(origen).orElse(lastId);

        System.out.println("AVL VALIDADOR  - Leidos: " + origen.size()
                + " | Insertados: " + insertados
                + " | Duplicados: " + duplicados
                + " | Fallidos: " + fallidos
                + " | MaxIdLote: " + maxIdLote);
        System.out.println("AVL VALIDADOR  - FIN DEL PROCESO");
    }

    private List<DescargasAvlCardO> leerLoteOrigen(Long lastId) {
        Pageable page = PageRequest.of(0, BATCH_SIZE, Sort.by(Sort.Direction.ASC, "id"));
        return descargasAvlCardRepoO.findByIdGreaterThan(lastId , page);
    }

    private Optional<Long> obtenerMaxId(List<DescargasAvlCardO> registros) {
        Long max = null;
        for (DescargasAvlCardO r : registros) {
            if (r.getId() == null) {
                continue;
            }
            if (max == null || r.getId() > max) {
                max = r.getId();
            }
        }
        return Optional.ofNullable(max);
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