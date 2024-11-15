package com.tde.motorDBInelibus.persistence.entity.destino;




import lombok.Data;
import org.hibernate.annotations.Immutable;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@Entity
@Immutable
@Table(name="cat_avl")
public class Avl implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", length = 30)
    private String type;

    @Column(name = "subtype", length = 30)
    private String subtype;

    @Column(name = "treatment", length = 30)
    private String treatment;

    @Column(name = "application", length = 40)
    private String application;

    @Column(name = "serial", length = 40)
    private String serial;

    @Column(name = "id_usr", nullable = false)
    private Long idUsr;

    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(4) DEFAULT 1")
    private Integer status;

    @Column(name = "assigned", nullable = false, columnDefinition = "TINYINT(4) DEFAULT 0")
    private Integer assigned;

    @Column(name = "id_dist", nullable = false, columnDefinition = "bigint(20) DEFAULT 0")
    private Long idDist;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private java.sql.Timestamp createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT NULL")
    private java.sql.Timestamp updatedAt;

    @Column(name = "downdated_at", columnDefinition = "TIMESTAMP DEFAULT NULL")
    private java.sql.Timestamp downdatedAt;


}
