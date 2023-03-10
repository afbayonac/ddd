package org.example.producto.bussiness.usecases;

import org.example.producto.bussiness.gateways.DomainEventRepository;
import org.example.producto.bussiness.gateways.EventBus;
import org.example.producto.bussiness.generic.UseCaseForCommand;
import org.example.producto.domain.Producto;
import org.example.producto.domain.comados.AprobarConceptoCommand;
import org.example.producto.domain.values.ConceptoId;
import org.example.producto.domain.values.ProductoId;
import org.example.producto.generic.Command;
import org.example.producto.generic.DomainEvent;

import java.util.List;
import java.util.stream.Collectors;

public class AprobarConceptoUseCase implements UseCaseForCommand {
    private final DomainEventRepository repository;
    private final EventBus eventBus;

    public AprobarConceptoUseCase(DomainEventRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }
    @Override
    public List<DomainEvent> apply(Command command) {
        AprobarConceptoCommand aprobarConceptoCommand = (AprobarConceptoCommand) command;

        List<DomainEvent> events = repository.findById(aprobarConceptoCommand.getProductoId());
        Producto producto = Producto.from(ProductoId.of(aprobarConceptoCommand.getProductoId()), events);
        producto.aprobarConcepto(
                ConceptoId.of(aprobarConceptoCommand.getPruebaConceptoId())
        );

        return producto
                .getUncommittedChanges()
                .stream()
                .map(event -> repository.saveEvent(event))
                .map(event -> {
                    eventBus.publish(event);
                    return event;
                })
                .collect(Collectors.toList());
    }
}
