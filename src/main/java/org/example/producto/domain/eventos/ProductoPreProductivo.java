package org.example.producto.domain.eventos;

import org.example.producto.generic.DomainEvent;

public class ProductoPreProductivo extends DomainEvent {
    public ProductoPreProductivo() {
        super("producto.pre.productivo");
    }
}
