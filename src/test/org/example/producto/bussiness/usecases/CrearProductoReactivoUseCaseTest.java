package org.example.producto.bussiness.usecases;

import org.example.producto.bussiness.gateways.DomainEventReactiveRepository;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class CrearProductoReactivoUseCaseTest {
    @Mock
    private DomainEventReactiveRepository repository;
    private CrearProductoReactivoUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new CrearProductoReactivoUseCase(repository);
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
                .thenAnswer(interceptor -> Mono.just(interceptor.getArgument(0)));

        Flux<DomainEvent> result = useCase.apply(Mono.just(command));

        StepVerifier
                .create(result)
                .assertNext(domainEvent -> {
                    ProductoCreado productoCreado = (ProductoCreado) domainEvent;
                    Assertions.assertEquals(NOMBRE, productoCreado.getNombre().value().name());
                    Assertions.assertEquals(VERSION, productoCreado.getNombre().value().version());
                    Assertions.assertEquals(AUTHOR_ID, productoCreado.getAuthorId().value());
                })
                .verifyComplete();

    }
}