package org.example.producto.domain;

import org.example.producto.domain.eventos.*;
import org.example.producto.domain.values.EstadoProducto;
import org.example.producto.generic.EventChange;

import java.util.Optional;

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
            Optional<Concepto> result = producto
                    .conceptos
                    .stream()
                    .filter(c -> c.identity().equals(event.getConceptoId()))
                    .findFirst();

            if (result.isPresent()) {
                result.get().resultado = event.getResultado();
            }
        });

        apply((ConceptoAprobado event) -> {
            Optional<Concepto> result = producto
                    .conceptos
                    .stream()
                    .filter(c -> c.identity().equals(event.getConceptoId()))
                    .findFirst();

            if (result.isPresent()) {
                result.get().aprobar();
            }
        });

        apply((ConceptoRechazado event) -> {
            Optional<Concepto> result = producto
                    .conceptos
                    .stream()
                    .filter(c -> c.identity().equals(event.getConceptoId()))
                    .findFirst();

            if (result.isPresent()) {
                result.get().rechazar();
            }
        });

        apply((PruebaViabilidadCreado event) -> {
            Optional<Concepto> result = producto
                    .conceptos
                    .stream()
                    .filter(c -> c.identity().equals(event.getConceptoId()))
                    .findFirst();

            if (result.isPresent()) {
                var pruebaViabilidad = new PruebaViabilidad(
                        event.getPruebaViabilidadId(),
                        event.getAuthorId(),
                        event.getDescripcion()
                );

                result.get().pruebasViabilidad.add(pruebaViabilidad);
            }
        });

        apply((PruebaViabilidadAprobado event) -> {
            Optional<Concepto> result = producto
                    .conceptos
                    .stream()
                    .filter(c -> c.identity().equals(event.getConceptoId()))
                    .findFirst();

            if (result.isPresent()) {
                result.get().aprobarPruebaViabilidad(event.getPruebaViabilidadId());
            }

        });

        apply((PruebaViabilidadRechazado event) -> {
            Optional<Concepto> result = producto
                    .conceptos
                    .stream()
                    .filter(c -> c.identity().equals(event.getConceptoId()))
                    .findFirst();

            if (result.isPresent()) {
                result.get().rechazarPruebaViabilidad(event.getPruebaViabilidadId());
            }
        });
    }
}
