package org.example.producto.bussiness.usecases;

import org.example.producto.bussiness.gateways.DomainEventRepository;
import org.example.producto.bussiness.generic.UseCaseForCommand;
import org.example.producto.domain.Producto;
import org.example.producto.domain.comados.CrearProductoCommand;
import org.example.producto.domain.values.AuthorId;
import org.example.producto.domain.values.NombreProducto;
import org.example.producto.domain.values.ProductoId;
import org.example.producto.generic.Command;
import org.example.producto.generic.DomainEvent;

import java.util.List;
import java.util.stream.Collectors;

public class CrearProductoUseCase implements UseCaseForCommand {
    private final DomainEventRepository repository;
    public CrearProductoUseCase(DomainEventRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<DomainEvent> apply(Command command) {
        CrearProductoCommand crearProducto = (CrearProductoCommand) command;
        Producto producto = new Producto(
                new ProductoId(),
                AuthorId.of(crearProducto.getAuthorId()),
                new NombreProducto(crearProducto.getName(), crearProducto.getVersion())
        );

        return producto
                .getUncommittedChanges()
                .stream()
                .map(event -> repository.saveEvent(event))
                .collect(Collectors.toList());
    }
}
