package com.tde.motorDBInelibus.persistence.origen.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "rxavl")
public class RxAvlO implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "str_id_modem")
    private String idModem;

    @Column(name = "int_tipo_tratamiento_datos")
    private Integer tipoTratamientoDatos;

    @Column(name = "str_dato_rx")
    private String datoRx;

    @Column(name = "int_var_control")
    private Integer varControl;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

   // @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "str_id_modem", referencedColumnName = "serial", insertable = false, updatable = false)
   // private CatAvlD catAvl; // Relación con la tabla `cat_avl`

}
