package com.tde.motorDBInelibus.persistence.origen.entity;

import lombok.Data;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Immutable
@Table(name = "descargas_odometro")
public class DescargasOdometroO implements Serializable {

    @Id
    @Column(name = "id_dgprs") 
    private Long idDgprs;

    @Column(name = "bintodometro")
    private Long odometro;

    @Column(name = "strmodemid")
    private String modemId;

    @Column(name = "intvarcontrol")
    private Integer varControl;

}