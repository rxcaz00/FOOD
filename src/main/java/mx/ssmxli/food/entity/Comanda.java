package mx.ssmxli.food.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "comanda")
public class Comanda {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "numeroMesa", nullable = true)
    private int numeroMesa;//Numero de mesa donde se entregara. Puede pedirse para llevar, asi que puede ser nulo
    @Column(name = "notas")
    private String notas;//Alguna indicacion en la preparación

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "recibo_id", referencedColumnName = "id")
    private Recibo recibo;

    public Comanda(int numeroMesa, String notas) {
        this.numeroMesa = numeroMesa;
        this.notas = notas;
    }

    public Comanda(){}

}
