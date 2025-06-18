package com.tde.motorDBInelibus.persistence.destino.entity;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Immutable
@Table(name = "tbldescargasavl_card")
public class DescargasAVLCardD implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "inttipoavl")
    private Integer inttipoavl;

    @Column(name = "strmodemid")
    private String strmodemid;

    @Column(name = "flongitud_grad")
    private Float flongitud_grad;

    @Column(name = "flatitud_grad")
    private Float flatitud_grad;

    @Column(name = "intvelocidad")
    private Integer intvelocidad;

    @Column(name = "intnum_sat")
    private Integer intnum_sat;

    @Column(name = "dfecha_hora_sat")
    private LocalDateTime dfecha_hora_sat;

    @Column(name = "inttipo_evento")
    private Integer inttipo_evento;

    @Column(name = "intvariable1")
    private Integer intvariable1;

    @Column(name = "dfechahoracomputadora")
    private LocalDateTime dfechahoracomputadora;

    @Column(name = "intvarcontrol")
    private Integer intvarcontrol;

    @Column(name = "intstatus")
    private Integer intstatus;

    @Column(name = "id_uni")
    private Long id_uni;

}
