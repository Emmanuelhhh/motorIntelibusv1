package com.tde.motorDBInelibus.persistence.destino.entity;


import lombok.Data;

import org.hibernate.annotations.Immutable;



import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;


@Data
@Entity
@Immutable
@Table(name = "tbldescargasavl")
public class DescargasAvlD implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
    private Long id;

    @Column(name = "inttipoavl")
    private Integer tipoAvl;

    @Column(name = "strmodemid")
    private String idModem;

    @Column(name = "flongitud_grad")
    private Float longitudGrad;

    @Column(name = "flatitud_grad")
    private Float latitudGrad;

    @Column(name = "intvelocidad")
    private Integer velocidad;

    @Column(name = "intnum_sat")
    private Integer numSat;

    @Column(name = "dfecha_hora_sat")
    private LocalDateTime fechaHoraSat;

    @Column(name = "inttipo_evento")
    private Integer tipoEvento;

    @Column(name = "intvariable1")
    private Integer variable1;

    @Column(name = "dfechahoracomputadora")
    private LocalDateTime fechaHoraComputadora;

    @Column(name = "intvarcontrol")
    private Integer varControl;

    @Column(name = "intstatus")
    private Integer status;


}