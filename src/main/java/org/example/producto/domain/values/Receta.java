package org.example.producto.domain.values;

import org.example.producto.generic.ValueObject;

public class Receta implements ValueObject<String> {
    protected final String resultado;

    public Receta(final String resultado) {
        this.resultado = resultado;
    }

    @Override
    public String value() {
        return resultado;
    }
}
