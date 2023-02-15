package org.example.producto.bussiness.usecases;

import org.example.producto.bussiness.gateways.DomainEventRepository;
import org.example.producto.bussiness.generic.UseCaseForCommand;
import org.example.producto.domain.Producto;
import org.example.producto.domain.comados.AsignarPrecioCommand;
import org.example.producto.domain.values.Precio;
import org.example.producto.domain.values.ProductoId;
import org.example.producto.generic.Command;
import org.example.producto.generic.DomainEvent;

import java.util.List;
import java.util.stream.Collectors;

public class AsignarPrecioUseCase implements UseCaseForCommand {
    private final DomainEventRepository repository;
    public AsignarPrecioUseCase(DomainEventRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<DomainEvent> apply(Command command) {
        AsignarPrecioCommand asignarPrecioCommand = (AsignarPrecioCommand) command;

        List<DomainEvent> events = repository.findById(asignarPrecioCommand.getProductoId());
        Producto producto = Producto.from(ProductoId.of(asignarPrecioCommand.getProductoId()), events);
        producto.asignarPrecio(new Precio(asignarPrecioCommand.getPrecio()));

        return producto
                .getUncommittedChanges()
                .stream()
                .map(event -> repository.saveEvent(event))
                .collect(Collectors.toList());
    }
}
