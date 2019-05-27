package mx.ssmxli.food.entity;

import lombok.Data;

import javax.persistence.*;

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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "recibo_id", referencedColumnName = "id")
    private Recibo recibo;

    public Comanda(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public Comanda(){}

}
