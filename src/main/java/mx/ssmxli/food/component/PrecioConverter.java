package mx.ssmxli.food.component;

import mx.ssmxli.food.entity.Precio;
import mx.ssmxli.food.model.PrecioModel;
import org.springframework.stereotype.Component;

    @Component("precioConverter")
    public class PrecioConverter {
        public Precio convertToPrecioModel2Precio(PrecioModel precioModel){
            Precio precio = new Precio();
            precio.setTamano(precioModel.getTamano());
            precio.setPrecio(precioModel.getPrecio());
            precio.setId(precioModel.getId());
            return precio;
        }

        public PrecioModel convertPrecio2PrecioModel(Precio precio){
            PrecioModel precioModel = new PrecioModel();
            precioModel.setTamano(precio.getTamano());
            precioModel.setPrecio(precio.getPrecio());
            precioModel.setPrecio(precio.getId());
            return precioModel;
        }
}
