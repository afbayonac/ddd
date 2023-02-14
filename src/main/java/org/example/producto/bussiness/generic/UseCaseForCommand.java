package org.example.producto.bussiness.generic;

import org.example.producto.generic.Command;
import org.example.producto.generic.DomainEvent;

import java.util.List;

public interface UseCaseForCommand <R extends Command>{
    List<DomainEvent> apply(R command);
}
