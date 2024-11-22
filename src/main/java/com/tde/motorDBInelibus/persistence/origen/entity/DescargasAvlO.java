package com.tde.motorDBInelibus.persistence.origen.entity;
	

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import java.time.LocalDateTime;
import java.io.Serializable;


@Data
@Entity
@Immutable
@Table(name="tbldescargasavl")
public class  DescargasAvlO implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;


    @Column(name="inttipoavl")
    private Integer tipoAvl;

    // @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn( name="strModem_ID", referencedColumnName = "serial")
    //private Avl avl;
    
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
    private Integer tipoEvento;

    @Column(name="intvariable1")
    private Integer variable1;


    @Column(name = "dfechahoracomputadora")
    private LocalDateTime fechaHoraComputadora;

    @Column(name="intvarcontrol")
    private Integer varControl;
}
