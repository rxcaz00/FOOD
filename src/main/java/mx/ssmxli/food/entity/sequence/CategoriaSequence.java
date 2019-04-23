package mx.ssmxli.food.entity.sequence;

import lombok.Data;

import javax.persistence.*;

/**
 * Entidad estilo sequence
 * Utilizada para llevar control de los identificadores de categorias de Alimento
 * */
@Data
@Entity
@Table(name = "categoria_sequence")
public class CategoriaSequence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
