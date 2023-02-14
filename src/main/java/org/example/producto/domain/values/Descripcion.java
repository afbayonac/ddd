package org.example.producto.domain.values;

import org.example.producto.generic.ValueObject;

public class Descripcion implements ValueObject<String> {
    protected final String descripcion;

    public Descripcion(final String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String value() {
        return descripcion;
    }
}
