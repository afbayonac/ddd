package org.example.producto.domain.eventos;

import org.example.producto.domain.values.AuthorId;
import org.example.producto.domain.values.ConceptoId;
import org.example.producto.domain.values.Descripcion;
import org.example.producto.domain.values.PruebaViabilidadId;
import org.example.producto.generic.DomainEvent;

public class PruebaViabilidadCreado extends DomainEvent {
    protected PruebaViabilidadId pruebaViabilidadId;
    protected ConceptoId conceptoId;

    protected AuthorId authorId;

    protected Descripcion descripcion;

    public PruebaViabilidadCreado(
            ConceptoId conceptoId,
            PruebaViabilidadId pruebaViabilidadId,
            AuthorId authorId,
            Descripcion descripcion
    ) {
        super("producto.prueba.viabilidad.creado");
        this.pruebaViabilidadId = pruebaViabilidadId;
        this.conceptoId = conceptoId;
        this.authorId = authorId;
        this.descripcion = descripcion;
    }

    public PruebaViabilidadId getPruebaViabilidadId() {
        return pruebaViabilidadId;
    }

    public ConceptoId getConceptoId() {
        return conceptoId;
    }

    public AuthorId getAuthorId() {
        return authorId;
    }

    public Descripcion getDescripcion() {
        return descripcion;
    }
}
