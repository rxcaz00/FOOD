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

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "numeroMesa")
    private int numeroMesa;

    @Column(name = "notas")
    private String notas;

    public Comanda(){

    }

}
