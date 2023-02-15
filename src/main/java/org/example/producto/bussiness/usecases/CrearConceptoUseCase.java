package org.example.producto.bussiness.usecases;

import org.example.producto.bussiness.gateways.DomainEventRepository;
import org.example.producto.bussiness.generic.UseCaseForCommand;
import org.example.producto.domain.Producto;
import org.example.producto.domain.comados.CrearConceptoCommand;
import org.example.producto.domain.values.ConceptoId;
import org.example.producto.domain.values.ProductoId;
import org.example.producto.domain.values.Receta;
import org.example.producto.generic.Command;
import org.example.producto.generic.DomainEvent;

import java.util.List;
import java.util.stream.Collectors;

public class CrearConceptoUseCase implements UseCaseForCommand {
    private final DomainEventRepository repository;
    public CrearConceptoUseCase(DomainEventRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<DomainEvent> apply(Command command) {
        CrearConceptoCommand crearConceptoCommand = (CrearConceptoCommand) command;

        List<DomainEvent> events = repository.findById(crearConceptoCommand.getProductoId());
        Producto producto = Producto.from(ProductoId.of(crearConceptoCommand.getProductoId()), events);
        producto.crearConcepto(
                new ConceptoId(),
                new Receta(crearConceptoCommand.getReceta())
        );

        return producto
                .getUncommittedChanges()
                .stream()
                .map(event -> repository.saveEvent(event))
                .collect(Collectors.toList());
    }
}
