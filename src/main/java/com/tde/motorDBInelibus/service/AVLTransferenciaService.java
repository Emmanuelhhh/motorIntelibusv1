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

import com.tde.motorDBInelibus.persistence.origen.entity.DescargasAvlO;
import com.tde.motorDBInelibus.persistence.origen.repository.DescargasAvlRepoO;
import com.tde.motorDBInelibus.persistence.destino.entity.DescargasAvlD;
import com.tde.motorDBInelibus.persistence.destino.repository.DescargasAvlRepoD;



@Service
public class AVLTransferenciaService {
    private static final int BATCH_SIZE = 500; // ajusta a tu carga/ventana


    @Autowired
    private DescargasAvlRepoO descargasAvlRepoO;

    @Autowired
    private DescargasAvlRepoD descargasAvlRepoD;

    @Transactional
    public void transferirDatos() {
    	
    	  Long lastId = descargasAvlRepoD.findTopByOrderByIdDesc()
                  .map(DescargasAvlD::getId)
                  .orElse(0L);
    	  System.out.println("INTELIBUS AVL - LAST ID DESTINO: " + lastId);

          List<DescargasAvlO> origen = leerLoteOrigen(lastId);
    	  
          System.out.println("REGISTROS INTELIBUS AVL ENCONTRADOS " + origen.size());
          
          if (origen.isEmpty()) {
              System.out.println("INTELIBUS AVL - Sin registros nuevos para transferir.");
              System.out.println("INTELIBUS FIN DEL PROCESO AVL");
              return;
          }
          List<DescargasAvlD> destino = new ArrayList<>(origen.size());  
          
          for (DescargasAvlO o : origen) {
              destino.add(convertirADestino(o));
          }
          long insertados = 0;
          long duplicados = 0;
          long fallidos = 0;
          
          try {
              descargasAvlRepoD.saveAll(destino);
              insertados = destino.size();
          } catch (DataIntegrityViolationException bulkEx) {
              System.err.println("AVL - saveAll falló (posibles duplicados). Fallback a inserción individual. Detalle: "
                      + bulkEx.getMostSpecificCause().getMessage());
              for (DescargasAvlD d : destino) {
                  try {
                      descargasAvlRepoD.save(d);
                      insertados++;
                  } catch (DataIntegrityViolationException dup) {
                      duplicados++;
                  } catch (Exception e) {
                      fallidos++;
                      System.err.println("AVL - Error insertando id=" + d.getId() + ". Detalle: " + e.getMessage());
                  }
              }
          }
          
          
          Long maxIdLote = obtenerMaxId(origen).orElse(lastId);

          System.out.println("AVL - Leídos: " + origen.size()
                  + " | Insertados: " + insertados
                  + " | Duplicados: " + duplicados
                  + " | Fallidos: " + fallidos
                  + " | MaxIdLote: " + maxIdLote);
          System.out.println("FIN DEL PROCESO AVL");
    }

    private List<DescargasAvlO> leerLoteOrigen(Long lastId) {
    	Pageable page = PageRequest.of(0, BATCH_SIZE, Sort.by(Sort.Direction.ASC, "id"));

        return descargasAvlRepoO.findByIdGreaterThan(lastId, page);
    }

    private Optional<Long> obtenerMaxId(List<DescargasAvlO> registros) {
        Long max = null;
        for (DescargasAvlO r : registros) {
            if (r.getId() == null) {
                continue;
            }
            if (max == null || r.getId() > max) {
                max = r.getId();
            }
        }
        return Optional.ofNullable(max);
    }

    private DescargasAvlD convertirADestino(DescargasAvlO origen) {
        DescargasAvlD destino = new DescargasAvlD();

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