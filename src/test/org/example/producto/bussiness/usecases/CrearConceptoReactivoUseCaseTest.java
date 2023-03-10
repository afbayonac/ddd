package org.example.producto.bussiness.usecases;

import org.example.producto.bussiness.gateways.DomainEventReactiveRepository;
import org.example.producto.domain.comados.CrearConceptoCommand;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class CrearConceptoReactivoUseCaseTest {
    @Mock
    private DomainEventReactiveRepository repository;

    private CrearConceptoReactivoUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new CrearConceptoReactivoUseCase(repository);
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
                .thenReturn(Flux.just(productoCreado));

        Mockito
                .when(repository.saveEvent(ArgumentMatchers.any(ConceptoCreado.class)))
                .thenAnswer(interceptor -> Mono.just(interceptor.getArgument(0)));

        Flux<DomainEvent> result = useCase.apply(Mono.just(command));

        StepVerifier
                .create(result)
                .assertNext(domainEvent -> {
                    ConceptoCreado conceptoCreado = (ConceptoCreado) domainEvent;
                    Assertions.assertEquals(RECETA, conceptoCreado.getReceta().value());
                    Assertions.assertEquals(PRODUCTO_ID.value(), conceptoCreado.aggregateRootId());
                })
                .verifyComplete();
    }
}