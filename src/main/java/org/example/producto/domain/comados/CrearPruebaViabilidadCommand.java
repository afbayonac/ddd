package org.example.producto.domain.comados;

import org.example.producto.domain.values.Descripcion;
import org.example.producto.generic.Command;

public class CrearPruebaViabilidadCommand extends Command {
    private String productoId;
    private String pruebaConceptoId;
    private String pruebaViabilidadId;

    private String Author;

    private Descripcion descripcion;

    public CrearPruebaViabilidadCommand() {}

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public Descripcion getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(Descripcion descripcion) {
        this.descripcion = descripcion;
    }

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
