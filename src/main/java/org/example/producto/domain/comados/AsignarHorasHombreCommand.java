package org.example.producto.domain.comados;

public class AsignarHorasHombreCommand {
    private String porductoId;
    private Integer horasHombre;

    public String getPorductoId() {
        return porductoId;
    }

    public void setPorductoId(String porductoId) {
        this.porductoId = porductoId;
    }

    public Integer getHorasHombre() {
        return horasHombre;
    }

    public void setHorasHombre(Integer horasHombre) {
        this.horasHombre = horasHombre;
    }
}
