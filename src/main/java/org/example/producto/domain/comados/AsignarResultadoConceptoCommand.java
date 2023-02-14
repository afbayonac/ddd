package org.example.producto.domain.comados;

public class AsignarResultadoConceptoCommand {
    private String productoId;
    private String pruebaConceptoId;
    private String resultado;

    public AsignarResultadoConceptoCommand() {}

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

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
