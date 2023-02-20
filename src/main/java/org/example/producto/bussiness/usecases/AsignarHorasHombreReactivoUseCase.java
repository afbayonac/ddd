package org.example.producto.bussiness.usecases;

import org.example.producto.bussiness.gateways.DomainEventReactiveRepository;
import org.example.producto.bussiness.generic.UseCaseReactiveForCommand;
import org.example.producto.domain.Producto;
import org.example.producto.domain.comados.AprobarConceptoCommand;
import org.example.producto.domain.comados.AsignarHorasHombreCommand;
import org.example.producto.domain.values.ConceptoId;
import org.example.producto.domain.values.HorasHombre;
import org.example.producto.domain.values.ProductoId;
import org.example.producto.generic.DomainEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AsignarHorasHombreReactivoUseCase extends UseCaseReactiveForCommand<AsignarHorasHombreCommand> {
    private final DomainEventReactiveRepository repository;
    public AsignarHorasHombreReactivoUseCase(DomainEventReactiveRepository repository) {
        this.repository = repository;
    }
    @Override
    public Flux<DomainEvent> apply(Mono<AsignarHorasHombreCommand> AsignarHorasHombreCommandMono) {
        return AsignarHorasHombreCommandMono
        .flatMapMany(command ->
            repository.findById(command.getProductoId())
            .collectList()
            .flatMapIterable(domainEvents -> {
                Producto producto = Producto.from(ProductoId.of(command.getProductoId()), domainEvents);
                producto.agregarHorasHombre(new HorasHombre(command.getHorasHombre()));
                return producto.getUncommittedChanges();
            })
            .map(domainEvent -> {
                return domainEvent;
            })
            .flatMap(domainEvent -> repository.saveEvent(domainEvent))
        );
    }
}
