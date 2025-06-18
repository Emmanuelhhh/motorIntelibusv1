package com.tde.motorDBInelibus.persistence.destino.entity;

import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Immutable
@Table(name = "tbldescargastde_barras")
public class DescargasTDEBarrasD implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_dgprs")
    private Long idDGPRS;

    @Column(name = "inttipoframe")
    private Integer tipoFrame;

    @Column(name = "intsubidas_pta1")
    private Integer subidasPta1;

    @Column(name = "intbajadas_pta1")
    private Integer bajadasPta1;

    @Column(name = "intbloqueos_pta1")
    private Integer bloqueosPta1;

    @Column(name = "intpablos_pta1")
    private Integer pablosPta1;

    @Column(name = "intsubidas_pta2")
    private Integer subidasPta2;

    @Column(name = "intbajadas_pta2")
    private Integer bajadasPta2;

    @Column(name = "intbloqueos_pta2")
    private Integer bloqueosPta2;

    @Column(name = "intpablos_pta2")
    private Integer pablosPta2;

    @Column(name = "intnumoperador")
    private Integer numOperador;

    @Column(name = "intbanderaliquidacion")
    private Integer banderaLiquidacion;

    @Column(name = "intvarcontrol")
    private Integer varControl;

    @Column(name = "intnumApagados_pta1")
    private Integer numApagadosPta1;

    @Column(name = "intnumapagados_pta2")
    private Integer numApagadosPta2;

    @Column(name = "intid_asignacion")
    private Long idAsignacion;

    @Column(name = "intstatus")
    private Integer status;

    @Column(name = "strmodem_id")
    private String modemId;

    @Column(name = "dfehahorainsert")
    private Date fechaHoraInsert;
}
