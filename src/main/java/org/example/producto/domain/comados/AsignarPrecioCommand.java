package org.example.producto.domain.comados;

import org.example.producto.generic.Command;

public class AsignarPrecioCommand extends Command {
    private String productoId;
    private Integer precio;

    public AsignarPrecioCommand() {}

    public AsignarPrecioCommand(String productoId, Integer precio) {
        this.productoId = productoId;
        this.precio = precio;
    }

    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }
}
