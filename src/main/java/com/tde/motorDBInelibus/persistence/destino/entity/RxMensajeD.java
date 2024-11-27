package com.tde.motorDBInelibus.persistence.destino.entity;

import lombok.Data;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Immutable
@Table(name = "rxmensaje")
public class RxMensajeD implements Serializable {

    @Id
    @Column(name = "id_dgprs") 
    private Long idDgprs;

    @Column(name = "str_id_modem")
    private String idModem;

    @Column(name = "int_tipo_tratamiento_datos")
    private Integer tipoTratamientoDatos;

    @Column(name = "str_dato_rx")
    private String datoRx;

    @Column(name = "int_var_control")
    private Integer varControl;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

}