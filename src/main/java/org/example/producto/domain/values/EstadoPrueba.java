package org.example.producto.domain.values;

import org.example.producto.generic.ValueObject;

public class EstadoPrueba implements ValueObject<String> {
    private enum Estado {
        ESPERA,
        APROBADO,
        RECHAZADO
    }
    final Estado estado;

    public EstadoPrueba() {
        this.estado = Estado.ESPERA;
    };

    private EstadoPrueba(Estado e) {
        this.estado = e;
    };

    public EstadoPrueba aprovar() {
       return new EstadoPrueba(Estado.APROBADO);
    }

    public EstadoPrueba rechazar() {
        return new EstadoPrueba(Estado.RECHAZADO);
    }

    @Override
    public String value() {
        return estado.toString();
    }
}
