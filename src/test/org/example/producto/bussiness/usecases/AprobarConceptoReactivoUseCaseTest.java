package org.example.producto.bussiness.usecases;

import org.example.producto.bussiness.gateways.DomainEventReactiveRepository;
import org.example.producto.domain.comados.AprobarConceptoCommand;
import org.example.producto.domain.comados.AsignarPrecioCommand;
import org.example.producto.domain.eventos.ConceptoAprobado;
import org.example.producto.domain.eventos.ConceptoCreado;
import org.example.producto.domain.eventos.PrecioAsignado;
import org.example.producto.domain.eventos.ProductoCreado;
import org.example.producto.domain.values.*;
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
class AprobarConceptoReactivoUseCaseTest {
    @Mock
    private DomainEventReactiveRepository repository;

    private AprobarConceptoReactivoUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new AprobarConceptoReactivoUseCase(repository);
    }

    @Test
    void successFullScene() {
        var CONCEPTO_ID = new ConceptoId();
        var PRODUCTO_ID = new ProductoId();
        var NOMBRE = "producto-test";
        var VERSION = "version-test";
        var AUTHOR_ID = "author-id";
        var RECETA = "asi se hace";

        ProductoCreado productoCreado = new ProductoCreado(
                AuthorId.of(AUTHOR_ID),
                new NombreProducto(NOMBRE, VERSION)
        );

        ConceptoCreado conceptoCreado = new ConceptoCreado(
                CONCEPTO_ID,
                new Receta(RECETA)
        );

        AprobarConceptoCommand command = new AprobarConceptoCommand(
                PRODUCTO_ID.value(),
                CONCEPTO_ID.value()
        );

        Mockito
                .when(repository.findById(PRODUCTO_ID.value()))
                .thenReturn(Flux.just(productoCreado, conceptoCreado));

        Mockito
                .when(repository.saveEvent(ArgumentMatchers.any(ConceptoAprobado.class)))
                .thenAnswer(interceptor -> Mono.just(interceptor.getArgument(0)));

        Flux<DomainEvent> result = useCase.apply(Mono.just(command));

        StepVerifier
                .create(result)
                .assertNext(domainEvent -> {
                    ConceptoAprobado conceptoAprobado = (ConceptoAprobado) domainEvent;
                    Assertions.assertEquals(CONCEPTO_ID, conceptoAprobado.getConceptoId());
                })
                .verifyComplete();
    }
}