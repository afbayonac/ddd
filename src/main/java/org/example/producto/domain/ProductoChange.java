package org.example.producto.domain;

import org.example.producto.domain.comados.RechazarPruebaViabilidadCommand;
import org.example.producto.domain.eventos.*;
import org.example.producto.domain.values.ConceptoId;
import org.example.producto.domain.values.EstadoProducto;
import org.example.producto.generic.EventChange;

import java.util.HashSet;
import java.util.Iterator;

public class ProductoChange extends EventChange {
    public ProductoChange(Producto producto) {
        apply((ProductoCreado event) -> {
            producto.authorId = event.getAuthorId();
            producto.nombre = event.getNombre();
            producto.estado = new EstadoProducto();
        });

        apply((HorasHombreAsignado event) -> {
            producto.horasHombre = event.getHorasHombre();
        });

        apply((PrecioAsignado event) -> {
            producto.precio = event.getPrecio();
        });

        apply((ConceptoCreado event) -> {
            var concepto = new Concepto(event.getConceptoId() ,event.getReceta());
            producto.conceptos.add(concepto);
        });

        apply((ResultadoConceptoAsignado event) -> {
            for (Iterator<Concepto> it = producto.conceptos.iterator(); it.hasNext(); ) {
                Concepto concepto = it.next();
                if (concepto.identity().equals(event.getConceptoId())) {
                    concepto.resultado = event.getResultado();
                }
            }
        });

        apply((ConceptoAprobado event) -> {
            for (Iterator<Concepto> it = producto.conceptos.iterator(); it.hasNext(); ) {
                Concepto concepto = it.next();
                if (concepto.identity().equals(event.getConceptoId())) {
                    concepto.aprobar();
                }
            }
        });

        apply((ConceptoRechazado event) -> {
            for (Iterator<Concepto> it = producto.conceptos.iterator(); it.hasNext(); ) {
                Concepto concepto = it.next();
                if (concepto.identity().equals(event.getConceptoId())) {
                    concepto.rechazar();
                }
            }
        });

        apply((PruebaViabilidadCreado event) -> {
            for (Iterator<Concepto> it = producto.conceptos.iterator(); it.hasNext(); ) {
                Concepto concepto = it.next();
                if (concepto.identity().equals(event.getConceptoId())) {
                    var pruebaViabilidad = new PruebaViabilidad(
                            event.getPruebaViabilidadId(),
                            event.getAuthorId(),
                            event.getDescripcion()
                    );
                    concepto.pruebasViabilidad.add(pruebaViabilidad);
                }
            }
        });

        apply((PruebaViabilidadAprobado event) -> {
            for (Iterator<Concepto> it = producto.conceptos.iterator(); it.hasNext(); ) {
                Concepto concepto = it.next();
                if (concepto.identity().equals(event.getConceptoId())) {
                    concepto.aprobarPruebaViabilidad(event.getPruebaViabilidadId());
                }
            }
        });

        apply((PruebaViabilidadRechazado event) -> {
            for (Iterator<Concepto> it = producto.conceptos.iterator(); it.hasNext(); ) {
                Concepto concepto = it.next();
                if (concepto.identity().equals(event.getConceptoId())) {
                    concepto.rechazarPruebaViabilidad(event.getPruebaViabilidadId());
                }
            }
        });
    }
}
