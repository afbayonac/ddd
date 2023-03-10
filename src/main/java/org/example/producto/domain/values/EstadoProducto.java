package org.example.producto.domain.values;

import org.example.producto.generic.ValueObject;

public class EstadoProducto implements ValueObject<String> {
    private enum Estado {
        CONCEPTO,
        PRE_PRODUCTIVO,
        PRODUCTIVO,
        CERRADO,
        CANCELADO
    }
    final Estado estado;

    public EstadoProducto() {
        this.estado = Estado.CONCEPTO;
    };

    private EstadoProducto(Estado e) {
        this.estado = e;
    };

    public EstadoProducto HabilitarPruebas() {
       return new EstadoProducto(Estado.PRE_PRODUCTIVO);
    }

    public EstadoProducto HabilitarProductivo() {
        return new EstadoProducto(Estado.PRODUCTIVO);
    }

    public EstadoProducto Cerrar() {
        return new EstadoProducto(Estado.CERRADO);
    }

    public EstadoProducto Cancelar() {
        return new EstadoProducto(Estado.CANCELADO);
    }

    @Override
    public String value() {
        return estado.toString();
    }
}
