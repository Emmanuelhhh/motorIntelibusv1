package com.tde.motorDBInelibus.persistence.destino.entity;

import lombok.Data;
import org.hibernate.annotations.Immutable;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Immutable
@Table(name = "tbldescarga_minisigo")
public class DescargasMiniSigoD implements Serializable {

    @Id
    @Column(name = "iddgprs")
    private Long idDGPRS;

    @Column(name = "strmodem_id")
    private String idModem;

   // @Column(name = "intciudad")
   //	 private Integer idCiudad;

    @Column(name = "intruta")
    private Integer idRuta;

    @Column(name = "intidminisigo")
    private Integer idIntelibus;

    @Column(name = "intmodooperacion")
    private Integer idOperador;

    @Column(name = "intsubidas_barra1")
    private Integer subidasBarra1;

    @Column(name = "intbajadas_barra1")
    private Integer bajadasBarra1;

    @Column(name = "intbloqueos_barra1")
    private Integer bloqueosBarra1;

    @Column(name = "intpablos_barra1")
    private Integer pablosBarra1;

    @Column(name = "intnumapagados_barra1")
    private Integer numApagadosBarra1;

    @Column(name = "intsubidas_barra2")
    private Integer subidasBarra2;

    @Column(name = "intbajadas_barra2")
    private Integer bajadasBarra2;

    @Column(name = "intbloqueos_barra2")
    private Integer bloqueosBarra2;

    @Column(name = "intpablos_barra2")
    private Integer pablosBarra2;

    @Column(name = "intnumapagados_barra2")
    private Integer numApagadosBarra2;

    @Column(name = "bintsubidas_bicamdvr1")
    private Long subidasCamDvr1;

    @Column(name = "bintbajadas_bicamdvr1")
    private Long bajadasCamDvr1;

    @Column(name = "bintsubidas_bicamdvr2")
    private Long subidasCamDvr2;

    @Column(name = "bintbajadas_bicamdvr2")
    private Long bajadasCamDvr2;

    @Column(name = "bintsubidas_bicamdvr3")
    private Long subidasCamDvr3;

    @Column(name = "bintbajadas_bicamdvr3")
    private Long bajadasCamDvr3;

    @Column(name = "bintsubidas_bicamdvr4")
    private Long subidasCamDvr4;

    @Column(name = "bintbajadas_bicamdvr4")
    private Long bajadasCamDvr4;

    @Column(name = "intvarcontrol")
    private Integer valControl;
}