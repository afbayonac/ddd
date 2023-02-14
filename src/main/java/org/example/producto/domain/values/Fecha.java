package org.example.producto.domain.values;

import org.example.producto.generic.ValueObject;

import java.util.Date;

public class Fecha implements ValueObject<Date> {
    protected final Date fecha;

    public Fecha(final Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public Date value() {
        return fecha;
    }
}

