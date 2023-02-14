package org.example.producto.domain.values;

import org.example.producto.generic.ValueObject;

public class Resultado implements ValueObject<String> {
    protected final String resultado;

    public Resultado(final String resultado) {
        this.resultado = resultado;
    }

    @Override
    public String value() {
        return resultado;
    }
}
