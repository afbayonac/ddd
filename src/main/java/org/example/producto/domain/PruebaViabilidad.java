package org.example.producto.domain;

import org.example.producto.domain.values.*;
import org.example.producto.generic.Entity;

public class PruebaViabilidad extends Entity<PruebaViabilidadId> {
    protected EstadoPrueba estado;
    protected Descripcion descripcion;
    protected AuthorId authorId;

    public PruebaViabilidad(
            PruebaViabilidadId id,
            AuthorId authorId,
            Descripcion descripcion
    ) {
        super(id);
        this.authorId = authorId;
        this.descripcion = descripcion;
        this.estado = new EstadoPrueba();
    }

    public void rechazar() {
        this.estado = this.estado.rechazar();
    }

    public void aprobar() {
        this.estado = this.estado.aprovar();
    }
}
