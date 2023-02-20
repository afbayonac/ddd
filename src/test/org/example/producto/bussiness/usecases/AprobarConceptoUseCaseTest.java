package org.example.producto.bussiness.usecases;

import org.example.producto.bussiness.gateways.DomainEventRepository;
import org.example.producto.domain.comados.AprobarConceptoCommand;
import org.example.producto.domain.eventos.ConceptoAprobado;
import org.example.producto.domain.eventos.ConceptoCreado;
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

import java.util.List;

@ExtendWith(MockitoExtension.class)
class AprobarConceptoUseCaseTest {
    @Mock
    private DomainEventRepository repository;

    private AprobarConceptoUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new AprobarConceptoUseCase(repository);
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
                .thenReturn(List.of(productoCreado, conceptoCreado));

        Mockito
                .when(repository.saveEvent(ArgumentMatchers.any(ConceptoAprobado.class)))
                .thenAnswer(interceptor -> interceptor.getArgument(0));

        List<DomainEvent> result = useCase.apply(command);

        ConceptoAprobado event = (ConceptoAprobado) result.get(0);
        Assertions.assertEquals(CONCEPTO_ID, event.getConceptoId());
    }
}