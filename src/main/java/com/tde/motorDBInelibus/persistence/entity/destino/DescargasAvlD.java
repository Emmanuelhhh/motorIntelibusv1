package com.tde.motorDBInelibus.persistence.entity.destino;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import java.time.LocalDateTime;
import java.io.Serializable;


@Data
@Entity
@Immutable
@Table(name="descargas_avl")
public class  DescargasAvlD implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;


    @Column(name="intTipoAVL")
    private Integer tipoAvl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name="strModem_ID", referencedColumnName = "serial")
     private Avl avl;
    
    @Column(name="strModemID")
    private String idModem;

    @Column(name="fLongitud_grad")
    private Float longitudGrad;

    @Column(name="fLatitud_grad")
    private Float latidudGrad;

    @Column(name="intVelocidad")
    private Integer velocidad;


    @Column(name="intNum_Sat")
    private Integer numSat;

    @Column(name="intHeading")
    private Integer heading;

    @Column(name="dFecha_Hora_SAT")
    private LocalDateTime fechaHoraSat;

    @Column(name="intTipo_Evento")
    private Integer tipoEvento;

    @Column(name="intVariable1")
    private Integer variable1;


    @Column(name="dFechaHoraComputadora")
    private LocalDateTime fechaHoraComputadora;

    @Column(name="intVarControl")
    private Integer varControl;
}
