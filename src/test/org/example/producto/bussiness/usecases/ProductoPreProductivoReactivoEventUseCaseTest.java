package org.example.producto.bussiness.usecases;

import org.example.producto.bussiness.gateways.DomainEventReactiveRepository;
import org.example.producto.bussiness.gateways.EventBus;
import org.example.producto.domain.eventos.ConceptoAprobado;
import org.example.producto.domain.eventos.ConceptoCreado;
import org.example.producto.domain.eventos.ProductoCreado;
import org.example.producto.domain.eventos.ProductoPreProductivo;
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
class ProductoPreProductivoReactivoEventUseCaseTest {
    @Mock
    private DomainEventReactiveRepository repository;
    @Mock
    private EventBus eventBus;

    private ProductoPreProductivoReactivoEventUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new ProductoPreProductivoReactivoEventUseCase(repository, eventBus);
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

        ConceptoAprobado conceptoAprobado = new ConceptoAprobado(
                CONCEPTO_ID
        );
        conceptoAprobado.setAggregateRootId(PRODUCTO_ID.value());

        Mockito
                .when(repository.findById(PRODUCTO_ID.value()))
                .thenReturn(Flux.just(productoCreado, conceptoCreado, conceptoAprobado));

        Mockito
                .when(repository.saveEvent(ArgumentMatchers.any(ProductoPreProductivo.class)))
                .thenAnswer(interceptor -> Mono.just(interceptor.getArgument(0)));

        Mockito
                .doAnswer(i -> null)
                .when(eventBus)
                .publish(ArgumentMatchers.any(DomainEvent.class));

        Flux<DomainEvent> result = useCase.apply(Mono.just(conceptoAprobado));

        StepVerifier
                .create(result)
                .assertNext(domainEvent -> {
                    ProductoPreProductivo productoPreProductivo = (ProductoPreProductivo) domainEvent;
                    Assertions.assertEquals(PRODUCTO_ID.value(), productoPreProductivo.aggregateRootId());
                })
                .verifyComplete();
    }
}