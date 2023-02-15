package org.example.producto.domain.comados;

import org.example.producto.generic.Command;

public class CrearConceptoCommand extends Command {
    private String productoId;
    private String receta;

    public CrearConceptoCommand() {}

    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public String getReceta() {
        return receta;
    }

    public void setReceta(String receta) {
        this.receta = receta;
    }
}
