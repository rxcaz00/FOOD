package mx.ssmxli.food.entity.sequence;

import lombok.Data;

import javax.persistence.*;

/**
 * Entidad estilo sequence
 * Utilizada para llevar control de los identificadores de nombres de Alimento
 * */
@Data
@Entity
@Table(name = "nombre_sequence")
public class NombreSequence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "valor")
    private int valor;
    @Column(name = "nombre")
    private String nombre;

    public NombreSequence(String nombre) {
        this.nombre = nombre;
    }

    public NombreSequence() {
    }
}
