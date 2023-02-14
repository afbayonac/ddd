package org.example.producto.domain.eventos;

import org.example.producto.domain.values.AuthorId;
import org.example.producto.domain.values.ConceptoId;
import org.example.producto.domain.values.Descripcion;
import org.example.producto.domain.values.PruebaViabilidadId;
import org.example.producto.generic.DomainEvent;

public class PruebaViabilidadAprobado extends DomainEvent {
    protected PruebaViabilidadId pruebaViabilidadId;
    protected ConceptoId conceptoId;

    public PruebaViabilidadAprobado(
            ConceptoId conceptoId,
            PruebaViabilidadId pruebaViabilidadId
    ) {
        super("producto.prueba.viabilidad.aprobado");
        this.pruebaViabilidadId = pruebaViabilidadId;
        this.conceptoId = conceptoId;
    }

    public PruebaViabilidadId getPruebaViabilidadId() {
        return pruebaViabilidadId;
    }

    public ConceptoId getConceptoId() {
        return conceptoId;
    }
}
