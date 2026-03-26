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
@Table(name="tbldescargasavl")
public class  DescargasAvlCardO implements Serializable{

   



	@Id
    @Column(name="id")
    private Long id;


    @Column(name="inttipoavl")
    private Integer tipoAvl;
    
    @Column(name="strmodemid")
    private String idModem;

    @Column(name="flongitud_grad")
    private Float longitudGrad;

    @Column(name="flatitud_grad")
    private Float latidudGrad;

    @Column(name="intvelocidad")
    private Integer velocidad;


    @Column(name="intnum_sat")
    private Integer numSat;

 
    @Column(name="dfecha_hora_sat")
    private LocalDateTime fechaHoraSat;

    @Column(name="inttipo_evento")
    private int  tipoEvento;

    @Column(name="intvariable1")
    private Integer variable1;


    @Column(name = "dfechahoracomputadora")
    private LocalDateTime fechaHoraComputadora;

    @Column(name="intvarcontrol")
    private Integer varControl;
}
