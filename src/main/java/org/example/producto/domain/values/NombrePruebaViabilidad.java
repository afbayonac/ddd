package org.example.producto.domain.values;

import org.example.producto.generic.ValueObject;

public class NombrePruebaViabilidad implements ValueObject<String> {
    protected final String nombre;

    public NombrePruebaViabilidad(final String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String value() {
        return nombre;
    }
}
