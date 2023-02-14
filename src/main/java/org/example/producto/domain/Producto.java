package org.example.producto.domain;

import org.example.producto.domain.eventos.*;
import org.example.producto.domain.values.*;
import org.example.producto.generic.AggregateRoot;

import java.util.Set;

public class Producto extends AggregateRoot<ProductoId> {
    protected Set<Concepto> conceptos;
    protected AuthorId authorId;
    protected ConceptoId masterId;
    protected EstadoProducto estado;
    protected HorasHombre horasHombre;
    protected Precio precio;
    protected NombreProducto nombre;
    public Producto (
            ProductoId productoId,
            AuthorId authorId,
            NombreProducto nombre
    ) {
        super(productoId);
        subscribe(new ProductoChange(this));
        appendChange(new ProductoCreado(authorId, nombre));
    }

    public void agregarHorasHombre(HorasHombre horasHombre) {
        appendChange(new HorasHombreAsignado(horasHombre));
    }

    public void asignarPrecio(Precio precio) {
        appendChange(new PrecioAsignado(precio));
    }

    public void crearPruebaConcepto(ConceptoId conceptoId, Receta receta) {
        appendChange(new ConceptoCreado(conceptoId, receta));
    }

    public void asignarResultadoConcepto(ConceptoId conceptoId, Resultado resultado) {
        appendChange(new ResultadoConceptoAsignado(conceptoId, resultado));
    }

    public void aprobarConcepto(ConceptoId conceptoId) {
        appendChange(new ConceptoAprobado(conceptoId));
    }

    public void rechazarConcepto(ConceptoId conceptoId) {
        appendChange(new ConceptoRechazado(conceptoId));
    }

    public void crearPruebaViabilidad(
            ConceptoId conceptoId,
            PruebaViabilidadId pruebaViabilidadId,
            AuthorId authorId,
            Descripcion descripcion
    ) {
        appendChange(new PruebaViabilidadCreado(conceptoId, pruebaViabilidadId, authorId, descripcion));
    }

    public void aprobarPruebaViabilidad(
            ConceptoId conceptoId,
            PruebaViabilidadId pruebaViabilidadId
    ) {
        appendChange(new PruebaViabilidadAprobado(conceptoId, pruebaViabilidadId));
    }

    public void rechazarPruebaViabilidad(
            ConceptoId conceptoId,
            PruebaViabilidadId pruebaViabilidadId
    ) {
        appendChange(new PruebaViabilidadRechazado(conceptoId, pruebaViabilidadId));
    }


    private void actualizarEstado() {}
}
