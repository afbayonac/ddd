package org.example.producto.domain.eventos;

import org.example.producto.domain.values.Precio;
import org.example.producto.generic.DomainEvent;

public class PrecioAsignado extends DomainEvent {
    private final Precio precio;

    public PrecioAsignado(Precio precio) {
        super("producto.precio.asignado");
        this.precio = precio;
    }

    public Precio getPrecio() {
        return precio;
    }
}
