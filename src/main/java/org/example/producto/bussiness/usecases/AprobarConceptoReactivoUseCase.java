package org.example.producto.bussiness.usecases;

import org.example.producto.bussiness.gateways.DomainEventReactiveRepository;
import org.example.producto.bussiness.gateways.EventBus;
import org.example.producto.bussiness.generic.UseCaseReactiveForCommand;
import org.example.producto.domain.Producto;
import org.example.producto.domain.comados.AprobarConceptoCommand;
import org.example.producto.domain.values.ConceptoId;
import org.example.producto.domain.values.ProductoId;
import org.example.producto.generic.DomainEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AprobarConceptoReactivoUseCase extends UseCaseReactiveForCommand<AprobarConceptoCommand> {
    private final DomainEventReactiveRepository repository;
    private final EventBus eventBus;

    public AprobarConceptoReactivoUseCase(DomainEventReactiveRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }
    @Override
    public Flux<DomainEvent> apply(Mono<AprobarConceptoCommand> AprobarConceptoCommandMono) {
        return AprobarConceptoCommandMono
        .flatMapMany(command ->
            repository.findById(command.getProductoId())
            .collectList()
            .flatMapIterable(domainEvents -> {
                Producto producto = Producto.from(ProductoId.of(command.getProductoId()), domainEvents);
                producto.aprobarConcepto(ConceptoId.of(command.getPruebaConceptoId()));
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
