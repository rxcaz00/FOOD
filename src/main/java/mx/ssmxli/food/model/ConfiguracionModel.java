package mx.ssmxli.food.model;

import lombok.Data;

@Data
public class ConfiguracionModel {

    private int id;
    private double iva;
    private double retribucion;

    public ConfiguracionModel() {

    }
}
