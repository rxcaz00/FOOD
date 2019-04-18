package mx.ssmxli.food.entity;

import lombok.Data;

import javax.persistence.*;

//La etiqueta "Data" se utiliza para autogenerar los getters y setters de los atributos.
@Data
//La etiqueta "Entity" se utiliza cuando se especifica que la clase es una tabla de la base de datos.
@Entity
//La etiqueta "Table" se utiliza para especificar el nombre de la tabla de la base de datos.
@Table(name = "configuracion")
public class Configuracion {
    //La etiqueta "Id" se utiliza para especificar que dicho atributo será el identificador de la tabla.
    @Id
    //La etiqueta "GeneratedValue" se utiliza cuando un atributo será autoincrementable.
    @GeneratedValue
    //La etiqueta "Column" se utiliza para especificar el nombre de la columna de la tabla a la cual se está haciendo referencia.
    @Column(name = "id")
    private int id;

    //La etiqueta "Column" se utiliza para especificar el nombre de la columna de la tabla a la cual se está haciendo referencia.
    @Column(name = "iva")
    private double iva;

    //La etiqueta "Column" se utiliza para especificar el nombre de la columna de la tabla a la cual se está haciendo referencia.
    @Column(name = "retribucion")
    private double retribucion;


    public Configuracion() {

    }
}
