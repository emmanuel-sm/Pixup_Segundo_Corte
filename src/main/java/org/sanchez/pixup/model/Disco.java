package org.sanchez.pixup.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "TBL_DISCO")
public class Disco extends Catalogo {

    @Column(name = "TITULO", nullable = false)
    private String titulo;

    @Column(name = "PRECIO", nullable = false)
    private Float precio;

    @Column(name = "EXISTENCIA", nullable = false)
    private Integer existencia;

    @Column(name = "DESCUENTO")
    private Float descuento;

    @Column(name = "FECHA_LANZAMIENTO")
    private LocalDate fechaLanzamiento;

    @Column(name = "IMAGEN")
    private String imagen;

    @Column(name = "TBL_ARTISTA_ID", nullable = false)
    private Integer artistaId;

    @Column(name = "TBL_DISQUERA_ID", nullable = false)
    private Integer disqueraId;

    @Column(name="TBL_GENERO_ID", nullable = false)
    private Integer generoId;
}
