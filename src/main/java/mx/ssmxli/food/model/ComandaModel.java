package mx.ssmxli.food.model;

import lombok.Data;
import mx.ssmxli.food.entity.Recibo;

import java.util.ArrayList;
import java.util.List;

@Data
public class ComandaModel {

    private int id;
    private int numeroMesa;
    private ReciboModel recibo;

    public ComandaModel(){

    }
}
