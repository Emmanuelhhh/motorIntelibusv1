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

import com.tde.motorDBInelibus.persistence.destino.entity.DescargasMiniSigoD;
import com.tde.motorDBInelibus.persistence.destino.repository.DescargasMiniSigoRepoD;
import com.tde.motorDBInelibus.persistence.origen.entity.DescargasMiniSigoO;
import com.tde.motorDBInelibus.persistence.origen.repository.DescargasMiniSigoRepoO;

@Service
public class MSTranferenciaService {

    private static final int BATCH_SIZE = 500; // ajusta a tu carga/ventana

    @Autowired
    private DescargasMiniSigoRepoO descargasMiniSigoRepoO;

    @Autowired
    private DescargasMiniSigoRepoD descargasMiniSigoRepoD;

    @Transactional
    public void minisigotransferirDatos(Integer status) {

        Long lastId = descargasMiniSigoRepoD.findTopByOrderByIdDGPRSDesc()
                .map(DescargasMiniSigoD::getIdDGPRS)
                .orElse(0L);

        System.out.println("MS - LAST ID DESTINO: " + lastId);

        // 1) Leer lote incremental (filtrado por status si aplica)
        List<DescargasMiniSigoO> origen = leerLoteOrigen(lastId, status);

        System.out.println("REGISTROS MS ENCONTRADOS  " + origen.size());
        if (origen.isEmpty()) {
            System.out.println("MS - Sin registros nuevos para transferir.");
            System.out.println("FIN DEL PROCESO MS");
            return;
        }

        // 2) Convertir a destino
        List<DescargasMiniSigoD> destino = new ArrayList<>(origen.size());
        for (DescargasMiniSigoO o : origen) {
            destino.add(convertirADestino(o));
        }

        // 3) Guardar por lote
        long insertados = 0;
        long duplicados = 0;
        long fallidos = 0;

        try {
            descargasMiniSigoRepoD.saveAll(destino);
            insertados = destino.size();

        } catch (DataIntegrityViolationException bulkEx) {
            System.err.println("MS - saveAll falló (posibles duplicados). Fallback a inserción individual. Detalle: "
                    + bulkEx.getMostSpecificCause().getMessage());

            for (DescargasMiniSigoD d : destino) {
                try {
                    descargasMiniSigoRepoD.save(d);
                    insertados++;
                } catch (DataIntegrityViolationException dup) {
                    duplicados++; // idempotencia por PK/Unique
                } catch (Exception e) {
                    fallidos++;
                    System.err.println("MS - Error insertando idDGPRS=" + d.getIdDGPRS() + ". Detalle: " + e.getMessage());
                }
            }
        }

        Long maxIdLote = obtenerMaxId(origen).orElse(lastId);

        System.out.println("MS - Leídos: " + origen.size()
                + " | Insertados: " + insertados
                + " | Duplicados: " + duplicados
                + " | Fallidos: " + fallidos
                + " | MaxIdLote: " + maxIdLote);

        System.out.println("FIN DEL PROCESO MS");
    }

    private List<DescargasMiniSigoO> leerLoteOrigen(Long lastId, Integer status) {
        Pageable page = PageRequest.of(0, BATCH_SIZE, Sort.by(Sort.Direction.ASC, "idDGPRS"));

        // Si tu "status" realmente es "valControl" (como en tu convertor original), úsalo aquí:
        // return descargasMiniSigoRepoO.findByValControlAndIdDGPRSGreaterThanOrderByIdDGPRSAsc(status, lastId, page);

        // Si NO quieres filtrar por status, usa esta:
        // return descargasMiniSigoRepoO.findByIdDGPRSGreaterThanOrderByIdDGPRSAsc(lastId, page);

      
        return descargasMiniSigoRepoO.findByIdDGPRSGreaterThanOrderByIdDGPRSAsc(lastId, page);
    }

    private Optional<Long> obtenerMaxId(List<DescargasMiniSigoO> registros) {
        Long max = null;
        for (DescargasMiniSigoO r : registros) {
            if (r.getIdDGPRS() == null) continue;
            if (max == null || r.getIdDGPRS() > max) max = r.getIdDGPRS();
        }
        return Optional.ofNullable(max);
    }

    private DescargasMiniSigoD convertirADestino(DescargasMiniSigoO o) {
        DescargasMiniSigoD d = new DescargasMiniSigoD();

        d.setIdDGPRS(o.getIdDGPRS());
        d.setIdModem(o.getId_modem());
        d.setIdCiudad(o.getIdCiudad());
        d.setIdRuta(o.getIdRuta());
        d.setIdIntelibus(o.getIdIntelibus());
        d.setIdOperador(o.getIdOperador());
        d.setModoOperacion(o.getModoOperacion());

        d.setSubidasBarra1(o.getSubidasBarra1());
        d.setBajadasBarra1(o.getBajadasBarra1());
        d.setBloqueosBarra1(o.getBloqueosBarra1());
        d.setPablosBarra1(o.getPablosBarra1());
        d.setNumApagadosBarra1(o.getNumApagadosBarra1());

        d.setSubidasBarra2(o.getSubidasBarra2());
        d.setBajadasBarra2(o.getBajadasBarra2());
        d.setBloqueosBarra2(o.getBloqueosBarra2());
        d.setPablosBarra2(o.getPablosBarra2());
        d.setNumApagadosBarra2(o.getNumApagadosBarra2());

        d.setSubidasCamDvr1(o.getSubidasCamDvr1());
        d.setBajadasCamDvr1(o.getBajadasCamDvr1());
        d.setSubidasCamDvr2(o.getSubidasCamDvr2());
        d.setBajadasCamDvr2(o.getBajadasCamDvr2());
        d.setSubidasCamDvr3(o.getSubidasCamDvr3());
        d.setBajadasCamDvr3(o.getBajadasCamDvr3());
        d.setSubidasCamDvr4(o.getSubidasCamDvr4());
        d.setBajadasCamDvr4(o.getBajadasCamDvr4());

        d.setValControl(o.getValControl());
        return d;
    }
}
