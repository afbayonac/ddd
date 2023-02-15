package org.example.producto.domain;

import org.example.producto.domain.values.*;
import org.example.producto.generic.Entity;

import java.util.*;

public class Concepto extends Entity<ConceptoId> {
    protected Resultado resultado;
    protected EstadoPrueba estado;
    protected Fecha fecha;
    protected Receta receta;
    protected Set<PruebaViabilidad> pruebasViabilidad;

    Concepto(ConceptoId id, Receta receta) {
        super(id);
        this.receta = receta;
        this.estado = new EstadoPrueba();
        this.fecha = new Fecha(new Date());
        this.pruebasViabilidad = new HashSet();
    }

    public void agregarResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    public void rechazar() {
       this.estado = this.estado.rechazar();
    }

    public void aprobar() {
        this.estado = this.estado.aprovar();
    }

    public void aprobarPruebaViabilidad(PruebaViabilidadId pruebaViabilidadId) {
        Optional<PruebaViabilidad> result = pruebasViabilidad
                .stream()
                .filter(c -> c.identity().equals(pruebaViabilidadId))
                .findFirst();

        if (result.isPresent()) {
            result.get().aprobar();
        }
    }

    public void rechazarPruebaViabilidad(PruebaViabilidadId pruebaViabilidadId) {
        Optional<PruebaViabilidad> result = pruebasViabilidad
                .stream()
                .filter(c -> c.identity().equals(pruebaViabilidadId))
                .findFirst();

        if (result.isPresent()) {
            result.get().rechazar();
        }
    }
}
