package org.example.producto.domain.eventos;

import org.example.producto.domain.values.ConceptoId;
import org.example.producto.domain.values.PruebaViabilidadId;
import org.example.producto.generic.DomainEvent;

public class PruebaViabilidadRechazado extends DomainEvent {
    protected PruebaViabilidadId pruebaViabilidadId;
    protected ConceptoId conceptoId;

    public PruebaViabilidadRechazado(
            ConceptoId conceptoId,
            PruebaViabilidadId pruebaViabilidadId
    ) {
        super("producto.prueba.viabilidad.rechazado");
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
