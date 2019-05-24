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
    @Column(name = "nombre", unique = true)
    private String nombre;
    @Column(name = "enabled")
    private boolean enabled;

    public CategoriaSequence(String nombre){
        this.nombre = nombre;
    }

    public CategoriaSequence(String nombre, boolean enabled) {
        this.enabled = enabled;
        this.nombre = nombre;
    }

    public CategoriaSequence() {
    }
}
