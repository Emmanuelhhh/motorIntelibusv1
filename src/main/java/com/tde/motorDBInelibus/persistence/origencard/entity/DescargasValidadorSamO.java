package com.tde.motorDBInelibus.persistence.origencard.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.Data;

@Data
@Entity
@Immutable
@Table(name = "tblDescargasValidadorSAM")
public class DescargasValidadorSamO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "id_DGPRS")
    private Long idDgprs;

    @Column(name = "int_ctdEventovalido")
    private Integer ctdEventoValido;

    @Column(name = "int_IDRuta")
    private Integer idRuta;

    @Column(name = "vc_IdValidador")
    private String idValidador;

    @Column(name = "dt_Fecha_Evento")
    private LocalDateTime fechaEvento;

    @Column(name = "int_TipoTecnologia")
    private Integer tipoTecnologia;

    @Column(name = "int_Evento")
    private Short evento;

    @Column(name = "str_UIDTarjeta")
    private String uidTarjeta;

    @Column(name = "int_TipoTarjeta")
    private Short tipoTarjeta;

    @Column(name = "dec_SaldoInicial")
    private BigDecimal saldoInicial;

    @Column(name = "dec_Cobro")
    private BigDecimal cobro;

    @Column(name = "dec_SaldoFinal")
    private BigDecimal saldoFinal;

    @Column(name = "int_IdPuntoVentaRec")
    private Integer idPuntoVentaRec;

    @Column(name = "int_FolioVenta")
    private Long folioVenta;

    @Column(name = "str_IDSAMDebita")
    private String idSamDebita;

    @Column(name = "str_IDSAMRecarga")
    private String idSamRecarga;

    @Column(name = "str_IDSAMActiva")
    private String idSamActiva;

    @Column(name = "str_ModemID")
    private String modemId;

    @Column(name = "dt_FechaHoraInsert")
    private LocalDateTime fechaHoraInsert;
}
