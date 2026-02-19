package com.tde.motorDBInelibus.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tde.motorDBInelibus.persistence.destino.entity.DescargasCardD;
import com.tde.motorDBInelibus.persistence.destino.repository.DescargasCardRepoD;
import com.tde.motorDBInelibus.persistence.origencard.entity.DescargasCardO;
import com.tde.motorDBInelibus.persistence.origencard.repository.DescargasCardRepoO;

@Service
public class CardTransferenciaService {
	
	@Autowired
	private DescargasCardRepoO cardO;
	
	@Autowired
	private DescargasCardRepoD cardD;
	
	@Transactional
	public void tranferirDatos(Integer status) {
		
	List<DescargasCardO> registros = new ArrayList<>();
	
	cardO.findTopByVarControl(status).forEach(registros::add);
	
	System.out.println("REGISTROS CARD ENCONTRADOS" + registros.size());
	
	 List<DescargasCardO> registrosFallidos = new ArrayList<>();

	 for(DescargasCardO registroO : registros) {
		 
		 
		 try {
			 DescargasCardD registroD = convertirADestino(registroO);
				cardD.save(registroD);
		} catch (Exception e) {
			registrosFallidos.add(registroO);
			System.err.println("Error al transferir registro: " + registroO.getModem());
		}
		 
	 }
	
	registros.removeAll(registrosFallidos);
	cardD.deleteAll();
	 System.out.println("FIN DEL PROCESO CARDS");
	
	}
	
	private DescargasCardD convertirADestino(DescargasCardO origen) {
	    DescargasCardD destino = new DescargasCardD();

	  
	    destino.setId(origen.getIdGprs());
	    destino.setValidador(origen.getValidador());
	    destino.setIdTarjeta(origen.getIdTarjeta());
	    destino.setTipoTarjeta(origen.getTipoTarjeta());
	    destino.setTipoEventoTarjeta(origen.getTipoEvento());
	    destino.setSaldoInicialTarjeta(origen.getSaldoInicial());
	    destino.setSaldoFinalTarjeta(origen.getSaldoFinal());
	    destino.setFechaEventoTarjeta(origen.getFechaEvento());
	    destino.setIdPuntoVenta(origen.getPuntoVenta());
	    destino.setFolioVenta(origen.getFolioVenta());
	    destino.setVarControl(origen.getVarControl());
	    destino.setStatus(origen.getStatus());
	    destino.setFechaAvl(origen.getFechaAvl());
	    destino.setNumOperador(origen.getNumOperador());
	    destino.setFolioTarjeta(origen.getFolio());
	    destino.setFrameEvento(origen.getFrameEvento());
	    destino.setRuta(origen.getRuta());
	    destino.setContadorCiclicoTrans(origen.getContadorCiclicoTrans());
	    destino.setIdProducto(origen.getIdProducto());
	    destino.setEntidad(origen.getEntidad());
	    destino.setIdSam(origen.getIdSam());
	    destino.setIdEvento(origen.getIdEvento());
	    destino.setUid(origen.getUid());
	    destino.setConsecutivoSam(origen.getConsecutivoSam());
	    destino.setModemId(origen.getModem());
	    

	    return destino;
	}
	

	
	
	
}
