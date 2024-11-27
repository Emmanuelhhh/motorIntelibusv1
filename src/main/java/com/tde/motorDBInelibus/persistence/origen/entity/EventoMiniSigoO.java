package com.tde.motorDBInelibus.persistence.origen.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "evento_mini_sigo")
public class EventoMiniSigoO implements Serializable {

    @Id
    @Column(name = "id_dgprs", nullable = false)
    private Long idDgprs;

    @Column(name = "str_id_modem")
    private String idModem;

    @Column(name = "int_id_mini_sigo")
    private Integer idMiniSigo;

    @Column(name = "int_ciudad")
    private Integer ciudad;

    @Column(name = "int_ruta")
    private Integer ruta;

    @Column(name = "int_num_operador")
    private Integer numOperador;

    @Column(name = "int_id_evento_mini_sigo")
    private Integer idEventoMiniSigo;

    @Column(name = "int_var_control")
    private Integer varControl;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "str_id_modem", referencedColumnName = "serial", insertable = false, updatable = false)
    //private CatAvlD catAvl; // Relación con la tabla `cat_avl`

}
