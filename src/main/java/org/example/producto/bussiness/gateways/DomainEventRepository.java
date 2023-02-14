package org.example.producto.bussiness.gateways;

import org.example.producto.generic.DomainEvent;

import java.util.List;

public interface DomainEventRepository {
    List<DomainEvent> findById(String aggregateId);
    DomainEvent saveEvent(DomainEvent event);
}
