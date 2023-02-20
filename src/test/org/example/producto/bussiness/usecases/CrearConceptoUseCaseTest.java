package org.example.producto.bussiness.usecases;

import org.example.producto.bussiness.gateways.DomainEventRepository;
import org.example.producto.domain.Producto;
import org.example.producto.domain.comados.CrearConceptoCommand;
import org.example.producto.domain.comados.CrearProductoCommand;
import org.example.producto.domain.eventos.ConceptoCreado;
import org.example.producto.domain.eventos.ProductoCreado;
import org.example.producto.domain.values.AuthorId;
import org.example.producto.domain.values.NombreProducto;
import org.example.producto.domain.values.ProductoId;
import org.example.producto.generic.DomainEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CrearConceptoUseCaseTest {
    @Mock
    private DomainEventRepository repository;

    private CrearConceptoUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new CrearConceptoUseCase(repository);
    }

    @Test
    void successFullScene() {
        var PRODUCTO_ID = new ProductoId();
        var NOMBRE = "producto-test";
        var VERSION = "version-test";
        var AUTHOR_ID = "author-id";
        var RECETA = "asi se hace";

        CrearConceptoCommand command = new CrearConceptoCommand(
                PRODUCTO_ID.value(),
                RECETA
        );

        ProductoCreado productoCreado = new ProductoCreado(
                AuthorId.of(AUTHOR_ID),
                new NombreProducto(NOMBRE, VERSION)
        );

        Mockito
                .when(repository.findById(PRODUCTO_ID.value()))
                .thenReturn(List.of(productoCreado));

        Mockito
                .when(repository.saveEvent(ArgumentMatchers.any(ConceptoCreado.class)))
                .thenAnswer(interceptor -> interceptor.getArgument(0));

        List<DomainEvent> result = useCase.apply(command);

        ConceptoCreado event = (ConceptoCreado) result.get(0);
        Assertions.assertEquals(RECETA, event.getReceta().value());
        Assertions.assertEquals(PRODUCTO_ID.value(), event.aggregateRootId());
    }
}