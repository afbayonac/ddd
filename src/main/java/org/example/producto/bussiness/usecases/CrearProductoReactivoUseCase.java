package org.example.producto.bussiness.usecases;

import org.example.producto.generic.DomainEvent;
import org.example.producto.bussiness.gateways.DomainEventReactiveRepository;
import org.example.producto.bussiness.generic.UseCaseReactiveForCommand;
import org.example.producto.domain.Producto;
import org.example.producto.domain.comados.CrearProductoCommand;
import org.example.producto.domain.values.AuthorId;
import org.example.producto.domain.values.NombreProducto;
import org.example.producto.domain.values.ProductoId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CrearProductoReactivoUseCase extends UseCaseReactiveForCommand<CrearProductoCommand> {
    private final DomainEventReactiveRepository repository;
    public CrearProductoReactivoUseCase(DomainEventReactiveRepository repository) {
        this.repository = repository;
    }
    @Override
    public Flux<DomainEvent> apply(Mono<CrearProductoCommand> crearProductoCommandMono) {

        return crearProductoCommandMono
        .flatMapIterable(command -> {
            Producto producto = new Producto(
                    new ProductoId(),
                    AuthorId.of(command.getAuthorId()),
                    new NombreProducto(command.getName(), command.getVersion())
            );
            return producto.getUncommittedChanges();
        })
        .flatMap(event -> repository.saveEvent(event));
    }
}
