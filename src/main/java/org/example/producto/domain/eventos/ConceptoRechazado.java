package org.example.producto.domain.eventos;

import org.example.producto.domain.values.ConceptoId;
import org.example.producto.domain.values.Resultado;
import org.example.producto.generic.DomainEvent;

public class ConceptoRechazado extends DomainEvent {
    private final ConceptoId conceptoId;

    public ConceptoRechazado(ConceptoId conceptoId) {
        super("producto.resultado.concepto.asignado");
        this.conceptoId = conceptoId;
    }

    public ConceptoId getConceptoId() {
        return conceptoId;
    }
}
