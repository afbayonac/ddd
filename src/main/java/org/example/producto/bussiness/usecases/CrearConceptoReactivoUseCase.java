package org.example.producto.bussiness.usecases;

import org.example.producto.bussiness.gateways.DomainEventReactiveRepository;
import org.example.producto.bussiness.gateways.DomainEventRepository;
import org.example.producto.bussiness.generic.UseCaseForCommand;
import org.example.producto.bussiness.generic.UseCaseReactiveForCommand;
import org.example.producto.domain.Producto;
import org.example.producto.domain.comados.AprobarConceptoCommand;
import org.example.producto.domain.comados.CrearConceptoCommand;
import org.example.producto.domain.values.ConceptoId;
import org.example.producto.domain.values.ProductoId;
import org.example.producto.domain.values.Receta;
import org.example.producto.generic.Command;
import org.example.producto.generic.DomainEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

public class CrearConceptoReactivoUseCase extends UseCaseReactiveForCommand<CrearConceptoCommand> {
    private final DomainEventReactiveRepository repository;
    public CrearConceptoReactivoUseCase(DomainEventReactiveRepository repository) {
        this.repository = repository;
    }
    @Override
    public Flux<DomainEvent> apply(Mono<CrearConceptoCommand> CrearConceptoCommandMono) {
        return CrearConceptoCommandMono
        .flatMapMany(command ->
            repository.findById(command.getProductoId())
            .collectList()
            .flatMapIterable(domainEvents -> {
                Producto producto = Producto.from(ProductoId.of(command.getProductoId()), domainEvents);
                producto.crearConcepto(new ConceptoId(), new Receta(command.getReceta()));
                return producto.getUncommittedChanges();
            })
            .map(domainEvent -> {
                return domainEvent;
            })
            .flatMap(domainEvent -> repository.saveEvent(domainEvent))
        );
    }
}
