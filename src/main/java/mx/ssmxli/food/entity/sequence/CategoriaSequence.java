package mx.ssmxli.food.entity.sequence;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "categoria_sequence")
public class CategoriaSequence {
    @Id
    @GeneratedValue
    @Column(name = "valor")
    private int valor;
    @Column(name = "nombre")
    private String nombre;

    public CategoriaSequence(String nombre) {
        this.nombre = nombre;
    }

    public CategoriaSequence() {
    }
}
