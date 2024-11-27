package com.tde.motorDBInelibus.persistence.destino.entity;




import lombok.Data;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;


import java.io.Serializable;



@Data
@Entity
@Immutable
@Table(name="descargas_card")
public class DescargasCardD  implements Serializable{

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="id_dgprs")
	private Long idGprs;
	@Column(name="str_validador")
	private String validador;
	@Column(name="str_id_tarjeta")
	private String  idTarjeta;
	@Column(name="int_tipo_tarjeta")
	private Integer tipoTarjeta;
	@Column(name="int_tipo_evento_tarjeta")
	private Integer tipoEvento;
	@Column(name="int_saldo_inicial_tarjeta")
	private Integer saldoInicial;
	@Column(name="int_saldo_final_tarjeta")
	private Integer saldoFinal;
	@Column(name="d_fecha_evento_tarjeta")
	private LocalDateTime fechaEvento;
	@Column(name="int_id_punto_venta")
	private Integer puntoVenta;
	@Column(name="int_folio_venta")
	private Integer folioVenta;
	@Column(name="int_var_control")
	private Integer varControl;
	@Column(name="int_status")
	private Integer status;
	@Column(name="d_fecha_avl")
	private LocalDateTime fechaAvl;
	@Column(name="int_num_operador")
	private Integer numOperador;
	@Column(name="int_folio_tarjeta")
	private Integer folio;
	@Column(name="id_frame_evento")
	private Integer frameEvento;
	@Column(name="int_ruta")
	private Integer ruta;
	@Column(name="int_contador_ciclico_trans")
	private Integer contadorCiclicoTrans;
	@Column(name="int_id_producto")
	private Integer idProducto;
	@Column(name="int_entidad")
	private Integer entidad;
	@Column(name="str_id_sam")
	private String idSam;
	@Column(name="int_id_evento")
	private Integer idEvento;
	
	
	@Column(name="str_uid")
	private String uid;
	@Column(name="str_consecutivo_sam")
	private String consecutivoSam;
	@Column(name="str_id_modem")
	private String modem;
	
	
	
}
