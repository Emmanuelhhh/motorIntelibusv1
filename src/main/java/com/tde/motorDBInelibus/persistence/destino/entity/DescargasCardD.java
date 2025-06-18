package com.tde.motorDBInelibus.persistence.destino.entity;

import lombok.Data;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
	
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;


import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Immutable
@Table(name = "tbldescargascard")
public class DescargasCardD implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "id_dgprs")
    private Long idDgprs;

    @Column(name = "strvalidador")
    private String validador;

    @Column(name = "stridtarjeta")
    private String idTarjeta;

    @Column(name = "inttipotarjeta")
    private Integer tipoTarjeta;

    @Column(name = "inttipoeventotarjeta")
    private Integer tipoEventoTarjeta;

    @Column(name = "intsaldoinicialtarjeta")
    private Integer saldoInicialTarjeta;

    @Column(name = "intsaldofinaltarjeta")
    private Integer saldoFinalTarjeta;

    @Column(name = "dfechaeventotarjeta")
    private LocalDateTime fechaEventoTarjeta;

    @Column(name = "intidpuntoventa")
    private Integer idPuntoVenta;

    @Column(name = "intfolioventa")
    private Integer folioVenta;

    @Column(name = "intvarcontrol")
    private Integer varControl;

    @Column(name = "intstatus")
    private Integer status;

    @Column(name = "dfechaavl")
    private LocalDateTime fechaAvl;

    @Column(name = "intnumoperador")
    private Integer numOperador;

    @Column(name = "intfoliotarjeta")
    private Integer folioTarjeta;

    @Column(name = "idframeevento")
    private Integer frameEvento;

    @Column(name = "intruta")
    private Integer ruta;

    @Column(name = "intcontadorciclicotrans")
    private Integer contadorCiclicoTrans;

    @Column(name = "intidproducto")
    private Integer idProducto;

    @Column(name = "intentidad")
    private Integer entidad;

    @Column(name = "stridsam")
    private String idSam;

    @Column(name = "intidevento")
    private Integer idEvento;

    @Column(name = "struid")
    private String uid;

    @Column(name = "strconsecutivosam")
    private String consecutivoSam;

    @Column(name = "strmodem_id")
    private String modemId;

    @Column(name = "dfechahorainsert")
    private Date fechaHoraInsert;
}