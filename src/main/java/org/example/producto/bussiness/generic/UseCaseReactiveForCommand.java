package org.example.producto.bussiness.generic;

import co.com.sofka.domain.generic.Command;
import co.com.sofka.domain.generic.DomainEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

public abstract class  UseCaseReactiveForCommand <R extends Command> implements Function<Mono<R>, Flux<co.com.sofka.domain.generic.DomainEvent>> {
    public abstract Flux<DomainEvent> apply(Mono<R> rMono);
}
