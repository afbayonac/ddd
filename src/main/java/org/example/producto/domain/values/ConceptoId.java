package org.example.producto.domain.values;

import org.example.producto.generic.Identity;

public class ConceptoId extends Identity {
    private ConceptoId(String uuid) {
        super(uuid);
    }

    public ConceptoId() {}

    public static ConceptoId of(String uuid){
        return new ConceptoId(uuid);
    }
}
