package org.example.producto.domain.comados;

import org.example.producto.generic.Command;

public class AprobarConceptoCommand extends Command {
    private String productoId;
    private String pruebaConceptoId;

    public AprobarConceptoCommand() {}

    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public String getPruebaConceptoId() {
        return pruebaConceptoId;
    }

    public void setPruebaConceptoId(String pruebaConceptoId) {
        this.pruebaConceptoId = pruebaConceptoId;
    }
}
