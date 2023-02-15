package org.example.producto.domain.comados;

import org.example.producto.generic.Command;

public class AsignarHorasHombreCommand extends Command {
    private String productoId;
    private Integer horasHombre;

    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public Integer getHorasHombre() {
        return horasHombre;
    }

    public void setHorasHombre(Integer horasHombre) {
        this.horasHombre = horasHombre;
    }
}
