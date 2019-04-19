package mx.ssmxli.food.entity.sequence;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tamano_sequence")
public class TamanoSequence {
    @Id
    @GeneratedValue
    @Column(name = "valor")
    private int valor;
    @Column(name = "nombre")
    private String nombre;

    public TamanoSequence(String nombre) {
        this.nombre = nombre;
    }

    public TamanoSequence() {
    }
}
