package com.tde.motorDBInelibus.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tde.motorDBInelibus.persistence.destino.entity.DescargasAVLCardD;
import com.tde.motorDBInelibus.persistence.destino.repository.DescargasAVLCardRepoD;

import com.tde.motorDBInelibus.persistence.origencard.repository.DescargasAvlCardRepoO;
import com.tde.motorDBInelibus.persistence.origencard.entity.*;
@Service
public class AVLCardTransferenciaService {

	@Autowired
	private DescargasAvlCardRepoO descargasAvlCardRepoO;
	
	@Autowired
	private DescargasAVLCardRepoD descargasAVLCardRepoD;
	
	@Transactional
	public void transferirDatos(Integer intValControl) {
	List<DescargasAvlCardO> registros = new  ArrayList<>(); 
	descargasAvlCardRepoO.findTopByVarControl().forEach(registros::add);
    System.out.println("REGISTROS AVL BARRAS DESCARGAS AVL TARJETAS  " + registros);

    // Lista para almacenar los registros que fallaron
    List<DescargasAvlCardO> registrosFallidos = new ArrayList<>();
    
    for (DescargasAvlCardO registroO : registros) {
        try {
        	DescargasAVLCardD registroD = convertirADestino(registroO);
            descargasAVLCardRepoD.save(registroD); // Guardar registro individualmente
        } catch (Exception e) {
            registrosFallidos.add(registroO); // Almacenar los fallidos
            System.err.println("Error al transferir registro: " + registroO.getIdModem() + ", " + e.getMessage());
        }
    }
    
 // Elimina solo los registros que no fallaron
    registros.removeAll(registrosFallidos);
    descargasAvlCardRepoO.deleteAll(registros);

    System.out.println("FIN DEL PROCESO");
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

    // Campos adicionales en DescargasAvlD que no están en DescargasAvlO
    // Puedes inicializar `avl` como null o asignar un valor predeterminado
  //  destino.setAvl(null);  // O asigna un objeto `Avl` según tu lógica.

    return destino;
}
	
}
