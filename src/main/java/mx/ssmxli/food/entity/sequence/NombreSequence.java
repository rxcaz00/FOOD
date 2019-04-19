package mx.ssmxli.food.entity.sequence;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "nombre_sequence")
public class NombreSequence {
    @Id
    @GeneratedValue
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
