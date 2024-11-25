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
@Table(name="descargas_avl")
public class  DescargasAvlD implements Serializable{

    @Id
    @Column(name="id")
    private Long id;


    @Column(name="int_tipo_avl")
    private Integer tipoAvl;

  
   /* @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name="str_id_modem1", referencedColumnName = "serial")
     private Avl avl;*/
    
    @Column(name="str_id_modem")
    private String idModem;

    @Column(name="f_longitud_grad")
    private Float longitudGrad;

    @Column(name="f_latitud_grad")
    private Float latidudGrad;

    @Column(name="int_velocidad")
    private Integer velocidad;


    @Column(name="int_num_sat")
    private Integer numSat;


    @Column(name="d_fecha_hora_sat")
    private LocalDateTime fechaHoraSat;

    @Column(name="int_tipo_evento")
    private Integer tipoEvento;

    @Column(name="int_variable1")
    private Integer variable1;


    @Column(name="d_fecha_hora_computadora")
    private LocalDateTime fechaHoraComputadora;

    @Column(name="int_var_control")
    private Integer varControl;
}
