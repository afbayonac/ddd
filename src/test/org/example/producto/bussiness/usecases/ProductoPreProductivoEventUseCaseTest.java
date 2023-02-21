package org.example.producto.bussiness.usecases;

import org.example.producto.bussiness.gateways.DomainEventRepository;
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

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductoPreProductivoEventUseCaseTest {
    @Mock
    private DomainEventRepository repository;
    @Mock
    private EventBus eventBus;

    private ProductoPreProductivoEventUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new ProductoPreProductivoEventUseCase(repository, eventBus);
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
                .thenReturn(List.of(productoCreado, conceptoCreado, conceptoAprobado));

        Mockito
                .when(repository.saveEvent(ArgumentMatchers.any(ProductoPreProductivo.class)))
                .thenAnswer(interceptor -> interceptor.getArgument(0));

        List<DomainEvent> result = useCase.apply(conceptoAprobado);

        ProductoPreProductivo event = (ProductoPreProductivo) result.get(0);
        Assertions.assertEquals(PRODUCTO_ID.value(), event.aggregateRootId());
    }
}