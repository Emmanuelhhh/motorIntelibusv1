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
@Table(name="descarga_mini_sigo")
public class DescargasMiniSigoD  implements Serializable {



    @Id
    @Column(name="id_dgprs")
    private Long idDGPRS;

    @Column(name="str_id_modem")
    private String id_modem;

    @Column(name="int_ciudad")
    private Integer idCiudad;

    @Column(name="int_ruta")
    private Integer idRuta;

    @Column(name="int_id_minisigo")
    private Integer idIntelibus;

    @Column(name="int_num_operdor")
    private Integer idOperador;

    //@Column(name="intModoOperacion")
    //private Integer modoOperacion;

    @Column(name="int_subidas_barra1")
    private Integer subidasBarra1;

    @Column(name="int_bajadas_barra1")
    private Integer bajadasBarra1;

    @Column(name="int_bloqueos_barra1")
    private Integer BloqueosBarra1;

    @Column(name="int_pablos_barra1")
    private Integer pablosBarra1;

    @Column(name="int_num_apagados_barra1")
    private Integer numApagadosBarra1;

    @Column(name="int_subidas_barra2")
    private Integer subidasBarra2;

    @Column(name="int_bajadas_barra2")
    private Integer bajadasBarra2;

    @Column(name="int_bloqueos_barra2")
    private Integer bloqueosBarra2;

    @Column(name="int_pablos_barra2")
    private Integer pablosBarra2;

    @Column(name="int_num_apagados_barra2")
    private Integer numApagadosBarra2;

    @Column(name="bint_subidas_bicam_dvr1")
    private Long subidasCamDvr1;

    @Column(name="bint_bajadas_bicam_dvr1")
    private Long bajadasCamDvr1;

    @Column(name="bint_subidas_bicam_dvr2")
    private Long subidasCamDvr2;

    @Column(name="bint_bajadas_bicam_dvr2")
    private Long bajadasCamDvr2;

    @Column(name="bint_subidas_bicam_dvr3")
    private Long subidasCamDvr3;

    @Column(name="bint_bajadas_bicam_dvr3")
    private Long bajadasCamDvr3;

    @Column(name="bint_subidas_bicam_dvr4")
    private Long subidasCamDvr4;

    @Column(name="bint_bajadas_bicam_dvr4")
    private Long bajadasCamDvr4;

    @Column(name="int_var_control")
    private Integer valControl;

   /* @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name="strModem_ID", referencedColumnName = "serial")
    private Avl avl;
*/

}
