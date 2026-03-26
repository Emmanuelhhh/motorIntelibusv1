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

import com.tde.motorDBInelibus.persistence.origencard.entity.DescargasValidadorSamO;
import com.tde.motorDBInelibus.persistence.origencard.repository.DescargasValidadorSamRepoO;
import com.tde.motorDBInelibus.persistence.destino.entity.DescargasValidadorSamD;
import com.tde.motorDBInelibus.persistence.destino.repository.DescargasValidadorSamRepoD;

@Service
public class DescargasValidadorSamTransferenciaService {
    private static final int BATCH_SIZE = 500; // ajusta a tu carga/ventana

    @Autowired
    private DescargasValidadorSamRepoO descargasValidadorSamRepoO;

    @Autowired
    private DescargasValidadorSamRepoD descargasValidadorSamRepoD;

    @Transactional
    public void transferirDatos() {
        Long lastId = descargasValidadorSamRepoD.findTopByOrderByIdDgprsDesc()
                .map(DescargasValidadorSamD::getIdDgprs)
                .orElse(0L);
        System.out.println("VALIDADOR SAM - LAST ID DESTINO: " + lastId);
       
        List<DescargasValidadorSamO> origen = leerLoteOrigen(lastId);
        
        System.out.println("VALIDADOR SAM - REGISTROS ENCONTRADOS: " + origen.size());
        
        if (origen.isEmpty()) {
            System.out.println("VALIDADOR SAM - Sin registros nuevos para transferir.");
            System.out.println("VALIDADOR SAM - FIN DEL PROCESO");
            return;
        }
        
        List<DescargasValidadorSamD> destino = new ArrayList<>(origen.size());  
        
        for (DescargasValidadorSamO o : origen) {
            destino.add(convertirADestino(o));
        }
        
        long insertados = 0;
        long duplicados = 0;
        long fallidos = 0;
        
        try {
            descargasValidadorSamRepoD.saveAll(destino);
            insertados = destino.size();
            
           
        } catch (DataIntegrityViolationException bulkEx) {
            System.err.println("VALIDADOR SAM - saveAll falló (posibles duplicados). Fallback a inserción individual. Detalle: "
                    + bulkEx.getMostSpecificCause().getMessage());
            
            List<DescargasValidadorSamO> origenEliminar = new ArrayList<>();
            
            for (DescargasValidadorSamD d : destino) {
                try {
                    descargasValidadorSamRepoD.save(d);
                    insertados++;
                    
                    // Buscar y agregar a la lista de eliminación el registro correspondiente
                    Optional<DescargasValidadorSamO> origenOpt = origen.stream()
                        .filter(o -> o.getId().equals(d.getId()))
                        .findFirst();
                    
                    if (origenOpt.isPresent()) {
                        origenEliminar.add(origenOpt.get());
                    }
                    
                } catch (DataIntegrityViolationException dup) {
                    duplicados++;
                    System.err.println("VALIDADOR SAM - Registro duplicado id=" + d.getId());
                } catch (Exception e) {
                    fallidos++;
                    System.err.println("VALIDADOR SAM - Error insertando id=" + d.getId() + ". Detalle: " + e.getMessage());
                }
            }
            
           
        }
        
        Long maxIdLote = obtenerMaxId(origen).orElse(lastId);

        System.out.println("VALIDADOR SAM - Leidos: " + origen.size()
                + " | Insertados: " + insertados
                + " | Duplicados: " + duplicados
                + " | Fallidos: " + fallidos
                + " | MaxIdLote: " + maxIdLote);
        System.out.println("VALIDADOR SAM - FIN DEL PROCESO");
    }

    private List<DescargasValidadorSamO> leerLoteOrigen(Long lastId) {
        Pageable page = PageRequest.of(0, BATCH_SIZE, Sort.by(Sort.Direction.ASC, "idDgprs"));
        return descargasValidadorSamRepoO.findByIdDgprsGreaterThan(lastId, page);
    }

    private Optional<Long> obtenerMaxId(List<DescargasValidadorSamO> registros) {
        Long max = null;
        for (DescargasValidadorSamO r : registros) {
            if (r.getId() == null) {
                continue;
            }
            if (max == null || r.getId() > max) {
                max = r.getId();
            }
        }
        return Optional.ofNullable(max);
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