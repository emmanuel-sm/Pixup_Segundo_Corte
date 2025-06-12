package org.sanchez.pixup.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "TBL_CANCION")
public class Cancion extends Catalogo {
    @Column( name ="TITULO" , nullable = false )
    private String titulo;

    @Column (name="DURACION", nullable = false)
    private Integer duracion;

    @Column (name = "TBL_DISCO_ID", nullable = false)
    private Integer discoId;
}
