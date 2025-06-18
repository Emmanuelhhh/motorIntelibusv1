package com.tde.motorDBInelibus.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;

import com.tde.motorDBInelibus.persistence.destino.entity.DescargasMiniSigoD;
import com.tde.motorDBInelibus.persistence.destino.repository.DescargasMiniSigoRepoD;
import com.tde.motorDBInelibus.persistence.origen.repository.DescargasMiniSigoRepoO;
import com.tde.motorDBInelibus.persistence.origen.entity.*;


@Service
public class MSTranferenciaService {

	@Autowired 
	private DescargasMiniSigoRepoO descargasMiniSigoRepoO;
	@Autowired 
	private DescargasMiniSigoRepoD descargasMiniSigoRepoD;
	
	@Transactional
	public void minisigotransferirDatos(Integer status) {
		
	List<DescargasMiniSigoO> registros = new ArrayList<>();
	
	descargasMiniSigoRepoO.findTopByVarControl(status).forEach(registros::add);
	
	System.out.println("REGISTROS MS ENCONTRADOS  " + registros);
	
	List<DescargasMiniSigoO> registrosFallidos = new  ArrayList<>();
	
	for(DescargasMiniSigoO registrosO : registros) {
		
		try {
			DescargasMiniSigoD registroD = convertorADestino(registrosO);
			
			descargasMiniSigoRepoD.save(registroD);
		}catch (Exception e) {
			registrosFallidos.add(registrosO);
			System.err.println("Error al transferir registro: " + registrosO.getId_modem() + ", " + e.getMessage());
			
		}
	}
	
	registros.removeAll(registrosFallidos);
	descargasMiniSigoRepoO.deleteAll(registros);
	 System.out.println("FIN DEL PROCESO MS");
	}
	
	private DescargasMiniSigoD convertorADestino(DescargasMiniSigoO origen) {
		
		DescargasMiniSigoD destino = new DescargasMiniSigoD();
		
		destino.setIdDGPRS(origen.getIdDGPRS());
		destino.setIdModem(origen.getId_modem());
		destino.setIdCiudad(origen.getIdCiudad());
		destino.setIdRuta(origen.getIdRuta());
		destino.setIdIntelibus(origen.getIdIntelibus());
		
		destino.setIdOperador(origen.getIdOperador());
		destino.setModoOperacion(origen.getModoOperacion());
		
		destino.setSubidasBarra1(origen.getSubidasBarra1());
		destino.setBajadasBarra1(origen.getBajadasBarra1());
		destino.setBloqueosBarra1(origen.getBloqueosBarra1());
		destino.setPablosBarra1(origen.getPablosBarra1());
		destino.setNumApagadosBarra1(origen.getNumApagadosBarra1());
		destino.setSubidasBarra2(origen.getSubidasBarra2());
		destino.setBajadasBarra2(origen.getBajadasBarra2());
		destino.setBloqueosBarra2(origen.getBloqueosBarra2());
		destino.setPablosBarra2(origen.getPablosBarra2());
		destino.setNumApagadosBarra2(origen.getNumApagadosBarra2());
		
		destino.setSubidasCamDvr1(origen.getSubidasCamDvr1());
		destino.setBajadasCamDvr1(origen.getBajadasCamDvr1());
		destino.setSubidasCamDvr2(origen.getSubidasCamDvr2());
		destino.setBajadasCamDvr2(origen.getBajadasCamDvr2());
		destino.setSubidasCamDvr3(origen.getSubidasCamDvr3());
		destino.setBajadasCamDvr3(origen.getBajadasCamDvr3());
		destino.setSubidasCamDvr4(origen.getSubidasCamDvr4());
		destino.setBajadasCamDvr4(origen.getBajadasCamDvr4());
		destino.setValControl(origen.getValControl());
		
		return destino;
		
	}
}
