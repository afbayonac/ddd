package org.example.producto.bussiness.usecases;

import org.example.producto.bussiness.gateways.DomainEventRepository;
import org.example.producto.domain.comados.CrearProductoCommand;
import org.example.producto.domain.eventos.ProductoCreado;
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

@ExtendWith(MockitoExtension.class)
class CrearProductoUseCaseTest {
    @Mock
    private DomainEventRepository repository;

    private CrearProductoUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new CrearProductoUseCase(repository);
    }

    @Test
    void successFullScene() {
        var NOMBRE = "producto-test";
        var VERSION = "version-test";
        var AUTHOR_ID = "author-id";

        CrearProductoCommand command = new CrearProductoCommand(
                AUTHOR_ID,
                NOMBRE,
                VERSION
        );

        Mockito
                .when(repository.saveEvent(ArgumentMatchers.any(ProductoCreado.class)))
                .thenAnswer(interceptor -> interceptor.getArgument(0));

        List<DomainEvent> result = useCase.apply(command);

        ProductoCreado event = (ProductoCreado) result.get(0);
        Assertions.assertEquals(NOMBRE, event.getNombre().value().name());
        Assertions.assertEquals(VERSION, event.getNombre().value().version());
        Assertions.assertEquals(AUTHOR_ID, event.getAuthorId().value());
    }
}