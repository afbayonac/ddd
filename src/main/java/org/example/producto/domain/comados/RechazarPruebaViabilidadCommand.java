package org.example.producto.domain.comados;

import org.example.producto.generic.Command;

public class RechazarPruebaViabilidadCommand extends Command {
    private String productoId;
    private String pruebaConceptoId;
    private String pruebaViabilidadId;

    public RechazarPruebaViabilidadCommand() {}

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

    public String getPruebaViabilidadId() {
        return pruebaViabilidadId;
    }

    public void setPruebaViabilidadId(String pruebaViabilidadId) {
        this.pruebaViabilidadId = pruebaViabilidadId;
    }
}
