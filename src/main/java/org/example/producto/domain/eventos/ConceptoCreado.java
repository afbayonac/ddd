package org.example.producto.domain.eventos;

import org.example.producto.domain.values.ConceptoId;
import org.example.producto.domain.values.Receta;
import org.example.producto.generic.DomainEvent;

public class ConceptoCreado extends DomainEvent {
    protected Receta receta;
    protected ConceptoId conceptoId;

    public ConceptoCreado(
            ConceptoId conceptoId,
            Receta receta
    ) {
        super("producto.prueba.consepto.creado");
        this.receta = receta;
        this.conceptoId = conceptoId;
    }

    public ConceptoId getConceptoId() {
        return conceptoId;
    }

    public Receta getReceta() {
        return receta;
    }

}
