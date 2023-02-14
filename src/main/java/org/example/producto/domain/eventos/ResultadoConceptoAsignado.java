package org.example.producto.domain.eventos;

import org.example.producto.domain.values.ConceptoId;
import org.example.producto.domain.values.Resultado;
import org.example.producto.generic.DomainEvent;

public class ResultadoConceptoAsignado extends DomainEvent {
    private final Resultado resultado;
    private final ConceptoId conceptoId;

    public ResultadoConceptoAsignado(ConceptoId conceptoId, Resultado resultado) {
        super("producto.resultado.concepto.asignado");
        this.resultado = resultado;
        this.conceptoId = conceptoId;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public ConceptoId getConceptoId() {
        return conceptoId;
    }
}
