package org.example.producto.domain.values;

import org.example.producto.generic.Identity;

public class ProductoId extends Identity {
    public ProductoId(String uuid) {
        super(uuid);
    }

    public ProductoId() {
    }

    public static ProductoId of(String uuid){
        return new ProductoId(uuid);
    }
}
