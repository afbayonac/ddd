package org.example.producto.domain.eventos;

import org.example.producto.domain.values.HorasHombre;
import org.example.producto.generic.DomainEvent;

public class HorasHombreAsignado extends DomainEvent {
    private final HorasHombre horasHombre;

    public HorasHombreAsignado(HorasHombre horasHombre) {
        super("producto.horas.hombre.asignado");
        this.horasHombre = horasHombre;
    }

    public HorasHombre getHorasHombre() {
        return horasHombre;
    }
}
