package org.example.producto.domain.values;

import org.example.producto.generic.ValueObject;

public class HorasHombre implements ValueObject<Integer> {
    protected final Integer horasHombre;

    public HorasHombre(final Integer horasHombre) {
        this.horasHombre = horasHombre;
    }

    @Override
    public Integer value() {
        return horasHombre;
    }
}

