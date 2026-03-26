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

import com.tde.motorDBInelibus.persistence.destino.entity.DescargasOdometroD;
import com.tde.motorDBInelibus.persistence.destino.repository.DescargasOdometroRepoD;
import com.tde.motorDBInelibus.persistence.origen.entity.DescargasOdometroO;
import com.tde.motorDBInelibus.persistence.origen.repository.DescargasOdometroRepoO;

@Service
public class OdometroTransferenciaService {
    private static final int BATCH_SIZE = 500; // ajusta a tu carga/ventana

    @Autowired
    private DescargasOdometroRepoO odometroORepo;

    @Autowired
    private DescargasOdometroRepoD odometroDRepo;

    @Transactional
    public void transferirDatos() {
        Long lastId = odometroDRepo.findTopByOrderByIdDgprsDesc()
                .map(DescargasOdometroD::getIdDgprs)
                .orElse(0L);
        System.out.println("ODOMETRO - LAST ID DESTINO: " + lastId);

        List<DescargasOdometroO> origen = leerLoteOrigen(lastId);
        
        System.out.println("ODOMETRO - REGISTROS ENCONTRADOS: " + origen.size());
        
        if (origen.isEmpty()) {
            System.out.println("ODOMETRO - Sin registros nuevos para transferir.");
            System.out.println("ODOMETRO - FIN DEL PROCESO");
            return;
        }
        
        List<DescargasOdometroD> destino = new ArrayList<>(origen.size());  
        
        for (DescargasOdometroO o : origen) {
            destino.add(convertirADestino(o));
        }
        
        long insertados = 0;
        long duplicados = 0;
        long fallidos = 0;
        
        try {
            odometroDRepo.saveAll(destino);
            insertados = destino.size();
            
           
        } catch (DataIntegrityViolationException bulkEx) {
            System.err.println("ODOMETRO - saveAll falló (posibles duplicados). Fallback a inserción individual. Detalle: "
                    + bulkEx.getMostSpecificCause().getMessage());
            
            List<DescargasOdometroO> origenEliminar = new ArrayList<>();
            
            for (DescargasOdometroD d : destino) {
                try {
                    odometroDRepo.save(d);
                    insertados++;
                    
                    // Buscar y agregar a la lista de eliminación el registro correspondiente
                    Optional<DescargasOdometroO> origenOpt = origen.stream()
                        .filter(o -> o.getIdDgprs().equals(d.getIdDgprs()))
                        .findFirst();
                    
                    if (origenOpt.isPresent()) {
                        origenEliminar.add(origenOpt.get());
                    }
                    
                } catch (DataIntegrityViolationException dup) {
                    duplicados++;
                    System.err.println("ODOMETRO - Registro duplicado id=" + d.getIdDgprs());
                } catch (Exception e) {
                    fallidos++;
                    System.err.println("ODOMETRO - Error insertando id=" + d.getIdDgprs() + ". Detalle: " + e.getMessage());
                }
            }
            
           
        }
        
        Long maxIdLote = obtenerMaxId(origen).orElse(lastId);

        System.out.println("ODOMETRO - Leídos: " + origen.size()
                + " | Insertados: " + insertados
                + " | Duplicados: " + duplicados
                + " | Fallidos: " + fallidos
                + " | MaxIdLote: " + maxIdLote);
        System.out.println("ODOMETRO - FIN DEL PROCESO");
    }

    private List<DescargasOdometroO> leerLoteOrigen(Long lastId) {
        Pageable page = PageRequest.of(0, BATCH_SIZE, Sort.by(Sort.Direction.ASC, "idDgprs"));
        return odometroORepo.findByIdDgprsGreaterThan(lastId, page);
    }

    private Optional<Long> obtenerMaxId(List<DescargasOdometroO> registros) {
        Long max = null;
        for (DescargasOdometroO r : registros) {
            if (r.getIdDgprs() == null) {
                continue;
            }
            if (max == null || r.getIdDgprs() > max) {
                max = r.getIdDgprs();
            }
        }
        return Optional.ofNullable(max);
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