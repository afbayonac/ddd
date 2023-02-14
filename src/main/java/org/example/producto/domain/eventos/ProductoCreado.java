package org.example.producto.domain.eventos;

import org.example.producto.domain.values.*;
import org.example.producto.generic.DomainEvent;

public class ProductoCreado extends DomainEvent {
    protected AuthorId authorId;
    protected NombreProducto nombre;

    public ProductoCreado(
            AuthorId authorId,
            NombreProducto nombre
    ) {
        super("producto.creado");
        this.authorId = authorId;
        this.nombre = nombre;
    }

    public AuthorId getAuthorId() {
        return authorId;
    }

    public NombreProducto getNombre() {
        return nombre;
    }
}

