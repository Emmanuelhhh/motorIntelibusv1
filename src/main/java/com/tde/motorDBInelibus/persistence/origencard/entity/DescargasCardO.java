package com.tde.motorDBInelibus.persistence.origencard.entity;


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
@Table(name="tbldescargascard")
public class DescargasCardO implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="id_gprs")
	private Long idGprs;
	@Column(name="strvalidador")
	private String validador;
	@Column(name="stridtarjeta")
	private String  idTarjeta;
	@Column(name="inttipotarjeta")
	private Integer tipoTarjeta;
	@Column(name="inttipoeventotarjeta")
	private Integer tipoEvento;
	@Column(name="intsaldoinicialtarjeta")
	private Integer saldoInicial;
	@Column(name="intsaldofinaltarjeta")
	private Integer saldoFinal;
	@Column(name="dfecheeventotarjeta")
	private LocalDateTime fechaEvento;
	@Column(name="intidpuntoventa")
	private Integer puntoVenta;
	@Column(name="intfolioventa")
	private Integer folioVenta;
	@Column(name="intvarcontrol")
	private Integer varControl;
	@Column(name="intstatus")
	private Integer status;
	@Column(name="dfechaavl")
	private LocalDateTime fechaAvl;
	@Column(name="intnumoperador")
	private Integer numOperador;
	@Column(name="intfoliotarjeta")
	private Integer folio;
	@Column(name="idframeevento")
	private Integer frameEvento;
	@Column(name="intruta")
	private Integer ruta;
	@Column(name="intcontadorciclicotrans")
	private Integer contadorCiclicoTrans;
	@Column(name="intidproducto")
	private Integer idProducto;
	@Column(name="intentidad")
	private Integer entidad;
	@Column(name="stridsam")
	private String idSam;
	@Column(name="intidevento")
	private Integer idEvento;
	@Column(name="struid")
	private String uid;
	@Column(name="strconsecutivosam")
	private String consecutivoSam;
	@Column(name="strmodem_id")
	private String modem;
	@Column(name="dfechahorainsert")
	private LocalDateTime fechaHoraInsert;
	
}
