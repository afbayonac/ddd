package org.example.producto.bussiness.generic;

import org.example.producto.generic.DomainEvent;

import java.util.List;

public interface UseCaseForEvent<R extends DomainEvent>{
    List<DomainEvent> apply(R command);
}
