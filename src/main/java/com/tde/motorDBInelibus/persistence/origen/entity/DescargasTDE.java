package com.tde.motorDBInelibus.persistence.origen.entity;


import lombok.Data;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;
import org.springframework.data.convert.ThreeTenBackPortConverters.LocalTimeToDateConverter;

import java.io.Serializable;


@Data
@Entity
@Immutable
@Table(name="tbldescargastde")
public class DescargasTDE implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
	
	@Column(name="inttipoframe")
	private Integer idframe;
	
	@Column(name="intsubidas_pta1")
	private Integer subidaspta1;
	
	@Column(name="intbajadas_pta1")
	private Integer bajadaspta1;
	
	@Column(name="intbloqueos_pta1")
	private Integer bloqueospta1;
	
	@Column(name="intpablos_pta1")
	private Integer pablospta1;
	
	@Column(name="intsubidas_pta2")
	private Integer subidaspta2;
	
	@Column(name="intbajadas_pta2")
	private Integer bajadaspta2;
	
	@Column(name="intbloqueos_pta2")
	private Integer bloqueospta2;
	
	@Column(name="intpablos_pta2")
	private Integer pablospta2;
	
	@Column(name="intnumoperador")
	private Integer numOperador;
	
	@Column(name="intbanderaliquidacion")
	private Integer banderaLiquidacion;
	
	@Column(name="intvarcontrol")
	private Integer varControl;
	
	@Column(name="intnumapagados_pta1")
	private Integer numApagadosPta1;
	
	@Column(name="intnumapagados_pta2")
	private Integer numApagadosPta2;
	
	@Column(name="intid_asignacion")
	private Integer idAsignacion;
	
	@Column(name="intstatus")
	private Integer status;
	
	@Column(name="strmodem_id")
	private String id_modem;
	
	@Column(name="dfechahorainsert")
	private LocalDateTime horaInsert;
	


}
