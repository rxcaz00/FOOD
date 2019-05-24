package mx.ssmxli.food.entity.sequence;

import lombok.Data;

import javax.persistence.*;


/**
 * Entidad estilo sequence.
 * Utilizada para llevar control de los identificadores de tamaños de Alimento.
 * */
@Data
@Entity
@Table(name = "tamano_sequence")
public class TamanoSequence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "valor")
    private int valor;
    @Column(name = "nombre", unique = true)
    private String nombre;
    @Column(name = "enabled")
    private boolean enabled;

    public TamanoSequence(String nombre){
        this.nombre = nombre;
    }

    public TamanoSequence(String nombre, boolean enabled) {
        this.nombre = nombre;
        this.enabled = enabled;
    }

    public TamanoSequence() {
    }
}
