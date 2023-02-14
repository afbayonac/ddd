package org.example.producto.domain.comados;

public class AsignarPrecioCommand {
    private String porductoId;
    private Integer precio;

    public String getPorductoId() {
        return porductoId;
    }

    public void setPorductoId(String porductoId) {
        this.porductoId = porductoId;
    }

    public Integer getHorasHombre() {
        return precio;
    }

    public void setHorasHombre(Integer horasHombre) {
        this.precio = precio;
    }
}
