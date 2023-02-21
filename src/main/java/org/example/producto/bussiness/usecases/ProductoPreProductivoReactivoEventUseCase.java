package org.example.producto.bussiness.usecases;

import org.example.producto.bussiness.gateways.DomainEventReactiveRepository;
import org.example.producto.bussiness.gateways.EventBus;
import org.example.producto.bussiness.generic.UseCaseReactiveForEvent;
import org.example.producto.domain.Producto;
import org.example.producto.domain.eventos.ConceptoAprobado;
import org.example.producto.domain.values.ProductoId;
import org.example.producto.generic.DomainEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ProductoPreProductivoReactivoEventUseCase extends UseCaseReactiveForEvent<ConceptoAprobado> {
    private final DomainEventReactiveRepository repository;
    private final EventBus eventBus;
    public ProductoPreProductivoReactivoEventUseCase(DomainEventReactiveRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }
    @Override
    public Flux<DomainEvent> apply(Mono<ConceptoAprobado> AprobarConceptoCommandMono) {
        return AprobarConceptoCommandMono
        .flatMapMany(event ->
            repository.findById(event.aggregateRootId())
            .collectList()
            .flatMapIterable(domainEvents -> {
                Producto producto = Producto.from(ProductoId.of(event.aggregateRootId()), domainEvents);
                producto.actualizarEstadoPreProductivo();
                return producto.getUncommittedChanges();
            })
            .map(domainEvent -> {
                eventBus.publish(domainEvent);
                return domainEvent;
            })
            .flatMap(domainEvent -> repository.saveEvent(domainEvent))
        );
    }
}
