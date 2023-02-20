package org.example.producto.bussiness.generic;


import org.example.producto.generic.Command;
import org.example.producto.generic.DomainEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

public abstract class  UseCaseReactiveForCommand <R extends Command> implements Function<Mono<R>, Flux<DomainEvent>> {
    public abstract Flux<DomainEvent> apply(Mono<R> rMono);
}
