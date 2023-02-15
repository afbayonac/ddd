package org.example.producto.bussiness.usecases;

import org.example.producto.bussiness.gateways.DomainEventRepository;
import org.example.producto.bussiness.generic.UseCaseForCommand;
import org.example.producto.domain.Producto;
import org.example.producto.domain.comados.AsignarHorasHombreCommand;
import org.example.producto.domain.comados.AsignarPrecioCommand;
import org.example.producto.domain.values.HorasHombre;
import org.example.producto.domain.values.Precio;
import org.example.producto.domain.values.ProductoId;
import org.example.producto.generic.Command;
import org.example.producto.generic.DomainEvent;

import java.util.List;
import java.util.stream.Collectors;

public class AsignarHorasHombreUseCase implements UseCaseForCommand {
    private final DomainEventRepository repository;
    public AsignarHorasHombreUseCase(DomainEventRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<DomainEvent> apply(Command command) {
        AsignarHorasHombreCommand asignarHorasHombreCommand = (AsignarHorasHombreCommand) command;

        List<DomainEvent> events = repository.findById(asignarHorasHombreCommand.getProductoId());
        Producto producto = Producto.from(ProductoId.of(asignarHorasHombreCommand.getProductoId()), events);
        producto.agregarHorasHombre(new HorasHombre(asignarHorasHombreCommand.getHorasHombre()));

        return producto
                .getUncommittedChanges()
                .stream()
                .map(event -> repository.saveEvent(event))
                .collect(Collectors.toList());
    }
}
