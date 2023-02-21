package org.example.producto.bussiness.usecases;

import org.example.producto.bussiness.gateways.DomainEventRepository;
import org.example.producto.bussiness.gateways.EventBus;
import org.example.producto.bussiness.generic.UseCaseForEvent;
import org.example.producto.domain.Producto;
import org.example.producto.domain.eventos.ConceptoAprobado;
import org.example.producto.domain.values.ProductoId;
import org.example.producto.generic.DomainEvent;

import java.util.List;
import java.util.stream.Collectors;

public class ProductoPreProductivoEventUseCase implements UseCaseForEvent {
    private final DomainEventRepository repository;
    private final EventBus eventBus;

    public ProductoPreProductivoEventUseCase(DomainEventRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }
    @Override
    public List<DomainEvent> apply(DomainEvent event) {
        ConceptoAprobado conceptoAprobado = (ConceptoAprobado) event;

        List<DomainEvent> events = repository.findById(conceptoAprobado.aggregateRootId());
        Producto producto = Producto.from(ProductoId.of(conceptoAprobado.aggregateRootId()), events);
        producto.actualizarEstadoPreProductivo();

        return producto
                .getUncommittedChanges()
                .stream()
                .map(event1 -> repository.saveEvent(event1))
                .map(event1 -> {
                    eventBus.publish(event1);
                    return event1;
                })
                .collect(Collectors.toList());
    }
}
