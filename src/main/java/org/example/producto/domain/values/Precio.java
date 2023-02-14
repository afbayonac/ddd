package org.example.producto.domain.values;

import org.example.producto.generic.ValueObject;

import java.util.Objects;

public class Precio implements ValueObject<Integer> {
    private final Integer value;

    public Precio(Integer value) {
        this.value = Objects.requireNonNull(value);
        if(value <= 0){
            throw new IllegalArgumentException("El valor debe un monto positivo");
        }
    }

    @Override
    public Integer value() { return  value; }
}
