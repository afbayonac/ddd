package org.example.producto.bussiness.gateways;

import org.example.producto.generic.DomainEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;import java.util.List;

public interface DomainEventReactiveRepository {
    Flux<DomainEvent> findById(String aggregateId);
    Mono<DomainEvent> saveEvent(DomainEvent event);
}
